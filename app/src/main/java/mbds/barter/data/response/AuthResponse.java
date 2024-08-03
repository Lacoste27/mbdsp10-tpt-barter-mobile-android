package mbds.barter.data.response;

import com.google.gson.annotations.SerializedName;

import mbds.barter.data.model.User;

public class AuthResponse {
    @SerializedName("user")
    private User user;
    @SerializedName("token")
    private String token;

    public AuthResponse() {

    }

    public AuthResponse(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
