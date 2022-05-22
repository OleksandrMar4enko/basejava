package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.storage.serializer.XmlStreamSerializer;

public class SqlStorageTest extends AbstractStorageTest {

	public SqlStorageTest() {
		super(Config.get().getStorage());
	}
}
