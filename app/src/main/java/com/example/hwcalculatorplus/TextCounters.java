package com.example.hwcalculatorplus;

import android.os.Parcel;
import android.os.Parcelable;

public class TextCounters implements Parcelable {
    private String textCounterA;
    private String textCounterAction;
    private String textCounterB;

    public void setTextCounterA(String textCounterA) {
        this.textCounterA = textCounterA;
    }

    public void setTextCounterAction(String textCounterAction) {
        this.textCounterAction = textCounterAction;
    }

    public void setTextCounterB(String textCounterB) {
        this.textCounterB = textCounterB;
    }

    public String getTextCounterA() {
        return textCounterA;
    }

    public String getTextCounterAction() {
        return textCounterAction;
    }

    public String getTextCounterB() {
        return textCounterB;
    }

    public TextCounters() {
        this.textCounterA = null;
        this.textCounterAction = null;
        this.textCounterB = null;
    }

    public TextCounters(String textCounterA, String textCounterAction, String textCounterB) {
        this.textCounterA = textCounterA;
        this.textCounterAction = textCounterAction;
        this.textCounterB = textCounterB;
    }

    protected TextCounters(Parcel in) {
        textCounterA = in.readString();
        textCounterAction = in.readString();
        textCounterB = in.readString();
    }

    public static final Creator<TextCounters> CREATOR = new Creator<TextCounters>() {
        @Override
        public TextCounters createFromParcel(Parcel in) {
            return new TextCounters(in);
        }

        @Override
        public TextCounters[] newArray(int size) {
            return new TextCounters[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(textCounterA);
        dest.writeString(textCounterAction);
        dest.writeString(textCounterB);
    }
}
