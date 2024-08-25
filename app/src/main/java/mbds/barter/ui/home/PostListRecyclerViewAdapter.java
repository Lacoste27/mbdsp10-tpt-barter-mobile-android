package mbds.barter.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mbds.barter.R;
import mbds.barter.data.model.QRCPost;
import mbds.barter.databinding.ItemCardBinding;

public class PostListRecyclerViewAdapter extends RecyclerView.Adapter<PostListRecyclerViewAdapter.CardViewHolder>{
    private final List<QRCPost> mValues;

    public PostListRecyclerViewAdapter(List<QRCPost> items) {
        mValues = items;
    }

//    @Override
//    public PostListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new PostListRecyclerViewAdapter.ViewHolder(ItemCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
//    }

    @Override
    public void onBindViewHolder(final PostListRecyclerViewAdapter.CardViewHolder holder, int position) {
        QRCPost post = mValues.get(position);

        holder.userName.setText(post.getAuthor().getName());

        StringBuilder objs = new StringBuilder();
        List<String> cats = new ArrayList<String>();

        for(QRCPost.ObjectPost objectPost: post.getObjects()){
            objs.append("• ").append(objectPost.getObject().getName()).append("\n");
            cats.add(objectPost.getObject().getCategory().getTitle());
        }

        // Convert List to Set to remove duplicates
        Set<String> set = new HashSet<>(cats);

        // Convert Set back to List
        List<String> listWithoutDuplicates = new ArrayList<>(set);

        holder.itemList.setText(objs.toString());
        holder.timeStamp.setText(post.getCreatedAt().toString());

        // Clear the container before adding new views
        holder.categoriesContainer.removeAllViews();

        for (String item : listWithoutDuplicates) {
            TextView textView = new TextView(holder.itemView.getContext());
            textView.setText(item);
            textView.setTextSize(12);
            textView.setBackgroundResource(R.drawable.categories_border);
            textView.setPadding(18, 18, 18, 18);
            textView.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.white));


            // Optional: Set layout parameters
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 8, 0);

            textView.setLayoutParams(params);

            // Add TextView to the LinearLayout
            holder.categoriesContainer.addView(textView);
        }
//        holder.mNameView.setText(object.getName());
//        holder.mDescriptionView.setText(object.getDescription());
        // Optionally handle image display using a library like Glide or Picasso if `image` is URLs.
    }


    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView userName, itemList, timeStamp;
        ImageView userImage, favoriteIcon;
        LinearLayout categoriesContainer;

        public CardViewHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_name);
            itemList = itemView.findViewById(R.id.item_list);
            timeStamp = itemView.findViewById(R.id.time_stamp);
            userImage = itemView.findViewById(R.id.user_image);
            categoriesContainer = itemView.findViewById(R.id.categories_container);
        }
    }
}
