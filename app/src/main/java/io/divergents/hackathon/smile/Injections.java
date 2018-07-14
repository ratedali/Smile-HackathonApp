package io.divergents.hackathon.smile;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import io.divergents.hackathon.smile.repo.PsychotherapistsRepository;
import io.divergents.hackathon.smile.repo.RemotePsychotherapistRepository;

public class Injections {
    public static FirebaseFirestore provideFirabaseFirestore() {
        return FirebaseFirestore.getInstance();
    }

    public static PsychotherapistsRepository providePsychotherapistRepository(Context context, String sharedPreferences) {
        return RemotePsychotherapistRepository.getInstance(
                provideFirabaseFirestore(),
                context.getSharedPreferences(sharedPreferences, Context.MODE_PRIVATE));
    }

    public static FirebaseAuth provideFirabaseAuth() {
        return FirebaseAuth.getInstance();
    }
}
