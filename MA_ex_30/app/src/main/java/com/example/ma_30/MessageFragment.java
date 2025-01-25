package com.example.ma_30;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MessageFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        EditText messageEditText = view.findViewById(R.id.messagefragment_edit);
        Button translateButton = view.findViewById(R.id.messagefragment_translate_button);

        translateButton.setOnClickListener(v -> {
            String message = messageEditText.getText().toString();
            NavDirections action = MessageFragmentDirections.actionMessageFragmentToConverterFragment(message);
            Navigation.findNavController(view).navigate(action);
        });

        return view;
    }
}