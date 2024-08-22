package mbds.barter.ui.post;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mbds.barter.R;
import mbds.barter.data.datasource.CardData;

public class ListPostActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private List<CardData> cardDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_post);

        recyclerView = findViewById(R.id.recycler_view);

        // Set up the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the data list
        cardDataList = new ArrayList<>();
        loadData(); // Load your data into cardDataList

        // Create and set the adapter
        cardAdapter = new CardAdapter(cardDataList);
        recyclerView.setAdapter(cardAdapter);
    }

    private void loadData() {
        // Here you would normally fetch data from a server or database
        // For demonstration, we are adding dummy data

        // Example data
        cardDataList.add(new CardData("User1", "• Table\n• Camera\n• Tripod\n• Tripod\n• Tripod", "7 Hours ago", "8000 views"));
        cardDataList.add(new CardData("User1", "• Table\n• Camera\n• Tripod", "7 Hours ago", "8000 views"));
        cardDataList.add(new CardData("User1", "• Table\n• Camera\n• Tripod", "7 Hours ago", "8000 views"));
        cardDataList.add(new CardData("User2", "• Chair\n• Laptop\n• Monitor", "1 Day ago", "12000 views"));
        cardDataList.add(new CardData("User1", "• Table\n• Camera\n• Tripod", "7 Hours ago", "8000 views"));
        cardDataList.add(new CardData("User1", "• Table\n• Camera\n• Tripod", "7 Hours ago", "8000 views"));
        cardDataList.add(new CardData("User1", "• Table\n• Camera\n• Tripod", "7 Hours ago", "8000 views"));
        cardDataList.add(new CardData("User2", "• Chair\n• Laptop\n• Monitor", "1 Day ago", "12000 views"));
        // Add more data as needed
    }
}