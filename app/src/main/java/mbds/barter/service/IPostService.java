package mbds.barter.service;

import mbds.barter.data.model.QRCPost;
import mbds.barter.data.response.ObjectsResponse;
import mbds.barter.data.response.PostsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IPostService {
    @GET("api/posts/{id}")
    Call<QRCPost> getPostById(@Path("id") String postId);

    @GET("/api/posts/explore/3")
    Call<PostsResponse> getAllPosts();
}
