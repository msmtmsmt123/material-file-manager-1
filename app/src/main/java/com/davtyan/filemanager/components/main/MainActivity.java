package com.davtyan.filemanager.components.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.davtyan.filemanager.App;
import com.davtyan.filemanager.R;
import com.davtyan.filemanager.data.Storage;
import com.davtyan.filemanager.views.storage.StorageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainMvp.View {

    @BindView(R.id.storage_internal) StorageView internalStorageView;
    @BindView(R.id.storage_container) LinearLayout storageContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MainFactory factory = ((App) getApplicationContext()).getMainFactory(this, this);
        MainMvp.Presenter presenter = factory.getPresenter();
        presenter.onCreate();

        internalStorageView.setTitle(R.string.storage_internal_title);
        internalStorageView.setIcon(R.drawable.ic_smartphone);
    }

    @Override
    public void setInternalStorage(Storage storage) {
        internalStorageView.setSpace(storage.getFreeSpace(), storage.getTotalSpace());
    }

    @Override
    public void setExternalStorage(Storage storage) {
        StorageView storageView = new StorageView(this);
        storageView.setTitle(storage.getName());
        storageView.setSpace(storage.getFreeSpace(), storage.getTotalSpace());
        storageView.setIcon(R.drawable.ic_sdcard);
        storageContainer.addView(storageView);
    }
}
