package io.divergents.hackathon.smile.repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.divergents.hackathon.smile.data.Psychotherapist;
import io.divergents.hackathon.smile.repo.PsychotherapistsRepository;

public class RemotePsychotherapistRepository implements PsychotherapistsRepository {
    private static volatile RemotePsychotherapistRepository instance;
    public static final String DESIGNATED_PSYCHOTHERAPIST = "DESIGNATED_PSYCHOTHERAPIST";

    private FirebaseFirestore db;
    private SharedPreferences preferences;

    private final MutableLiveData<List<Psychotherapist>> psychotherapistsLiveData;
    private final MutableLiveData<Psychotherapist> psychotherapistLiveData;
    private final MutableLiveData<Psychotherapist> designatedPsychotherapistLiveData;

    private RemotePsychotherapistRepository(FirebaseFirestore db, SharedPreferences preferences) {
        this.db = db;
        this.preferences = preferences;
        psychotherapistsLiveData = new MutableLiveData<>();
        psychotherapistLiveData = new MutableLiveData<>();
        designatedPsychotherapistLiveData = new MutableLiveData<>();
    }

    public static RemotePsychotherapistRepository getInstance(FirebaseFirestore db,
                                                              SharedPreferences preferences) {
        if (instance == null) {
            synchronized (RemotePsychotherapistRepository.class) {
                if (instance == null) {
                    instance = new RemotePsychotherapistRepository(db, preferences);
                }
            }
        }
        return instance;
    }

    @Override
    public LiveData<List<Psychotherapist>> getPsychotherapists() {
        db.collection("psychotherapists")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<Psychotherapist> data = new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String patients = document.getString("patients");
                                Psychotherapist psychotherapist = new Psychotherapist(
                                        document.getString("name"),
                                        document.getString("phone"),
                                        document.getString("email"),
                                        patients == null ? null : Arrays.asList(patients.split(",")));
                                data.add(psychotherapist);
                            }
                        }
                        psychotherapistsLiveData.setValue(data);
                    }
                });
        return psychotherapistsLiveData;
    }

    private LiveData<Psychotherapist> getPsychotherapist(String id, final MutableLiveData<Psychotherapist> liveData) {
        db.collection("psychotherapists").document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            String patients = document.getString("patients");
                            Psychotherapist psychotherapist = new Psychotherapist(
                                    document.getId(),
                                    document.getString("name"),
                                    document.getString("phone"),
                                    document.getString("email"),
                                    patients == null ? null : Arrays.asList(patients.split(",")));
                            liveData.setValue(psychotherapist);
                        }
                    }
                });
        return liveData;
    }

    @Override
    public LiveData<Psychotherapist> getPsychotherapist(String id) {
        return getPsychotherapist(id, psychotherapistLiveData);
    }

    @Override
    public LiveData<Psychotherapist> getDesignatedPsychotherapist() {
        String id = preferences.getString(DESIGNATED_PSYCHOTHERAPIST, null);
        return getPsychotherapist(id, designatedPsychotherapistLiveData);
    }
}
