package com.example.ma_32;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment_6 extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final String[] catNamesArray = {"Васька", "Рыжик", "Мурзик"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выберите любимое имя кота")
                // добавляем переключатели
                .setSingleChoiceItems(catNamesArray, -1,
                        (dialog, item) -> Toast.makeText(
                                getActivity(),
                                "Любимое имя кота: "
                                        + catNamesArray[item],
                                Toast.LENGTH_SHORT).show())
                .setPositiveButton("OK", (dialog, id) -> {
                })
                .setNegativeButton("Отмена", (dialog, id) -> {
                });

        return builder.create();
    }
}