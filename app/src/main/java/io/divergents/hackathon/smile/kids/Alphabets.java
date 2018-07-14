package io.divergents.hackathon.smile.kids;

import io.divergents.hackathon.smile.R;

/**
 * Created by Mukhtar on 7/13/2018.
 */

public class Alphabets {
    private String name;
    private int image_id;
    private int music_id;

    public Alphabets(int image_id, int music_id) {
        this.image_id = image_id;

        this.music_id = music_id;

    }

    public static final Alphabets[] theAlpha = {
            new Alphabets(R.drawable.a, R.raw.a),
            new Alphabets(R.drawable.b, R.raw.b),
            new Alphabets(R.drawable.c, R.raw.c),
            new Alphabets(R.drawable.d, R.raw.d),
            new Alphabets(R.drawable.e, R.raw.e),
            new Alphabets(R.drawable.f, R.raw.f),
            new Alphabets(R.drawable.g, R.raw.g),
            new Alphabets(R.drawable.h, R.raw.h),
            new Alphabets(R.drawable.i, R.raw.i),
            new Alphabets(R.drawable.j, R.raw.j),
            new Alphabets(R.drawable.k, R.raw.k),
            new Alphabets(R.drawable.l, R.raw.l),
            new Alphabets(R.drawable.m, R.raw.m),
            new Alphabets(R.drawable.n, R.raw.n),
            new Alphabets(R.drawable.o, R.raw.o),
            new Alphabets(R.drawable.p, R.raw.p),
            new Alphabets(R.drawable.q, R.raw.q),
            new Alphabets(R.drawable.r, R.raw.r),
            new Alphabets(R.drawable.s, R.raw.s),
            new Alphabets(R.drawable.t, R.raw.t),
            new Alphabets(R.drawable.u, R.raw.u),
            new Alphabets(R.drawable.v, R.raw.v),
            new Alphabets(R.drawable.w, R.raw.w),
            new Alphabets(R.drawable.x, R.raw.x),
            new Alphabets(R.drawable.y, R.raw.y),
            new Alphabets(R.drawable.z, R.raw.z),
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
