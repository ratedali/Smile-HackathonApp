package io.divergents.hackathon.smile.kids;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.divergents.hackathon.smile.R;

public class OutsideRyscylerviewFragment extends Fragment {
    public OutsideRyscylerviewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView outsideRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_outside_ryscylerview, container, false);
        String[] pizzaNames = new String[KidsWorldOutside.kidsworld.length];
        for (int i = 0; i < pizzaNames.length; i++) {
            pizzaNames[i] = KidsWorldOutside.kidsworld[i].getName();
        }
        int[] pizzaImages = new int[KidsWorldOutside.kidsworld.length];
        for (int i = 0; i < pizzaImages.length; i++) {
            pizzaImages[i] = KidsWorldOutside.kidsworld[i].getImageid();
        }
        OutsideCardViewAdapter adapter = new OutsideCardViewAdapter(getContext(), pizzaNames, pizzaImages);
        outsideRecycler.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        outsideRecycler.setLayoutManager(layoutManager);
        adapter.setListener(position -> {
            if (position == 0) {
                Intent intent = new Intent(getActivity(), SecondTestActivity.class);
                getActivity().startActivity(intent);
            }
            if (position == 2) {
                Intent intent = new Intent(getActivity(), ThirdTestActivity.class);
                getActivity().startActivity(intent);
            }
            if (position == 1) {
                Intent intent = new Intent(getActivity(), FourthTestActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return outsideRecycler;
    }
}
