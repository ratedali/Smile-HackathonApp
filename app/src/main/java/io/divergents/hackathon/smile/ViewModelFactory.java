package io.divergents.hackathon.smile;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.google.firebase.firestore.FirebaseFirestore;

import io.divergents.hackathon.smile.repo.PsychotherapistsRepository;
import io.divergents.hackathon.smile.therapy.TherapyViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory instance;

    private final Application application;
    private final FirebaseFirestore db;
    private final PsychotherapistsRepository psychotherapistsRepository;

    public static ViewModelFactory getInstance(Application application, String psychotherapistPreferences) {
        if (instance == null) {
            synchronized (ViewModelFactory.class) {
                if (instance == null) {
                    instance = new ViewModelFactory(
                            Injections.provideFirabaseFirestore(),
                            Injections.providePsychotherapistRepository(
                                    application,
                                    psychotherapistPreferences),
                            application);
                }
            }
        }
        return instance;
    }

    public ViewModelFactory(FirebaseFirestore db,
                            PsychotherapistsRepository psychotherapistsRepository,
                            Application application) {
        this.db = db;
        this.psychotherapistsRepository = psychotherapistsRepository;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TherapyViewModel.class)) {
            return (T) new TherapyViewModel(db, psychotherapistsRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
