package com.liang.tind.www.tindtest.activty.kotlin;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * @author lonnie.liang
 * @date 2020/03/23 10:57
 */
public class Model implements Parcelable {
    private int star = 0;
    private String type = "";
    private ArrayList<String> issueTypes = null;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.star);
        dest.writeString(this.type);
        dest.writeStringList(this.issueTypes);
    }

    public Model() {
    }

    protected Model(Parcel in) {
        this.star = in.readInt();
        this.type = in.readString();
        this.issueTypes = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Model> CREATOR = new Parcelable.Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel source) {
            return new Model(source);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };
}
