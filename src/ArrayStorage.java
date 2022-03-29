/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[1000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
                storage[i] = null;
            }
        size = 0;
    }

    void save(Resume r) {
        if (get(r.uuid) == null) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Resume " + r + " is already exist");
        }
    }

    void update(Resume resume) {
        int index = getIndex(resume.uuid);
        if (get(resume.uuid) == null) {
            System.out.println("Resume " + resume.uuid + " is not exist");
        } else {
            storage[index] = resume;
        }
    }

    Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " is not exist");
            return null;
        }
        return storage[index];

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
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
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
