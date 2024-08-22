package mbds.barter.ui.post.qrCodePostResult;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mbds.barter.R;
import mbds.barter.service.Api;
import mbds.barter.service.IPostService;

public class QrCodePostResultFragment extends Fragment {

    private QrCodePostResultViewModel mViewModel;

    public static QrCodePostResultFragment newInstance() {
        return new QrCodePostResultFragment();
    }

    private QrCodePostResultViewModel viewModel;

    private TextView postDescription ;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_qr_code_post_result, container, false);

        viewModel = new ViewModelProvider(this).get(QrCodePostResultViewModel.class);
        postDescription = root.findViewById(R.id.QRCodePostResultDescription);

        // Get the post ID from arguments
        String postId = null;
        if (getArguments() != null) {
            String url = getArguments().getString("scanned_result");
            postId = extractIdFromUrl(url);
        }
        Log.d("QrCodePostResultFragment", "Post ID: " + postId);
        if (postId != null) {
            viewModel.fetchPostById(postId);
        }

        // Observe the post data
        viewModel.getPostLiveData().observe(getViewLifecycleOwner(), post -> {
            if (post != null) {
                // Update UI with post data
                Log.d("QrCodePostResultFragment", "Post fetched: " + post.getDescription());
                postDescription.setText(post.getDescription());
            }
        });

        // Return the root view, not a new inflated view
        return root;
    }

    private String extractIdFromUrl(String url) {
        // Regular expression to match the ID in the URL
        Pattern pattern = Pattern.compile(".*/api/post/(\\d+)$");
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return matcher.group(1); // Return the ID
        } else {
            return null; // Return null if no ID is found
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(QrCodePostResultViewModel.class);
        // TODO: Use the ViewModel
    }

}