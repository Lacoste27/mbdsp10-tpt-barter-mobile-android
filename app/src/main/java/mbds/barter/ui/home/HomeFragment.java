package mbds.barter.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mbds.barter.R;
import mbds.barter.data.datasource.ObjectDataSource;
import mbds.barter.data.datasource.PostDataSource;
import mbds.barter.data.model.Objects;
import mbds.barter.data.model.QRCPost;
import mbds.barter.data.repository.ObjectRepository;
import mbds.barter.data.repository.PostRepository;
import mbds.barter.data.response.ObjectsResponse;
import mbds.barter.data.response.PostsResponse;
import mbds.barter.databinding.FragmentHomeBinding;
import mbds.barter.ui.objects.ObjectListRecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private RecyclerView recyclerView;
    private PostRepository postRepository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.liste);
        recyclerView.setLayoutManager(mColumnCount <= 1 ?
                new LinearLayoutManager(context) :
                new GridLayoutManager(context, mColumnCount));
        postRepository = PostRepository.getInstance(context, new PostDataSource());
        loadData();
        return view;
    }

    private void loadData() {
        postRepository.getAllPosts(new Callback<PostsResponse>() {
            @Override
            public void onResponse(Call<PostsResponse> call, Response<PostsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<QRCPost> items = response.body().getData();
                    recyclerView.setAdapter(new PostListRecyclerViewAdapter(items));
                } else {
                    // Handle the case where the server response is not successful
                    // e.g., show a message to the user
                }
            }

            @Override
            public void onFailure(Call<PostsResponse> call, Throwable t) {
                t.printStackTrace();
                // Handle the error, possibly update the UI to show an error message
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}