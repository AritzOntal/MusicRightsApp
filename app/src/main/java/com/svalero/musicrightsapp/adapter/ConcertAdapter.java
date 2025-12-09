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

public class ConcertAdapter extends RecyclerView.Adapter<ConcertAdapter.ConcertHolder> {

    private Context context;
    private List<Concert> concertList;
    private OnItemClickListener listener;

    //CONTRATO
    public interface OnItemClickListener {
        void onEditClick(Concert concert);
        void onDeleteClick(long id);
        void onConcertDetailsClick(long id);
    }

    // Constructor que pide el listener
    public ConcertAdapter(Context context, List<Concert> dataList, OnItemClickListener listener) {
        this.context = context;
        this.concertList = dataList;
        this.listener = listener; //ESTA VARIABLE ES EL THIS QUE LE PASA LA ACTIVITY PARA COLARSE COMO UN LISNTER (PORQUE FIRMA CONTRATO ONITEMCLICKLISTENER)
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

        // Configurar el Click de EDITAR
        holder.modifyItemConcertButton.setOnClickListener(view -> {
            listener.onEditClick(concert); // RECONOCE EL CLICK Y DESPUÉS USA EL CONTRATO
        });

        // Configurar el Click de BORRAR
        holder.deleteItemConcertButton.setOnClickListener(view -> {
            listener.onDeleteClick(concert.getId());
        });

        holder.itemView.setOnClickListener(view ->
                listener.onConcertDetailsClick(concert.getId()
                ));
    }

    @Override
    public int getItemCount() {
        return concertList.size();
    }

    public class ConcertHolder extends RecyclerView.ViewHolder {

        public TextView concertName;
        public TextView concertDescription;
        public ImageButton modifyItemConcertButton; // Botones públicos para acceder desde onBindViewHolder
        public ImageButton deleteItemConcertButton;

        public ConcertHolder(@NonNull View itemView) {
            super(itemView);

            concertName = itemView.findViewById(R.id.item_concert_name);
            concertDescription = itemView.findViewById(R.id.item_concert_description);
            modifyItemConcertButton = itemView.findViewById(R.id.modifyItemConcertButton);
            deleteItemConcertButton = itemView.findViewById(R.id.deleteItemConcertButton);
        }
    }
}