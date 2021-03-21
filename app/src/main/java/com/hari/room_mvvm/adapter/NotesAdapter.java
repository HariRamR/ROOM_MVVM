package com.hari.room_mvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hari.room_mvvm.R;
import com.hari.room_mvvm.room.NoteModel;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MainViewHolder> {

    private Context context;
    private List<NoteModel> noteList = new ArrayList<>();

    public NotesAdapter(Context context){
        this.context = context;
    }

    public void setNotes(List<NoteModel> noteList){
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        holder.titleTxt.setText(noteList.get(position).getTitle());
        holder.messageTxt.setText(noteList.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder{

        TextView titleTxt, messageTxt;
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTxt = itemView.findViewById(R.id.title_list_notes);
            messageTxt = itemView.findViewById(R.id.message_list_notes);
        }
    }
}
