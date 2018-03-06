package com.galileo.micromaster.simplepreferencescreen;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PreferenceFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.app_preferences);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final EditTextPreference name_pref =
                (EditTextPreference)getPreferenceManager().findPreference("1");
        name_pref.setSummary(getPreferenceManager()
                .getSharedPreferences().getString("1","empty"));
        name_pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                preference.setSummary(String.valueOf(o));
                return true;
            }
        });
        final NumberPickerPreference num_pref =
                (NumberPickerPreference)getPreferenceManager().findPreference("3");
        num_pref.setSummary(String.valueOf(getPreferenceManager()
                .getSharedPreferences().getInt("3",0)));
        num_pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary(String.valueOf(newValue));
                return true;
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onDisplayPreferenceDialog(Preference preference) {

        // Try if the preference is one of our custom Preferences
        DialogFragment dialogFragment = null;
        if (preference instanceof NumberPickerPreference) {
            // Create a new instance of the Preference
            dialogFragment = NumberPickerDialogFragment.newInstance(preference.getKey());
        }


        if (dialogFragment != null) {
            // The dialog was created (it was one of our custom Preferences), show the dialog for it
            dialogFragment.setTargetFragment(this, 0);
            dialogFragment.show(this.getFragmentManager(), "android.support.v7.preference" +
                    ".PreferenceFragment.DIALOG");
        } else {
            // Dialog creation could not be handled here. Try with the super method.
            super.onDisplayPreferenceDialog(preference);
        }

    }

}
