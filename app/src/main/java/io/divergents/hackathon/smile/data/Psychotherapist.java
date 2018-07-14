package io.divergents.hackathon.smile.data;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.UUID;

public class Psychotherapist {
    private final String id;
    private final String name;
    private final String phone;
    private final String email;
    private final List<String> patients;

    public Psychotherapist(String name, String phone, String email, List<String> patients) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.patients = patients;
    }

    public Psychotherapist(@NonNull String id, String name, String phone, String email, List<String> patients) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.patients = patients;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public List<String> getPatients() {
        return patients;
    }

    public String getEmail() {
        return email;
    }
}
