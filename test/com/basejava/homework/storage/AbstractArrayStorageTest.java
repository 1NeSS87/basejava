package com.basejava.homework.storage;

import com.basejava.homework.exception.ExistStorageException;
import com.basejava.homework.exception.NotExistStorageException;
import com.basejava.homework.exception.StorageException;
import com.basejava.homework.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;//1
    }

    private static final String UUID_1 = "uuid1";
    private static final Resume r1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume r2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume r3 = new Resume(UUID_3);
    private static final String UUID_4 = "uuid4";
    private static final Resume r4 = new Resume(UUID_4);

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume resumeUp = new Resume(UUID_2);
        storage.update(resumeUp);
        assertEquals(resumeUp, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(r4);
    }

    @Test
    public void getAll() throws Exception {
        Resume[] array = storage.getAll();
        assertEquals(array[0], r1);
        assertEquals(array[1], r2);
        assertEquals(array[2], r3);
        assertEquals(3, storage.size());
    }

    @Test
    public void save() throws Exception {
        storage.save(r4);
        assertEquals(r4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(r1);
    }

    @Test(expected = StorageException.class)
    public void storageOverflow() throws Exception {
        try {
            for(int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(r4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_3);
        assertEquals(2, storage.size());
        assertEquals(r3, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void get() throws Exception {
        assertEquals(r1, storage.get(UUID_1));
        assertEquals(r2, storage.get(UUID_2));
        assertEquals(r3, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}