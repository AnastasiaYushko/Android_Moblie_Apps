package com.example.ma_32;

import android.app.Dialog;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import androidx.annotation.NonNull;

public class MyDialogFragment_2 extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Важное сообщение!")
                .setMessage("Покормите кота!")
                .setIcon(R.drawable.hungrycat)
                .setPositiveButton("ОК, иду на кухню", (dialog, id) -> dialog.cancel());
        return builder.create();
    }
}
