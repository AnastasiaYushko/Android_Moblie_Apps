package com.example.ma_ex_26;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.widget.EditText;

public class MyPrefsFragment extends PreferenceFragment implements
        OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        updateListPrefSummary();
        updateHeightCat();
        updatePicker();
        updateSize();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    // Apply for ListPreference with key="pref_style"
    private void updateListPrefSummary() {
        ListPreference preference = (ListPreference) findPreference("Стиль");
        CharSequence entry = ((ListPreference) preference).getEntry();
        preference.setSummary("Текущая настройка: " + entry);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                          String key) {

        // if changed SharedPreference is ListPreference with key="pref_style",
        // update summary
        assert key != null;
        if (key.equals("Стиль")) {
            updateListPrefSummary();
        }
        if(key.equals("pref_height")){
            updateHeightCat();
        }
        if(key.equals("pref_picker")){
            updatePicker();
        }
        if(key.equals("Размер")){
            updateSize();
        }
    }

    private void updateHeightCat() {
        SeekBarDialogPreference seekBarDialogPreference = (SeekBarDialogPreference) findPreference("pref_height");
        int entry = seekBarDialogPreference.getProgress();
        seekBarDialogPreference.setSummary("Текущая настройка: " + entry + " см");
    }

    private void updatePicker() {
        NumberPickerDialogPreference numberPickerDialogPreference= (NumberPickerDialogPreference) findPreference("pref_picker");
        int entry = numberPickerDialogPreference.getValue();
        numberPickerDialogPreference.setSummary("Текущая настройка: " + entry + " гр");
    }

    private void updateSize() {
        EditTextPreference editTextPreference = (EditTextPreference) findPreference("Размер");
        String entry = ((EditTextPreference) editTextPreference).getText();
        editTextPreference.setSummary("Текущая настройка: " + entry);
    }

}