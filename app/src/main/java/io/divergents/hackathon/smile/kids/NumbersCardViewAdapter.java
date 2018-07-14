package io.divergents.hackathon.smile.kids;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import io.divergents.hackathon.smile.R;

class NumbersCardViewAdapter extends RecyclerView.Adapter<NumbersCardViewAdapter.ViewHolder> {
    private String[] captions;
    private int[] imageIds;
    private Listener listener;
    private Context context;

    public interface Listener {
        void onClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public NumbersCardViewAdapter(Context context, String[] captions, int[] imageIds) {
        this.context = context;
        this.captions = captions;
        this.imageIds = imageIds;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.numbers_layout_cardview, parent, false);
        return new ViewHolder(cv);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        ImageView imageView = cardView.findViewById(R.id.info_image);
        Drawable drawable = cardView.getResources().getDrawable(imageIds[position], context.getTheme());
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);
        cardView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return captions.length;
    }
}
