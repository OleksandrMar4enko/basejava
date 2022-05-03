package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage{
	private Map<String, Resume> map = new HashMap<>();

	@Override
	protected String getSearchKey(String fullName) {
		String searchKey = null;
		for (Map.Entry<String, Resume> entry : map.entrySet()) {
			if (fullName.equals(entry.getValue().getUuid())) {
				searchKey = entry.getKey();
			}
		}
		return searchKey;
	}

	@Override
	protected void doUpdate(Resume r, Object searchKey) {
		map.put(r.getUuid(), r);
	}

	@Override
	protected boolean isExist(Object searchKey) {
		return searchKey != null;
	}

	@Override
	protected void doSave(Resume r, Object searchKey) {
		map.put(r.getFullName(), r);
	}

	@Override
	protected Resume doGet(Object searchKey) {
		return map.get(searchKey);
	}

	@Override
	protected void doDelete(Object searchKey) {
		map.remove(searchKey);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public List<Resume> doCopyAll() {
		return null;
	}

	@Override
	public int size() {
		return map.size();
	}
}
