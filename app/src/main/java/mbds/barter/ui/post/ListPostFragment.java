package mbds.barter.ui.post;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import mbds.barter.ui.post.qrCodePostResult.QrCodePostResultFragment;

import com.journeyapps.barcodescanner.ScanOptions;
import com.journeyapps.barcodescanner.ScanContract;

import android.widget.Toast;

public class ListPostFragment extends Fragment {

    private ListPostViewModel mViewModel;
    private FragmentListPostBinding binding;
    private ActivityResultLauncher<ScanOptions> barcodeLauncher;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Use view binding to inflate the layout and access views
        binding = FragmentListPostBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize the QR code scanner launcher
        barcodeLauncher = registerForActivityResult(new ScanContract(), result -> {
            String qrCodeString = result.getContents();
            if (qrCodeString!= null && qrCodeString.matches("^(http|https)://\\d{1,3}(\\.\\d{1,3}){3}:\\d+/api/post/\\d+$")) {
                Toast.makeText(getActivity(), "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

                // Navigate to the QR Code Result Fragment using NavController
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);

                // Pass the scanned result to the new fragment using a Bundle
                Bundle args = new Bundle();
                args.putString("scanned_result", result.getContents());

                navController.navigate(R.id.action_listPostFragment_to_qrCodePostResultFragment, args);
            } else {
                Toast.makeText(getActivity(), "Scan Cancelled", Toast.LENGTH_LONG).show();
            }
        });

        // Set an onClickListener to initiate the QR code scan
        binding.btnScanQr.setOnClickListener(v -> {
            System.out.println("Scanning QR code...");
            ScanOptions options = new ScanOptions();
            options.setPrompt("Scan a QR code");
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            barcodeLauncher.launch(options);
        });

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Navigation for the add post button
        binding.addPostButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.action_listPostFragment_to_addPost);
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListPostViewModel.class);
    }
}
