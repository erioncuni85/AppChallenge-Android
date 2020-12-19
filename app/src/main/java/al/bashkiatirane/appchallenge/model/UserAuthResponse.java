package al.bashkiatirane.appchallenge.model;

import  com.google.gson.annotations.Expose;
import  com.google.gson.annotations.SerializedName;



public class UserAuthResponse {



    @SerializedName("error")
    @Expose
    private Boolean error;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("data")
    @Expose
    private UserData data;

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
