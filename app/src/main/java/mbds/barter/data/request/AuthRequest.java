package mbds.barter.data.request;

import com.google.gson.annotations.SerializedName;

public class AuthRequest {
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;
}
