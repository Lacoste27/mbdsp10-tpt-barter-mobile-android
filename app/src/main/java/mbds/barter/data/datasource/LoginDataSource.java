package mbds.barter.data.datasource;

import mbds.barter.data.Result;
import mbds.barter.data.model.LoggedInUser;
import mbds.barter.data.model.User;
import mbds.barter.data.request.AuthRequest;
import mbds.barter.data.response.AuthResponse;
import mbds.barter.service.Api;
import mbds.barter.service.IAuthService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private IAuthService api;

    public void  login(String username, String password, Callback<AuthResponse> callback) {
        api = Api.getClient().create(IAuthService.class);

        AuthRequest request = new AuthRequest();
        request.email = username;
        request.password = password;

        Call<AuthResponse> call = api.login(request);

        call.enqueue(callback);
    }

    public void logout() {
        // TODO: revoke authentication
    }
}