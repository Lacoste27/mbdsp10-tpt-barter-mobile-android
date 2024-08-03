package mbds.barter.service;

import mbds.barter.data.model.User;
import mbds.barter.data.request.AuthRequest;
import mbds.barter.data.response.AuthResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAuthService {
    @POST("/api/auth/login")
    Call<AuthResponse> login(@Body AuthRequest request);
}
