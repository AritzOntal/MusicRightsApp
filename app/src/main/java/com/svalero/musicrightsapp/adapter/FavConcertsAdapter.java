package com.svalero.musicrightsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.musicrightsapp.R;
import com.svalero.musicrightsapp.domain.Concert;

import java.util.List;

public class FavConcertsAdapter extends RecyclerView.Adapter<FavConcertsAdapter.FavoriteHolder> {

    private Context context;
    private List <Concert> concertList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEditClick(Concert concert);
        void onDeleteClick(long id);
        void onConcertDetailsClick(Concert concert);
    }

    public FavConcertsAdapter(Context context, List<Concert> dataList, OnItemClickListener listener) {
        this.context = context;
        this.concertList = dataList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite_concert, parent, false);
        return new FavoriteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteHolder holder, int position) {
        Concert concert = concertList.get(position);

        holder.concertTitle.setText(concert.getShowTitle());
        holder.concertDate.setText(concert.getDate());


        holder.btnDelete.setOnClickListener(view -> {
            listener.onDeleteClick(concert.getId());
        });

        holder.btnEdit.setOnClickListener(view -> {
            listener.onEditClick(concert);
        });

    }

    @Override
    public int getItemCount() {
        return concertList.size();
    }

    public class FavoriteHolder extends RecyclerView.ViewHolder {
        TextView concertTitle;
        TextView concertDate;
        ImageButton btnEdit;
        ImageButton btnDelete;


        public FavoriteHolder(@NonNull View itemView) {
            super(itemView);

            concertTitle = itemView.findViewById(R.id.tvFavTitle);
            concertDate = itemView.findViewById(R.id.tvFavDate);
            btnDelete = itemView.findViewById(R.id.btnFavDelete);
            btnEdit = itemView.findViewById(R.id.btnFavEdit);
        }
    }
}
