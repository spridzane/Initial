package jtm.activity13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class TeacherManager {

	protected Connection conn;
	private static Logger log = Logger.getLogger(TeacherManager.class);

	public TeacherManager() {
		// #1 When new TeacherManager is created, create connection to the
		// database server:
		// url =
		// "jdbc:mysql://localhost/?autoReconnect=true&useSSL=false&characterEncoding=utf8"
		// user = "root"
		// pass = "Student007"
		// Hints:
		// 1. Do not pass database name into url, because some statements
		// for tests need to be executed server-wise, not just database-wise.
		// 2. Set AutoCommit to false and use conn.commit() where necessary in
		// other methods

		if (conn == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost/?autoReconnect=true&useSSL=false", "root",
						"Student007");
				conn.setAutoCommit(false);
			} catch (Exception e) {
				log.debug(e.getMessage());
			}
		}

	}

	/**
	 * Returns a Teacher instance represented by the specified ID.
	 * 
	 * @param id the ID of teacher
	 * @return a Teacher object
	 */
	public Teacher findTeacher(int id) {
		// #2 Write an sql statement that searches teacher by ID.
		// If teacher is not found return Teacher object with zero or null in
		// its fields!
		// Hint: Because default database is not set in connection,
		// use full notation for table "database_activity.Teacher"

		Teacher teacher = new Teacher(0, null, null);

		try {
			conn.setAutoCommit(false);
			PreparedStatement pStmnt = null;
			pStmnt = conn.prepareStatement("SELECT * FROM database_activity.teacher where id = ?");
			pStmnt.setInt(1, id);
			conn.commit();
			ResultSet rs = pStmnt.executeQuery();
			if (rs.next()) {
				teacher = new Teacher(rs.getInt(1), rs.getString(2), rs.getString(3));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return teacher;
	}

	/**
	 * Returns a list of Teacher object that contain the specified first name and
	 * last name. This will return an empty List of no match is found.
	 * 
	 * @param firstName the first name of teacher.
	 * @param lastName  the last name of teacher.
	 * @return a list of Teacher object.
	 */
	public List<Teacher> findTeacher(String firstName, String lastName) {
		// TODO #3 Write an sql statement that searches teacher by first and
		// last name and returns results as ArrayList<Teacher>.
		// Note that search results of partial match
		// in form ...like '%value%'... should be returned
		// Note, that if nothing is found return empty list!
		List<Teacher> list = new ArrayList<>();
		PreparedStatement pStmnt;
		try {
			conn.setAutoCommit(false);
			pStmnt = conn.prepareStatement(
					"SELECT * FROM database_activity.teacher where firstname like ? and lastname like ?");
			pStmnt.setString(1, "%" + firstName + "%");
			pStmnt.setString(2, "%" + lastName + "%");
			conn.commit();
			ResultSet rs = pStmnt.executeQuery();
			while (rs.next()) {
				Teacher teacher = new Teacher(rs.getInt(1), rs.getString(2), rs.getString(3));
				list.add(teacher);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	/**
	 * Insert an new teacher (first name and last name) into the repository.
	 * 
	 * @param firstName the first name of teacher
	 * @param lastName  the last name of teacher
	 * @return true if success, else false.
	 */

	public boolean insertTeacher(String firstName, String lastName) {
		// #4 Write an sql statement that inserts teacher in database.
		try {
			conn.setAutoCommit(false);
			PreparedStatement pStmnt = conn
					.prepareStatement("INSERT INTO database_activity.teacher (firstname, lastname) VALUES (?, ?)");
			pStmnt.setString(1, firstName);
			pStmnt.setString(2, lastName);
			int a = pStmnt.executeUpdate();

			if (a == 1) {
				conn.commit();
				return true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Insert teacher object into database
	 * 
	 * @param teacher
	 * @return true on success, false on error (e.g. non-unique id)
	 */
	public boolean insertTeacher(Teacher teacher) {
		// #5 Write an sql statement that inserts teacher in database.

		try {
			conn.setAutoCommit(false);
			PreparedStatement pStmnt = conn.prepareStatement(
					"INSERT INTO database_activity.teacher (id, firstname, lastname) VALUES (?, ?, ?)");
			pStmnt.setInt(1, teacher.getId());
			pStmnt.setString(2, teacher.getFirstName());
			pStmnt.setString(3, teacher.getLastName());

			int a = pStmnt.executeUpdate();
			if (a == 1) {
				conn.commit();
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Updates an existing Teacher in the repository with the values represented by
	 * the Teacher object.
	 * 
	 * @param teacher a Teacher object, which contain information for updating.
	 * @return true if row was updated.
	 */
	public boolean updateTeacher(Teacher teacher) {
		boolean status = false;
		// #6 Write an sql statement that updates teacher information.
		PreparedStatement pStmnt;

		try {
			conn.setAutoCommit(false);
			pStmnt = conn
					.prepareStatement("UPDATE database_activity.teacher SET firstname = ?, lastname = ? where id = ? ");

			pStmnt.setString(1, teacher.getFirstName());
			pStmnt.setString(2, teacher.getLastName());

			pStmnt.setInt(3, teacher.getId());

			int a = pStmnt.executeUpdate();
			if (a == 1) {
				conn.commit();
				status = true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return status;
//		return false;
	}

	/**
	 * Delete an existing Teacher in the repository with the values represented by
	 * the ID.
	 * 
	 * @param id the ID of teacher.
	 * @return true if row was deleted.
	 */
	public boolean deleteTeacher(int id) {
		// #7 Write an sql statement that deletes teacher from database.
		try {
			conn.setAutoCommit(false);
			PreparedStatement pStmnt = conn.prepareStatement("Delete from database_activity.teacher where id = ?");
			pStmnt.setInt(1, id);
			int a = pStmnt.executeUpdate();

			if (a == 1) {
				conn.commit();
				return true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	public void closeConnecion() {
		// Close connection to the database server and reset conn object to null

		try {
			if (conn != null) {
				conn.close();
			}
			conn = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
