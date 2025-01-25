package com.example.ma_32;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment_3 extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = "Выбор есть всегда";
        String message = "Выбери пищу";
        String button1String = "Вкусная пища";
        String button2String = "Здоровая пища";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);  // заголовок
        builder.setMessage(message); // сообщение
        builder.setPositiveButton(button1String, (dialog, id) -> Toast.makeText(getActivity(), "Вы сделали правильный выбор",
                Toast.LENGTH_LONG).show());
        builder.setNegativeButton(button2String, (dialog, id) -> Toast.makeText(getActivity(), "Возможно вы правы", Toast.LENGTH_LONG)
                .show());
        builder.setCancelable(true);

        return builder.create();
    }
}