package mbds.barter.data.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class User {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("email")
    public String email;
    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;
    @SerializedName("roleId")
    public int roleId;
    @SerializedName("createdAt")
    public LocalDateTime createdAt;
    @SerializedName("updatedAt")
    public LocalDateTime updatedAt;
}
