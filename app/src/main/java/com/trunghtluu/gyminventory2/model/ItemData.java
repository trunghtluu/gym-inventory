package com.trunghtluu.gyminventory2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemData implements Parcelable {
    private String id;
    private String name;

    public ItemData(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    protected ItemData(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    public static final Creator<ItemData> CREATOR = new Creator<ItemData>() {
        @Override
        public ItemData createFromParcel(Parcel in) {
            return new ItemData(in);
        }

        @Override
        public ItemData[] newArray(int size) {
            return new ItemData[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
    }
}
