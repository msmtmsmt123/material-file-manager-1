package com.davtyan.filemanager.components.entry;

import com.davtyan.filemanager.data.Storage;

import java.io.File;
import java.util.Stack;

import lombok.Getter;

public class EntryModel implements EntryMvp.Model {
    private final Stack<String> entriesStack;
    private @Getter Storage[] entries;
    private String currentDir;

    public EntryModel() {
        entriesStack = new Stack<>();
    }

    @Override
    public void updateEntries(String dirPath) {
        String[] filenames = new File(dirPath).list();
        Storage[] entries = new Storage[filenames.length];
        File dirFile = new File(dirPath);
        for (int i = 0; i < filenames.length; i++) {
            entries[i] = new Storage(new File(dirFile, filenames[i]));
        }

        this.entries = entries;
        this.currentDir = dirPath;
    }

    @Override
    public void navigateForward(int position) {
        entriesStack.push(currentDir);
        updateEntries(entries[position].getPath());
    }

    @Override
    public void navigateBack() {
        updateEntries(entriesStack.pop());
    }

    @Override
    public boolean isAtRoot() {
        return entriesStack.size() == 0;
    }
}
