package com.svalero.musicrightsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.musicrightsapp.R;
import com.svalero.musicrightsapp.domain.Concert;

import java.util.List;

public class ConcertAdapter extends RecyclerView.Adapter<ConcertAdapter.ConcertHolder> {

    private Context context;
    private List<Concert> concertList;

    public ConcertAdapter(Context context, List<Concert> dataList) {
        this.context = context;
        this.concertList = dataList;
    }

    @NonNull
    @Override
    public ConcertHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.concert_item, parent, false);
        return new ConcertHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConcertHolder holder, int position) {
        Concert concert = concertList.get(position);

        holder.concertName.setText(concert.getShowTitle());
        holder.concertDescription.setText(concert.getCity() + " - " + concert.getProvince());

    }

    @Override
    public int getItemCount() {
        return concertList.size();
    }

    public class ConcertHolder extends RecyclerView.ViewHolder {

        private TextView concertName;
        private TextView concertDescription;

        public ConcertHolder(@NonNull View itemView) {
            super(itemView);

            concertName = itemView.findViewById(R.id.item_concert_name);
            concertDescription = itemView.findViewById(R.id.item_concert_description);
        }
    }
}
