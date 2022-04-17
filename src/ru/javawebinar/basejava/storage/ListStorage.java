package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

	List<Resume> list = new ArrayList<>();

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	protected Integer getSearchKey(String uuid) {
		for (int i = 0; i < list.size(); i++) {
			if (uuid.equals(list.get(i).getUuid())) {
				return i;
			}
		}
		return null;
	}

	@Override
	protected boolean isExist(Object searchKey) {
		return searchKey != null;
	}

	@Override
	protected void doSave(Object searchKey, Resume r) {
		list.add(r);
	}

	@Override
	protected Resume doGet(Object searchKey) {
		return list.get((Integer) searchKey);
	}

	@Override
	protected void doDelete(Object searchKey) {
		list.remove(((Integer) searchKey).intValue());
	}

	@Override
	protected void doUpdate(Resume r, Object searchKey) {
		list.set((Integer) searchKey, r);
	}

	@Override
	public Resume[] getAll() {
		return list.toArray(new Resume[list.size()]);
	}

	@Override
	public int size() {
		return list.size();
	}
}
