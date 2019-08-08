package com.basejava.homework;

import java.util.Arrays;

public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println("Storage is empty");
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size >= storage.length) {
            System.out.println("Storage is full");
        } else if (index == -1) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("Resume in storage");
        }
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("Storage is empty");
        }
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Storage is empty");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }
}



