package io.divergents.hackathon.smile.therapy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.divergents.hackathon.smile.data.Message;
import io.divergents.hackathon.smile.databinding.ItemMessageBinding;
import io.divergents.hackathon.smile.databinding.ItemMessageReceivedBinding;

public class MessagesAdapter extends RecyclerView.Adapter {

    private static final int SENT_MESSAGE = 989;
    private static final int RECEIVED_MESSAGE = 506;

    private ArrayList<Message> messages;
    private String userId;

    public MessagesAdapter(String userId) {
        this.messages = new ArrayList<>();
        this.userId = userId;
    }

    public MessagesAdapter(List<Message> messages, String userId) {
        this.messages = new ArrayList<>(messages);
        this.userId = userId;
    }

    @Override
    public int getItemViewType(int position) {
        if (messages.get(position).getSenderId().equals(userId)) {
            return SENT_MESSAGE;
        } else {
            return RECEIVED_MESSAGE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        if (viewType == SENT_MESSAGE) {
            ItemMessageBinding binding = ItemMessageBinding.inflate(inflater, viewGroup, false);
            return new MessageViewHolder(binding);
        } else {
            ItemMessageReceivedBinding binding = ItemMessageReceivedBinding.inflate(inflater, viewGroup, false);
            return new ReceivedViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder.getItemViewType() == SENT_MESSAGE) {
            MessageViewHolder holder = (MessageViewHolder) viewHolder;
            holder.binding.setMessage(messages.get(position));
            holder.binding.executePendingBindings();
        } else {
            ReceivedViewHolder holder = (ReceivedViewHolder) viewHolder;
            holder.binding.setMessage(messages.get(position));
            holder.binding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setMessages(List<Message> messages) {
        this.messages.clear();
        this.messages.addAll(messages);
        notifyDataSetChanged();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        public ItemMessageBinding binding;

        public MessageViewHolder(@NonNull ItemMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class ReceivedViewHolder extends RecyclerView.ViewHolder {
        public ItemMessageReceivedBinding binding;

        public ReceivedViewHolder(ItemMessageReceivedBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
