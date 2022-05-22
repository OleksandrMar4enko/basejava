package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.ConnectionFactory;
import ru.javawebinar.basejava.sql.SqlExecutor;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
	public final SqlHelper sqlHelper;

	public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
		sqlHelper = new SqlHelper(new ConnectionFactory() {
			@Override
			public Connection getConnection() throws SQLException {
				return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			}
		});
	}

	@Override
	public void clear() {
		sqlHelper.execute("DELETE FROM resume");
	}

	@Override
	public Resume get(String uuid) {
		return sqlHelper.execute("SELECT * FROM resume r WHERE r.uuid =?", new SqlExecutor<Resume>() {
			@Override
			public Resume execute(PreparedStatement ps) throws SQLException {
				ps.setString(1, uuid);
				ResultSet rs = ps.executeQuery();
				if (!rs.next()) {
					throw new NotExistStorageException(uuid);
				}
				return new Resume(uuid, rs.getString("full_name"));
			}
		});
	}

	@Override
	public void update(Resume r) {
		sqlHelper.execute("UPDATE resume r SET full_name = ? WHERE r.uuid = ?", new SqlExecutor<Void>() {
			@Override
			public Void execute(PreparedStatement ps) throws SQLException {
				ps.setString(1, r.getFullName());
				ps.setString(2, r.getUuid());
				ps.executeUpdate();
				return null;
			}
		});
	}

	@Override
	public void save(Resume r) {
		sqlHelper.<Void>execute("INSERT INTO resume (uuid, full_name) VALUES (?,?)", new SqlExecutor<Void>() {
			@Override
			public Void execute(PreparedStatement ps) throws SQLException {
				ps.setString(1, r.getUuid());
				ps.setString(2, r.getFullName());
				ps.execute();
				return null;
			}
		});
	}

	@Override
	public void delete(String uuid) {
		sqlHelper.execute("DELETE FROM resume WHERE uuid = ?", new SqlExecutor<Void>() {
			@Override
			public Void execute(PreparedStatement ps) throws SQLException {
				ps.setString(1, uuid);
				ps.execute();
				return null;
			}
		});
	}

	@Override
	public List<Resume> getAllSorted() {
		return sqlHelper.execute("SELECT * FROM resume r ORDER BY full_name, uuid", ps -> {
			ResultSet rs = ps.executeQuery();
			List<Resume> resumes = new ArrayList<>();
			while (rs.next()) {
				resumes.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
			}
			return resumes;
		});
	}

	@Override
	public int size() {
		return sqlHelper.execute("SELECT count(*) FROM resume", ps -> {
			ResultSet rs = ps.executeQuery();
			return rs.next() ? rs.getInt(1) : 0;
		});
	}


}