package io.divergents.hackathon.smile.kids;

import io.divergents.hackathon.smile.R;

/**
 * Created by Mukhtar on 7/13/2018.
 */

public class KidsWorldOutside {
    private String name;
    private int image_id;

    public KidsWorldOutside(String name, int image_id) {
        this.image_id = image_id;
        this.name = name;

    }

    public static final KidsWorldOutside[] kidsworld = {
            new KidsWorldOutside("NUMBER", R.drawable.kids_numbers_outside),
            new KidsWorldOutside("ALPHABET", R.drawable.kids_alphabet_outside),
            new KidsWorldOutside("numbers", R.drawable.kids_furnitue_outside)
    };


    public String getName() {
        return name;
    }

    public int getImageid() {
        return image_id;
    }
}
