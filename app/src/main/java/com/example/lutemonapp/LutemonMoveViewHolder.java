package com.example.lutemonapp;

import android.view.View;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonMoveViewHolder extends RecyclerView.ViewHolder {

    CheckBox checkboxName;

    public LutemonMoveViewHolder(@NonNull View itemView){
        super(itemView);
        checkboxName = itemView.findViewById(R.id.checkName);
    }
}
