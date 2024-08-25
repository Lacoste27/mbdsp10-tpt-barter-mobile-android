package mbds.barter.data.repository;

import android.content.Context;

import java.util.List;

import mbds.barter.data.datasource.ObjectDataSource;
import mbds.barter.data.datasource.PostDataSource;
import mbds.barter.data.local.ObjectsDAO;
import mbds.barter.data.local.QRCPostDAO;
import mbds.barter.data.model.Objects;
import mbds.barter.data.model.QRCPost;
import mbds.barter.data.response.ObjectsResponse;
import mbds.barter.data.response.PostsResponse;
import mbds.barter.utils.NetworkUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {
    private static volatile PostRepository instance;
    private PostDataSource dataSource;
    private QRCPostDAO postsDAO;
    private Context context;

    private PostRepository(Context context, PostDataSource dataSource) {
        this.context = context;
        this.dataSource = dataSource;
        this.postsDAO = new QRCPostDAO(context);
        this.postsDAO.open();
    }

    public static synchronized PostRepository getInstance(Context context, PostDataSource dataSource) {
        if (instance == null) {
            instance = new PostRepository(context, dataSource);
        }
        return instance;
    }

    public void getAllPosts(Callback<PostsResponse> callback) {
        if (NetworkUtils.isNetworkAvailable(context)) {
            // Online: Fetch from API
            dataSource.getAllPosts(new Callback<PostsResponse>() {
                @Override
                public void onResponse(Call<PostsResponse> call, Response<PostsResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        // Store the fetched objects into the local SQLite database
                        List<QRCPost> posts = response.body().getData();
                        for (QRCPost post : posts) {
                            postsDAO.insertQRCPost(post);
                        }
                        callback.onResponse(call, response);
                    } else {
                        // Offline: Fetch from SQLite
                        List<QRCPost> postsList = postsDAO.getAllQRCPosts();
                        PostsResponse postsResponse = new PostsResponse();
                        postsResponse.setData(postsList);
                        callback.onResponse(null, Response.success(postsResponse));
                    }
                }

                @Override
                public void onFailure(Call<PostsResponse> call, Throwable t) {
                    // Offline: Fetch from SQLite
                    List<QRCPost> postsList = postsDAO.getAllQRCPosts();
                    PostsResponse postsResponse = new PostsResponse();
                    postsResponse.setData(postsList);
                    callback.onResponse(null, Response.success(postsResponse));
                }
            });
        } else {
            // Offline: Fetch from SQLite
            List<QRCPost> postsList = postsDAO.getAllQRCPosts();
            PostsResponse postsResponse = new PostsResponse();
            postsResponse.setData(postsList);
            callback.onResponse(null, Response.success(postsResponse));
        }
    }
}
