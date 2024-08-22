package mbds.barter.ui.post;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mbds.barter.R;
import mbds.barter.data.datasource.CardData;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<CardData> cardDataList;

    public CardAdapter(List<CardData> cardDataList) {
        this.cardDataList = cardDataList;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        CardData cardData = cardDataList.get(position);

        holder.userName.setText(cardData.getUserName());
        holder.itemList.setText(cardData.getItemList());
        holder.timeStamp.setText(cardData.getTimeStamp());
        holder.viewsCount.setText(cardData.getViewsCount());
        // Remplir les autres données comme les tags, les images, etc.
    }

    @Override
    public int getItemCount() {
        return cardDataList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView userName, itemList, timeStamp, viewsCount;
        ImageView userImage, favoriteIcon;

        public CardViewHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_name);
            itemList = itemView.findViewById(R.id.item_list);
            timeStamp = itemView.findViewById(R.id.time_stamp);
            viewsCount = itemView.findViewById(R.id.views_count);
            userImage = itemView.findViewById(R.id.user_image);
            favoriteIcon = itemView.findViewById(R.id.favorite_icon);
        }
    }
}