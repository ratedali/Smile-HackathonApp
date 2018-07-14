package io.divergents.hackathon.smile.kids;

import io.divergents.hackathon.smile.R;

/**
 * Created by Mukhtar on 7/13/2018.
 */

public class Furniture {
    private String name;
    private int image_id;
    private int music_id;

    public Furniture(int image_id, int music_id) {
        this.image_id = image_id;

        this.music_id = music_id;

    }

    public static final Furniture[] thefurnitre = {
            new Furniture(R.drawable.bed, R.raw.bed),
            new Furniture(R.drawable.chair, R.raw.chair),
            new Furniture(R.drawable.cupboard, R.raw.cupboard),
            new Furniture(R.drawable.desk, R.raw.desk),
            new Furniture(R.drawable.lamp, R.raw.lamp),
            new Furniture(R.drawable.table, R.raw.table),
            new Furniture(R.drawable.television, R.raw.television)
    };


    public String getName() {
        return name;
    }

    public int getImageid() {
        return image_id;
    }

    public int getMusic_id() {
        return music_id;
    }
}
