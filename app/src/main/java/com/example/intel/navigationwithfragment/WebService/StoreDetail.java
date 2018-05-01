package com.example.intel.navigationwithfragment.WebService;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class StoreDetail implements Parcelable {

    @SerializedName("storeid")
    private String storeid;
    @SerializedName("storename")
    private String storename;
    @SerializedName("address")
    private String address;
    @SerializedName("city")
    private String city;
    @SerializedName("state")
    private String state;
    @SerializedName("country")
    private String country;
    @SerializedName("zip")
    private String zip;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String phone;
    @SerializedName("lat")
    private String lat;
    @SerializedName("lng")
    private String lng;
    @SerializedName("image")
    private String image;
    @SerializedName("availability")
    private String availability;
    @SerializedName("added_to_mystore")
    private boolean added_to_mystore;
    public StoreDetail(){

    }

    protected StoreDetail(Parcel in) {
        storeid = in.readString();
        storename = in.readString();
        address = in.readString();
        city = in.readString();
        state = in.readString();
        country = in.readString();
        zip = in.readString();
        email = in.readString();
        phone = in.readString();
        lat = in.readString();
        lng = in.readString();
        image = in.readString();
        availability = in.readString();
        added_to_mystore = in.readByte() != 0;
    }

    public static final Creator<StoreDetail> CREATOR = new Creator<StoreDetail>() {
        @Override
        public StoreDetail createFromParcel(Parcel in) {
            return new StoreDetail(in);
        }

        @Override
        public StoreDetail[] newArray(int size) {
            return new StoreDetail[size];
        }
    };

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public boolean isAdded_to_mystore() {
        return added_to_mystore;
    }

    public void setAdded_to_mystore(boolean added_to_mystore) {
        this.added_to_mystore = added_to_mystore;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(storeid);
        dest.writeString(storename);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(country);
        dest.writeString(zip);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(lat);
        dest.writeString(lng);
        dest.writeString(image);
        dest.writeString(availability);
        dest.writeByte((byte) (added_to_mystore ? 1 : 0));
    }
}
