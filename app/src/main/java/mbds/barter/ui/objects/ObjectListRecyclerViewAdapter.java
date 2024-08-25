package mbds.barter.ui.objects;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mbds.barter.data.model.Objects;
import mbds.barter.ui.objects.placeholder.PlaceholderContent.PlaceholderItem;
import mbds.barter.databinding.FragmentItemBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ObjectListRecyclerViewAdapter extends RecyclerView.Adapter<ObjectListRecyclerViewAdapter.ViewHolder> {

    private final List<Objects> mValues;

    public ObjectListRecyclerViewAdapter(List<Objects> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Objects object = mValues.get(position);
        holder.mNameView.setText(object.getName());
        holder.mDescriptionView.setText(object.getDescription());
        // Optionally handle image display using a library like Glide or Picasso if `image` is URLs.
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mNameView;
        public final TextView mDescriptionView;
        // Add an ImageView for the images if necessary

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mNameView = binding.name; // Adjust these IDs based on your actual layout
            mDescriptionView = binding.description;
        }
    }
}
