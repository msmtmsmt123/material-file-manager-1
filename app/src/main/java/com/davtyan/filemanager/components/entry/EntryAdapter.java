package com.davtyan.filemanager.components.entry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davtyan.filemanager.R;
import com.davtyan.filemanager.data.Storage;
import com.davtyan.filemanager.utils.BoolUtils;

import java.util.Arrays;

public class EntryAdapter extends EntryMvp.Adapter {

    private final Context context;
    private final EntryMvp.Presenter presenter;

    public EntryAdapter(Context context, EntryMvp.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public EntryMvp.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitem_entry, parent, false);
        return new EntryViewHolder(view, presenter);
    }

    @Override
    public void onBindViewHolder(EntryMvp.ViewHolder holder, int position) {
        presenter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getEntryCount();
    }

    @Override
    public void updateEntries(Storage[] entries) {
        Arrays.sort(entries, (a, b) -> a.getName().compareToIgnoreCase(b.getName()));
        Arrays.sort(entries, (a, b) -> BoolUtils.compare(a.isFile(), b.isFile()));
        notifyDataSetChanged();
    }
}
