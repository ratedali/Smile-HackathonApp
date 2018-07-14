package io.divergents.hackathon.smile.therapy;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import io.divergents.hackathon.smile.Injections;
import io.divergents.hackathon.smile.R;
import io.divergents.hackathon.smile.ViewModelFactory;
import io.divergents.hackathon.smile.data.Message;
import io.divergents.hackathon.smile.databinding.ActivityTherapyBinding;

public class TherapyActivity extends AppCompatActivity {

    public static final String EXTRA_USER_ID = "USER_ID";
    public static final String EXTRA_PSYCHOTHERAPIST_ID = "PSYCHOTHERAPIST_ID";
    private String senderId = "IudmLNajw4mVxVIZSlPT";
    private String userId = "IudmLNajw4mVxVIZSlPT";
    private String psychotherapistId = "hvvMZdPiEXnrr1kzHJ2g";

    private FirebaseAuth auth;
    private TherapyViewModel therapyViewModel;
    private MessagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityTherapyBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_therapy);

        ViewModelFactory factory = ViewModelFactory.getInstance(getApplication(), "psychotherapists");
        therapyViewModel = ViewModelProviders.of(this, factory).get(TherapyViewModel.class);
        auth = Injections.provideFirabaseAuth();
//
//        Intent intent = getIntent();
//        senderId = auth.getCurrentUser().getUid();
//        psychotherapistId = intent.getStringExtra(EXTRA_PSYCHOTHERAPIST_ID);
//        userId = intent.getStringExtra(EXTRA_USER_ID);


        adapter = new MessagesAdapter(senderId);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setStackFromEnd(true);
        binding.messageList.setLayoutManager(manager);
        binding.messageList.setAdapter(adapter);

        therapyViewModel.getMessages(userId, psychotherapistId).observe(this, messages -> adapter.setMessages(messages));

        binding.sendButton.setOnClickListener(view -> {
            Message message = new Message(binding.messageText.getText().toString(),
                    senderId);
            binding.messageText.setText("");
            therapyViewModel.sendMessage(message, userId, psychotherapistId);
        });
    }
}
