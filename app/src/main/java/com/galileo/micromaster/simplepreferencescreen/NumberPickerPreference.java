package com.galileo.micromaster.simplepreferencescreen;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.preference.DialogPreference;
import android.util.AttributeSet;


public class NumberPickerPreference extends DialogPreference {

    private int mSelected;
    private int mLayoutResourceId = R.layout.number_picker_preference_dialog;

    public NumberPickerPreference(Context context) {
        this(context, null);
    }
    public NumberPickerPreference(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.preferenceStyle);
    }
    public NumberPickerPreference(Context context, AttributeSet attrs,
                          int defStyleAttr) {
        this(context, attrs, defStyleAttr, defStyleAttr);
    }
    public NumberPickerPreference(Context context, AttributeSet attrs,
                          int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        // Do custom stuff here
        // ...
        // read attributes etc.
    }

    public int getNumber(){
        return mSelected;
    }
    public void setNumber(int selectedNumber){
        mSelected = selectedNumber;
        persistInt(mSelected);
    }
    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        // Default value from attribute. Fallback value is set to 0.
        return a.getInt(index, 0);
    }
    @Override
    protected void onSetInitialValue(boolean restorePersistedValue,
                                     Object defaultValue) {
        // Read the value. Use the default value if it is not possible.
        setNumber(restorePersistedValue ?
                getPersistedInt(mSelected) : (int) defaultValue);
    }
    @Override
    public int getDialogLayoutResource() {
        return mLayoutResourceId;
    }

}
