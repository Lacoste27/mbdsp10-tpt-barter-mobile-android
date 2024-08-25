package mbds.barter.ui.post.qrCodePostResult;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mbds.barter.R;
import mbds.barter.data.model.QRCPost;

public class ObjectPostAdapter extends RecyclerView.Adapter<ObjectPostAdapter.ObjectViewHolder>{
    private List<QRCPost.ObjectPost> objectPostList;

    public ObjectPostAdapter(List<QRCPost.ObjectPost> objectPostList) {
        this.objectPostList = objectPostList;
    }

    @NonNull
    @Override
    public ObjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_object_post, parent, false);
        return new ObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObjectViewHolder holder, int position) {
        QRCPost.ObjectPost objectPost = objectPostList.get(position);
        holder.bind(objectPost);
    }

    @Override
    public int getItemCount() {
        return objectPostList.size();
    }

    class ObjectViewHolder extends RecyclerView.ViewHolder {
        private TextView tvObjectName, tvObjectDescription;

        public ObjectViewHolder(@NonNull View itemView) {
            super(itemView);
            tvObjectName = itemView.findViewById(R.id.tvObjectName);
            tvObjectDescription = itemView.findViewById(R.id.tvObjectDescription);
        }

        public void bind(QRCPost.ObjectPost objectPost) {
            tvObjectName.setText(objectPost.getObject().getName());
            tvObjectDescription.setText(objectPost.getObject().getDescription());
        }
    }
}
