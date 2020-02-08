package com.example.soroushprofile.userprofile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soroushprofile.R;

public class UserMediaProfileAdapter extends RecyclerView.Adapter<UserMediaProfileAdapter.MyViewHolder> {

    @Nullable
    private UserMediaProfileSelectionProtocol mSelectionProtocol;

    public UserMediaProfileAdapter(@Nullable UserMediaProfileSelectionProtocol selectionProtocol) {
        this.mSelectionProtocol = selectionProtocol;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_media_profile_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(mSelectionProtocol != null ? mSelectionProtocol::onMediaClick : null);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
