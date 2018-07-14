package io.divergents.hackathon.smile.therapy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.firebase.ui.auth.data.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import io.divergents.hackathon.smile.data.Psychotherapist;
import io.divergents.hackathon.smile.databinding.ListItemPsychotherapistBinding;
import io.divergents.hackathon.smile.databinding.ListItemUserBinding;

public class TherapyAdapter extends RecyclerView.Adapter {
    List<Psychotherapist> psychotherapists;
    List<User> users;
    Consumer<Psychotherapist> onClick;
    boolean userIsPsychotherapist;

    private static final int PSYCHOTHERAPIST = 740;
    private static final int PATIENT = 41;


    public TherapyAdapter(Consumer<Psychotherapist> onClick, boolean userIsPsychotherapist) {
        this.psychotherapists = new ArrayList<>();
        this.users = new ArrayList<>();
        this.onClick = onClick;
        this.userIsPsychotherapist = userIsPsychotherapist;
    }

    @Override
    public int getItemViewType(int position) {
        return userIsPsychotherapist ? PSYCHOTHERAPIST : PATIENT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == PSYCHOTHERAPIST) {
            ListItemPsychotherapistBinding binding = ListItemPsychotherapistBinding.inflate(
                    LayoutInflater.from(viewGroup.getContext()),
                    viewGroup, false);

            return new PsychotherapistViewHolder(binding);
        }
        ListItemUserBinding binding = ListItemUserBinding.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                viewGroup, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder.getItemViewType() == PSYCHOTHERAPIST) {

            ((PsychotherapistViewHolder) viewHolder).binding.setPsychotherapist(psychotherapists.get(i));
            viewHolder.itemView.setOnClickListener(view ->
                    onClick.accept(psychotherapists.get(viewHolder.getAdapterPosition())));
            return;
        }

        ((UserViewHolder) viewHolder).binding.setUser(users.get(i));
        viewHolder.itemView.setOnClickListener(view ->
                onClick.accept(psychotherapists.get(viewHolder.getAdapterPosition())));
        return;

    }

    @Override
    public int getItemCount() {
        if(userIsPsychotherapist)
            return users.size();
        return psychotherapists.size();
    }

    public void setPsychotherapists(List<Psychotherapist> psychotherapists) {
        this.psychotherapists.clear();
        this.psychotherapists.addAll(psychotherapists);
        notifyDataSetChanged();
    }

    public void setUsers(List<User> users) {
        this.users.clear();
        this.users.addAll(users);
        notifyDataSetChanged();
    }

    public static class PsychotherapistViewHolder extends RecyclerView.ViewHolder {
        ListItemPsychotherapistBinding binding;

        public PsychotherapistViewHolder(ListItemPsychotherapistBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        ListItemUserBinding binding;

        public UserViewHolder(ListItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
