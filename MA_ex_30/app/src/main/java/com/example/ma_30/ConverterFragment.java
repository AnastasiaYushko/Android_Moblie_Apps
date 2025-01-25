package com.example.ma_30;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ConverterFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_converter, container, false);

        String message = ConverterFragmentArgs.fromBundle(requireArguments()).getMessage();
        StringBuilder meow = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            meow.append("мяу ");
        }

        TextView translatedText = view.findViewById(R.id.converterfragment_text);
        translatedText.setText(meow.toString());

        return view;
    }
}