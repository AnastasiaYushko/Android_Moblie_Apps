package com.example.ma_32;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment_4 extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Выберите правильный ответ")
                .setCancelable(true)
                .setPositiveButton("Мяу",
                        (dialog, id) -> dialog.cancel())
                .setNeutralButton("Гав",
                        (dialog, id) -> dialog.cancel())
                .setNegativeButton("Сам дурак!",
                        (dialog, id) -> dialog.cancel());

        return builder.create();
    }
}