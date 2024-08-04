package mbds.barter.ui.post;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import mbds.barter.R;
import mbds.barter.databinding.FragmentListPostBinding;
import mbds.barter.ui.post.add.AddPost;

public class ListPostFragment extends Fragment {

    private ListPostViewModel mViewModel;
    private FragmentListPostBinding binding;

    public static ListPostFragment newInstance() {
        return new ListPostFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentListPostBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        View view = inflater.inflate(R.layout.fragment_list_post, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstaceState) {
        super.onViewCreated(view, savedInstaceState);

        binding.addPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.action_listPostFragment_to_addPost);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListPostViewModel.class);
        // TODO: Use the ViewModel
    }

}