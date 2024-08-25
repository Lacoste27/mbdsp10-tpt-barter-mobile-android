package mbds.barter.data.datasource;

import java.io.IOException;

import mbds.barter.data.request.AuthRequest;
import mbds.barter.data.request.ObjectRequest;
import mbds.barter.data.response.AuthResponse;
import mbds.barter.data.response.ObjectsResponse;
import mbds.barter.data.response.PostsResponse;
import mbds.barter.service.Api;
import mbds.barter.service.IAuthService;
import mbds.barter.service.IObjectService;
import mbds.barter.service.IPostService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDataSource {
    private IPostService api;

    public PostDataSource() {
        api = Api.getClient().create(IPostService.class);
    }

    public void getAllPosts(Callback<PostsResponse> callback) {
        Call<PostsResponse> call = api.getAllPosts();
        call.enqueue(new Callback<PostsResponse>() {
            @Override
            public void onResponse(Call<PostsResponse> call, Response<PostsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, response);
                } else {
                    callback.onFailure(call, new IOException("Error fetching posts data"));
                }
            }

            @Override
            public void onFailure(Call<PostsResponse> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}
