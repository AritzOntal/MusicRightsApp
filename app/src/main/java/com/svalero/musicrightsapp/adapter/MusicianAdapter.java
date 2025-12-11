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
import com.svalero.musicrightsapp.domain.Musician;

import java.util.List;

public class MusicianAdapter extends RecyclerView.Adapter<MusicianAdapter.MusicianHolder> {

    private Context context;
    private List<Musician> musicianList;
    private OnItemClickListener listener;

    //CONTRATO
    public interface OnItemClickListener {
        void onEditClick(Musician musician);
        void onDeleteClick(long id);
    }

    // Constructor que pide el listener
    public MusicianAdapter(Context context, List<Musician> dataList, OnItemClickListener listener) {
        this.context = context;
        this.musicianList = dataList;
        this.listener = listener; //ESTA VARIABLE ES EL THIS QUE LE PASA LA ACTIVITY PARA COLARSE COMO UN LISNTER (PORQUE FIRMA CONTRATO ONITEMCLICKLISTENER)
    }

    @NonNull
    @Override
    public MusicianHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.musician_item, parent, false);
        return new MusicianHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicianHolder holder, int position) {
        Musician musician = musicianList.get(position);

        holder.musicianName.setText(musician.getFirstName());
        holder.musicianLastName.setText(musician.getLastName());

        // Configurar el Click de EDITAR
        holder.modifyItemMusicianButton.setOnClickListener(view -> {
            listener.onEditClick(musician); // RECONOCE EL CLICK Y DESPUÉS USA EL CONTRATO
        });

        // Configurar el Click de BORRAR
        holder.deleteItemMusicianButton.setOnClickListener(view -> {
            listener.onDeleteClick(musician.getId());
        });

    }


    @Override
    public int getItemCount() {
        return musicianList.size();
    }

    public class MusicianHolder extends RecyclerView.ViewHolder {

        public TextView musicianName;
        public TextView musicianLastName;
        public ImageButton modifyItemMusicianButton; // Botones públicos para acceder desde onBindViewHolder
        public ImageButton deleteItemMusicianButton;

        public MusicianHolder(@NonNull View itemView) {
            super(itemView);

            musicianName = itemView.findViewById(R.id.item_musician_name);
            musicianLastName = itemView.findViewById(R.id.item_musician_surname);
            modifyItemMusicianButton = itemView.findViewById(R.id.modifyItemMusicianButton);
            deleteItemMusicianButton = itemView.findViewById(R.id.deleteItemMusicianButton);
        }
    }
}