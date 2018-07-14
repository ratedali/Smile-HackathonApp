package io.divergents.hackathon.smile.therapy;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.firebase.ui.auth.data.model.User;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import io.divergents.hackathon.smile.data.Message;
import io.divergents.hackathon.smile.data.Psychotherapist;
import io.divergents.hackathon.smile.remote.ChannelLiveData;
import io.divergents.hackathon.smile.repo.PsychotherapistsRepository;

public class TherapyViewModel extends ViewModel {

    private final PsychotherapistsRepository repository;
    private FirebaseFirestore db;
    private CollectionReference channel;
    private ChannelLiveData channelLiveData;
    private MutableLiveData<Boolean> userIsPsychotherapist = new MutableLiveData<>();

    public TherapyViewModel(FirebaseFirestore db,
                            PsychotherapistsRepository repository) {
        this.db = db;
        this.repository = repository;
    }

    LiveData<List<Message>> getMessages(String userId, String psychotherapistId) {
        if (channel == null || channelLiveData == null) {
            channel = db.collection("therapies")
                    .document(userId + "," + psychotherapistId)
                    .collection("messages");
            channelLiveData = new ChannelLiveData(channel);
        }
        return channelLiveData;
    }

    LiveData<Psychotherapist> getPsychotherapist(String psychotherapistId) {
        return repository.getPsychotherapist(psychotherapistId);
    }

    LiveData<List<Psychotherapist>> getPsychotherapists() {
        return repository.getPsychotherapists();
    }

    LiveData<Boolean> isUserPsychotherapist(String id) {
        db.collection("users").document(id).get()
                .addOnSuccessListener(document -> {
                    userIsPsychotherapist.setValue(document.getBoolean("isPsychotherapist"));
                });
        return userIsPsychotherapist;
    }

    public String getUserId(String userId) {
        return userId;
    }

    void sendMessage(Message message, String userId, String psychotherapistId) {
        HashMap<String, Object> map = new HashMap<>(message.toMap());
        map.put("timestamp", FieldValue.serverTimestamp());
        db.collection("therapies")
                .document(userId + "," + psychotherapistId)
                .collection("messages")
                .add(map);

    }
}
