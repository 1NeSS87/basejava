package com.basejava.homework.storage;

import com.basejava.homework.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertIndex(Resume r, int index) {
        int numberIndex = - index - 1;
        System.arraycopy(storage, numberIndex, storage,numberIndex + 1, size - numberIndex);
        storage[numberIndex] = r;
    }

    @Override
    protected void deleteIndex(int index) {
        int numberIndex = size - index - 1;
        System.arraycopy(storage, index + 1, storage, index, numberIndex);
    }
}
