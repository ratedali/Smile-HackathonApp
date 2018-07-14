package io.divergents.hackathon.smile.kids;

import io.divergents.hackathon.smile.R;

/**
 * Created by Mukhtar on 7/13/2018.
 */

public class Numbers {
    private String name;
    private int image_id;
    private int music_id;

    public Numbers(String name, int image_id, int music_id) {
        this.image_id = image_id;
        this.name = name;
        this.music_id = music_id;

    }

    public static final Numbers[] thenumbers = {
            new Numbers("One", R.drawable.one, R.raw.one),
            new Numbers("Two", R.drawable.two, R.raw.two),
            new Numbers("Three", R.drawable.three, R.raw.three),
            new Numbers("Four", R.drawable.four, R.raw.four),
            new Numbers("Five", R.drawable.five, R.raw.five),
            new Numbers("Six", R.drawable.six, R.raw.six),
            new Numbers("Seven", R.drawable.seven, R.raw.seven),
            new Numbers("Eight", R.drawable.eight, R.raw.eight),
            new Numbers("Nine", R.drawable.nine, R.raw.nine)
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
