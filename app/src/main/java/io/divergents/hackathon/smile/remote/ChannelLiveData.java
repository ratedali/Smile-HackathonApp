package io.divergents.hackathon.smile.remote;

import android.arch.lifecycle.LiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import io.divergents.hackathon.smile.data.Message;

public class ChannelLiveData extends LiveData<List<Message>> {

    private final CollectionReference channel;
    private ListenerRegistration registration;
    private final EventListener<QuerySnapshot> messageListener = (queryDocumentSnapshots, e) -> {
        List<Message> messages = new ArrayList<>();
        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
            Message message = new Message(document.getString("content"),
                    document.getString("senderId"),
                    document.getDate("timestamp"));
            messages.add(message);
        }
        setValue(messages);
    };

    public ChannelLiveData(CollectionReference channel) {
        this.channel = channel;
    }


    @Override
    protected void onActive() {
        super.onActive();
        registration = channel.orderBy("timestamp")
                .addSnapshotListener(messageListener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if (registration != null) {
            registration.remove();
            registration = null;
        }
    }

}
