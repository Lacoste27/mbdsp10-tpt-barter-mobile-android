package mbds.barter.service;

import mbds.barter.data.model.QRCPost;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IPostService {
    @GET("api/posts/{id}")
    Call<QRCPost> getPostById(@Path("id") String postId);
}
