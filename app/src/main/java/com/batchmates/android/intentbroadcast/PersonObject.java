package com.batchmates.android.intentbroadcast;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Android on 7/8/2017.
 */

public class PersonObject implements Parcelable{

    String name;
    int age;
    String gender;

    public PersonObject(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public PersonObject(Parcel parcel)
    {
        this.name=parcel.readString();
        this.age=parcel.readInt();
        this.gender=parcel.readString();
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(age);
        parcel.writeString(gender);
    }


    public static final Creator<PersonObject> CREATOR=new Creator<PersonObject>() {
        @Override
        public PersonObject createFromParcel(Parcel parcel) {
            return new PersonObject(parcel);
        }

        @Override
        public PersonObject[] newArray(int i) {
            return new PersonObject[i];
        }
    };
}
