package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
	private Storage storage;

	private static final String UUID_1 = "uuid1";
	private static final Resume RESUME_1 = new Resume(UUID_1);
	private static final String UUID_2 = "uuid2";
	private static final Resume RESUME_2 = new Resume(UUID_2);
	private static final String UUID_3 = "uuid3";
	private static final Resume RESUME_3 = new Resume(UUID_3);

	protected AbstractArrayStorageTest(Storage storage) {
		this.storage = storage;
	}

	@Before
	public void setUp() throws Exception {
		storage.clear();
		storage.save(RESUME_1);
		storage.save(RESUME_2);
		storage.save(RESUME_3);
	}

	@Test
	public void size() throws Exception {
		Assert.assertEquals(3, storage.size());
	}

	@Test
	public void clear() throws Exception {
		storage.clear();
		Assert.assertEquals(0, storage.size());
	}

	@Test
	public void update() throws Exception {
		Resume r4 = new Resume(UUID_1);
		storage.update(r4);
		Assert.assertEquals(r4, storage.get(UUID_1));
	}

	@Test(expected = NotExistStorageException.class)
	public void updateNotExist() throws Exception {
		storage.update(new Resume());
	}

	@Test
	public void getAll() throws Exception {
		Resume[] array = storage.getAll();
		Assert.assertEquals(3, array.length);
		Assert.assertEquals(RESUME_1, array[0]);
		Assert.assertEquals(RESUME_2, array[1]);
		Assert.assertEquals(RESUME_3, array[2]);
	}

	@Test
	public void save() throws Exception {
		Resume resume = new Resume();
		storage.save(resume);
		int size = storage.getAll().length;
		Assert.assertEquals(4, size);
		Assert.assertEquals(resume, storage.get(resume.getUuid()));
	}

	@Test(expected = ExistStorageException.class)
	public void saveExist() throws Exception {
		storage.save(RESUME_1);
	}

	@Test
	public void delete() throws Exception {
		storage.delete(UUID_1);
		int size = storage.getAll().length;
		Assert.assertEquals(2, size);
	}

	@Test
	public void get() throws Exception {
		Assert.assertEquals(RESUME_1, storage.get(UUID_1));
		Assert.assertEquals(RESUME_2, storage.get(UUID_2));
		Assert.assertEquals(RESUME_3, storage.get(UUID_3));
	}

	@Test(expected = NotExistStorageException.class)
	public void getNotExist() throws Exception {
		storage.get("dummy");
	}

	@Test(expected = NotExistStorageException.class)
	public void deleteNotExist() throws Exception {
		storage.delete("dummy");
	}

	@Test(expected = StorageException.class)
	public void storageOverflow() throws Exception {
		try {
			for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
				storage.save(new Resume());
			}
		} catch (StorageException e) {
			Assert.fail("overflow happened ahead of time");
		}
		storage.save(new Resume());
	}
}