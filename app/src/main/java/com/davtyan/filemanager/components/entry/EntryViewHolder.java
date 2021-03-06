package com.davtyan.filemanager.components.entry;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.davtyan.filemanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntryViewHolder extends EntryMvp.ViewHolder implements View.OnClickListener {

    private final EntryMvp.Presenter presenter;

    @BindView(R.id.title) TextView titleView;
    @BindView(R.id.icon) ImageView iconView;

    public EntryViewHolder(View itemView, EntryMvp.Presenter presenter) {
        super(itemView);
        this.presenter = presenter;
        itemView.setOnClickListener(this);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setTitle(String title) {
        titleView.setText(title);
    }

    @Override
    public void setIsDirectory(boolean isDirectory) {
        if (isDirectory) {
            iconView.setImageResource(R.drawable.ic_directory);
        } else {
            iconView.setImageResource(R.drawable.ic_file);
        }
    }

    @Override
    public void onClick(View v) {
        presenter.onEntryClick(getAdapterPosition());
    }
}
