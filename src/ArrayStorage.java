/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int capacity = 3;
    Resume[] storage = new Resume[capacity];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
                storage[i] = null;
            }
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        Resume resume = null;
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " is not exist");
        } else {
            resume = storage[index];
        }
        return resume;
    }

    void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " is not exist");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return storage;
    }

    int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid == storage[i].uuid) {
                return i;
            }
        }
        return -1;
    }
}
