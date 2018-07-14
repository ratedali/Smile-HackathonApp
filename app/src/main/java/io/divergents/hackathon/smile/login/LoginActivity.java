package io.divergents.hackathon.smile.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;

import io.divergents.hackathon.smile.Injections;
import io.divergents.hackathon.smile.R;
import io.divergents.hackathon.smile.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    public static final String ARG_USER_ADDITIONAL_INFO = "com.example.ali.myapplication.additional_user_info";
    public static final String ARG_USER_IS_PSYCHOTHERAPIST = "com.example.ali.myapplication.user_is_psychotherapist";

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        auth = Injections.provideFirabaseAuth();
        db = Injections.provideFirabaseFirestore();

        binding.loginButton.setOnClickListener(view -> {
            Arrays.asList(binding.email, binding.password)
                    .forEach(field -> field.setTextColor(getResources().getColor(R.color.colorPrimaryText, getTheme())));
            auth.signInWithEmailAndPassword(
                    binding.email.getText().toString(),
                    binding.password.getText().toString())
                    .addOnSuccessListener(this::loginSuccessful).addOnFailureListener(e -> {
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    wrongInput();
                    return;
                }
                auth.createUserWithEmailAndPassword(
                        binding.email.getText().toString(),
                        binding.password.getText().toString())
                        .addOnSuccessListener(this::loginSuccessful).addOnFailureListener(e1 -> {
                    if (e1 instanceof FirebaseAuthInvalidCredentialsException) {
                        wrongInput();
                        return;
                    }
                    loginFailed();
                });
            });
        });
    }

    void loginSuccessful(AuthResult result) {
        String id = result.getUser().getUid();
        db.collection("users").document(id).get()
                .addOnSuccessListener(document -> {
                    Intent data = new Intent();
                    data.putExtra(ARG_USER_ADDITIONAL_INFO,
                            result.getAdditionalUserInfo());
                    data.putExtra(ARG_USER_IS_PSYCHOTHERAPIST,
                            document.getBoolean("isPsychotherapist"));
                    setResult(RESULT_OK, data);
                    finish();
                }).addOnFailureListener(error -> {
            Intent data = new Intent();
            data.putExtra(ARG_USER_IS_PSYCHOTHERAPIST, false);
            data.putExtra(ARG_USER_ADDITIONAL_INFO, result.getAdditionalUserInfo());
            setResult(RESULT_OK, data);
            finish();
        });
    }

    void wrongInput() {
        Arrays.asList(binding.email, binding.password)
                .forEach(view ->
                        view.setTextColor(getResources().getColor(R.color.colorError, getTheme())));
    }

    void loginFailed() {
    }
}
