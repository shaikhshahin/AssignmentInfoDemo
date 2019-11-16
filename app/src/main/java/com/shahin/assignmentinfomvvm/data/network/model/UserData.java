package com.shahin.assignmentinfomvvm.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.shahin.assignmentinfomvvm.utils.AppConstants;

/**
 * Created by Shahin on 16/11/2019.
 */
public class UserData implements Parcelable {

    @Expose
    @SerializedName(AppConstants.DESC)
    private String description;

    @Expose
    @SerializedName(AppConstants.IMAGE_HREF)
    private String image;

    @Expose
    @SerializedName(AppConstants.TITLE)
    private String title;

    protected UserData(Parcel in) {
        description = in.readString();
        image = in.readString();
        title = in.readString();
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    public UserData(String description, String image, String title) {
        this.description = description;
        this.image = image;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeString(image);
        parcel.writeString(title);
    }
}
