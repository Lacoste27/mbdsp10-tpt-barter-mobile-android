package mbds.barter.data.datasource;

import mbds.barter.data.Result;
import mbds.barter.data.model.LoggedInUser;
import mbds.barter.data.model.User;
import mbds.barter.data.request.AuthRequest;
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

    public Result<User> login(String username, String password) {
        try {
            api = Api.getClient().create(IAuthService.class);

            AuthRequest request = new AuthRequest();
            request.email = username;
            request.password = password;

            Call<User> call = api.login(request);
            CompletableFuture<User> future = new CompletableFuture<>();
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        future.complete(response.body());
                    } else {
                        future.completeExceptionally(new IOException("Error logging in: " + response.message()));
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    future.completeExceptionally(new IOException("Error logging in", t));
                }
            });

            // Wait for the response
            User user = future.get();
            return new Result.Success<>(user);

        } catch (ExecutionException | InterruptedException e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}