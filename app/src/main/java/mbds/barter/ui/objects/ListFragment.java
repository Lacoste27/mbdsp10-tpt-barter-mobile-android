package mbds.barter.ui.objects;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mbds.barter.R;
import mbds.barter.data.datasource.ObjectDataSource;
import mbds.barter.data.model.Objects;
import mbds.barter.data.repository.ObjectRepository;
import mbds.barter.data.response.ObjectsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private RecyclerView recyclerView;
    private ObjectRepository objectRepository;

    public static ListFragment newInstance(int columnCount) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(mColumnCount <= 1 ?
                new LinearLayoutManager(context) :
                new GridLayoutManager(context, mColumnCount));
        objectRepository = ObjectRepository.getInstance(context, new ObjectDataSource());
        loadData();
        return view;
    }

    private void loadData() {
        objectRepository.getAllObjects(new Callback<ObjectsResponse>() {
            @Override
            public void onResponse(Call<ObjectsResponse> call, Response<ObjectsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Objects> items = response.body().getData();
                    recyclerView.setAdapter(new ObjectListRecyclerViewAdapter(items));
                } else {
                    // Handle the case where the server response is not successful
                    // e.g., show a message to the user
                }
            }

            @Override
            public void onFailure(Call<ObjectsResponse> call, Throwable t) {
                t.printStackTrace();
                // Handle the error, possibly update the UI to show an error message
            }
        });
    }
}
