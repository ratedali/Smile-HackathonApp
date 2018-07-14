package io.divergents.hackathon.smile.kids;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.divergents.hackathon.smile.R;

public class NumbersRyscylerviewFragment extends Fragment {
    public NumbersRyscylerviewFragment() {
    }

    private int zeftzeft;
    private OutsideCardViewAdapter Aladapter;
    private int[] Alnumbername;
    private StaggeredGridLayoutManager _sGridLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView numberRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_number_ryscylerview, container, false);
        String[] pizzaNames = new String[Numbers.thenumbers.length];
        int[] pizzaImages = new int[Numbers.thenumbers.length];
        for (int i = 0; i < pizzaImages.length; i++) {
            pizzaImages[i] = Numbers.thenumbers[i].getImageid();
        }
        final int[] numbername = new int[Numbers.thenumbers.length];
        for (int i = 0; i < numbername.length; i++) {
            numbername[i] = Numbers.thenumbers[i].getMusic_id();
        }
        Alnumbername = numbername;
        OutsideCardViewAdapter adapter = new OutsideCardViewAdapter(getContext(), pizzaNames, pizzaImages);
        Aladapter = adapter;
        numberRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        numberRecycler.setLayoutManager(layoutManager);

        return numberRecycler;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Aladapter.setListener(position -> {
            zeftzeft = Alnumbername[position];
            MediaPlayer ring = MediaPlayer.create(getActivity(), zeftzeft);
            ring.start();
        });
    }
}
