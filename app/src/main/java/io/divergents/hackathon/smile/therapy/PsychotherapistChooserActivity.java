package io.divergents.hackathon.smile.therapy;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;

import io.divergents.hackathon.smile.Injections;
import io.divergents.hackathon.smile.R;
import io.divergents.hackathon.smile.ViewModelFactory;
import io.divergents.hackathon.smile.databinding.ActivityPsychotherapistChooserBinding;

public class PsychotherapistChooserActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private TherapyViewModel therapyViewModel;
    private TherapyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityPsychotherapistChooserBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_psychotherapist_chooser);

        ViewModelFactory factory = ViewModelFactory.getInstance(getApplication(), "psychotherapists");
        therapyViewModel = ViewModelProviders.of(this, factory).get(TherapyViewModel.class);
        auth = Injections.provideFirabaseAuth();

        binding.psychotherapistsList.setLayoutManager(new LinearLayoutManager(this));
        therapyViewModel.isUserPsychotherapist(auth.getUid()).observe(this, isUserPsychotherapist -> {
            boolean isUserPsychotherapistGuard =  isUserPsychotherapist == null ? false : isUserPsychotherapist;
            adapter = new TherapyAdapter(psychotherapist -> {
                Intent intent = new Intent(this, TherapyActivity.class);
                intent.putExtra(TherapyActivity.EXTRA_PSYCHOTHERAPIST_ID, psychotherapist.getId());
                intent.putExtra(TherapyActivity.EXTRA_USER_ID, auth.getCurrentUser().getUid());
                startActivity(intent);
            }, isUserPsychotherapistGuard);
            binding.psychotherapistsList.setAdapter(adapter);
            if(!isUserPsychotherapistGuard)
                therapyViewModel.getPsychotherapists().observe(this, psychotherapists -> adapter.setPsychotherapists(psychotherapists));
        });
    }
}
