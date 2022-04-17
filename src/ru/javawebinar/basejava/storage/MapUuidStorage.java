package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {
	Map<String, Resume> map = new HashMap<>();

	@Override
	protected Object getSearchKey(String uuid) {
		return map.get(uuid);
	}

	@Override
	protected boolean isExist(Object searchKey) {
		return map.containsKey(searchKey);
	}

	@Override
	protected void doSave(Object searchKey, Resume r) {

	}

	@Override
	protected Resume doGet(Object searchKey) {
		return null;
	}

	@Override
	protected void doDelete(Object searchKey) {

	}

	@Override
	protected void doUpdate(Resume r, Object key) {

	}

	@Override
	public void clear() {

	}

	@Override
	public Resume[] getAll() {
		return new Resume[0];
	}

	@Override
	public int size() {
		return 0;
	}
}
