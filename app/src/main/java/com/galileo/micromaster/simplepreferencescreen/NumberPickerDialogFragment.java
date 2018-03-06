package com.galileo.micromaster.simplepreferencescreen;

import android.os.Bundle;
import android.support.v7.preference.DialogPreference;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.view.View;
import android.widget.NumberPicker;


public class NumberPickerDialogFragment extends PreferenceDialogFragmentCompat {

    private NumberPicker picker;


    public static NumberPickerDialogFragment newInstance(String key) {
        final NumberPickerDialogFragment fragment =
                new NumberPickerDialogFragment();
        final Bundle b = new Bundle(1);
        b.putString(ARG_KEY, key);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    protected void onBindDialogView(View view){
        picker = view.findViewById(R.id.edit);

        if (picker == null) {
            throw new IllegalStateException("Dialog view must contain" +
                    " a NumberPicker with id 'edit'");
        }
        picker.setMinValue(0);
        picker.setMaxValue(10);
        Integer number = -1;
        DialogPreference pref = getPreference();
        if(pref instanceof NumberPickerPreference){
            number = ((NumberPickerPreference) pref).getNumber();
        }
        if(number>=0){
            picker.setValue(number);
        }

    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        if(positiveResult){
            int number = picker.getValue();
            DialogPreference preference = getPreference();
            if(preference instanceof NumberPickerPreference){
                NumberPickerPreference numberPickerPreference = ((NumberPickerPreference)preference);
                if(numberPickerPreference.callChangeListener(number)){
                    numberPickerPreference.setNumber(number);
                }

            }
        }
    }
}
