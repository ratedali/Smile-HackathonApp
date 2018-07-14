package io.divergents.hackathon.smile.data;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Message {
    @NonNull
    private String id;
    private String content;
    private String senderId;
    private Date timestamp;

    public Message() {
        this(null, null);
    }

    public Message(String content, String senderId) {
        this(UUID.randomUUID().toString(), content, senderId, Calendar.getInstance().getTime());
    }

    public Message(String content, String senderId, Date timestamp) {
        this(UUID.randomUUID().toString(), content, senderId, timestamp);
    }

    public Message(@NonNull String id, String content, String senderId, Date timestamp) {
        this.id = id;
        this.content = content;
        this.senderId = senderId;
        this.timestamp = timestamp;
    }

    @NonNull
    public String getId() {

        return id;
    }

    public String getContent() {
        return content;
    }

    public String getSenderId() {
        return senderId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("content", content);
        map.put("senderId", senderId);
        map.put("timestamp", timestamp);
        return map;
    }
}
