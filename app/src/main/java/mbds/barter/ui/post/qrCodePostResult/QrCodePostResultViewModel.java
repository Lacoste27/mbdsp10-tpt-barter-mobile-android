package mbds.barter.ui.post.qrCodePostResult;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import mbds.barter.data.model.QRCPost;
import mbds.barter.service.Api;
import mbds.barter.service.IPostService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QrCodePostResultViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<QRCPost> postLiveData = new MutableLiveData<>();
    private  final IPostService postService;
    public QrCodePostResultViewModel() {
        this.postService = Api.getClient().create(IPostService.class);
    }

    public LiveData<QRCPost> getPostLiveData() {
        return postLiveData;
    }

    public void fetchPostById(String postId) {
        postService.getPostById(postId).enqueue(new Callback<QRCPost>() {
            @Override
            public void onResponse(Call<QRCPost> call, Response<QRCPost> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    postLiveData.setValue(response.body());
                } else {
                    // Handle error response
                }
            }

            @Override
            public void onFailure(Call<QRCPost> call, Throwable throwable) {

            }

        });
    }
}