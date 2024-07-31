package mbds.barter.service;

import mbds.barter.data.model.User;
import mbds.barter.data.request.AuthRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAuthService {
    @POST("/api/auth/login")
    Call<User> login(@Body AuthRequest request);
}
