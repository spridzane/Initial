package jtm.activity14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class StudentManager {

	protected Connection conn;
	private static Logger log = Logger.getLogger(StudentManager.class);

	public StudentManager() {
		// #1 When new StudentManager is created, create connection to the
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
	 * Returns a Student instance represented by the specified ID.
	 * 
	 * @param id the ID of student
	 * @return a Student object
	 */
	public Student findStudent(int id) {
		// #2 Write an sql statement that searches student by ID.
		// If student is not found return Student object with zero or null in
		// its fields!
		// Hint: Because default database is not set in connection,
		// use full notation for table "database_activity.Student"

		Student student = new Student(0, null, null);
		try {
			PreparedStatement pStmnt = null;
			pStmnt = conn.prepareStatement("SELECT * FROM database_activity.student where id = ?");
			pStmnt.setInt(1, id);
			conn.commit();
			ResultSet rs = pStmnt.executeQuery();
			if (rs.next())
				student = new Student(rs.getInt(1), rs.getString(2), rs.getString(3));
			return student;

		} catch (SQLException e) {
			log.debug(e.getMessage());
		}
		return student;

	}

	/**
	 * Returns a list of Student object that contain the specified first name and
	 * last name. This will return an empty List of no match is found.
	 * 
	 * @param firstName the first name of student.
	 * @param lastName  the last name of student.
	 * @return a list of Student object.
	 */
	public List<Student> findStudent(String firstName, String lastName) {
		// #3 Write an sql statement that searches student by first and
		// last name and returns results as ArrayList<Student>.
		// Note that search results of partial match
		// in form ...like '%value%'... should be returned
		// Note, that if nothing is found return empty list!
		List<Student> list = new ArrayList<Student>();

		try {
			conn.setAutoCommit(false);
			PreparedStatement pStmnt = conn.prepareStatement(
					"SELECT * FROM database_activity.student where firstname like ? and lastname like ?");
			pStmnt.setString(1, "%" + firstName + "%");
			pStmnt.setString(2, "%" + lastName + "%");
			conn.commit();
			ResultSet rs = pStmnt.executeQuery();
			while (rs.next()) {
				list.add(new Student(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname")));
			}
			rs.close();
			pStmnt.close();

		} catch (SQLException e) {
			log.debug(e.getMessage());
		}

		return list;

	}

	/**
	 * This method will attempt to insert an new student (first name and last name)
	 * into the repository.
	 * 
	 * @param firstName the first name of student
	 * @param lastName  the last name of student
	 * @return true if insert, else false.
	 */

	public boolean insertStudent(String firstName, String lastName) {
		// #4 Write an sql statement that inserts student in database.
		try {

			PreparedStatement pStmnt = conn
					.prepareStatement("INSERT INTO database_activity.student (firstname, lastname) VALUES (?, ?)");
			pStmnt.setString(1, firstName);
			pStmnt.setString(2, lastName);
			pStmnt.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			log.debug(e.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * Try to insert Student in database
	 * 
	 * @param student
	 * @return true on success, false on error (e.g. non-unique id)
	 */
	public boolean insertStudent(Student student) {
		// #5 Write an sql statement that inserts student in database.
		boolean status = false;
		try {
			PreparedStatement pStmnt = conn.prepareStatement(
					"INSERT INTO database_activity.student (id, firstname, lastname) VALUES (?, ?, ?)");
			pStmnt.setString(1, Integer.toString(student.getId()));
			pStmnt.setString(2, student.getFirstName());
			pStmnt.setString(3, student.getLastName());

			int rows = pStmnt.executeUpdate();
			conn.commit();
			if (rows == 1) {

				status = true;
			}

		} catch (SQLException e) {
			log.debug(e.getMessage());
			status = false;
		}

		return status;
	}

	/**
	 * Updates an existing Student in the repository with the values represented by
	 * the Student object.
	 * 
	 * @param student a Student object, which contain information for updating.
	 * @return true if row was updated.
	 */
	public boolean updateStudent(Student student) {
		// #6 Write an sql statement that updates student information.
		boolean status = false;

		try {

			PreparedStatement pStmnt = conn
					.prepareStatement("UPDATE database_activity.student SET firstname = ?, lastname = ? where id = ? ");

			pStmnt.setString(1, student.getFirstName());
			pStmnt.setString(2, student.getLastName());

			pStmnt.setInt(3, student.getId());

			int rows = pStmnt.executeUpdate();
			conn.commit();
			pStmnt.close();
			if (rows == 1) {

				status = true;
			}

		} catch (Exception e) {

			log.debug(e.getMessage());
			status = false;
		}
		return status;
	}

	/**
	 * Delete an existing Student in the repository with the values represented by
	 * the ID.
	 * 
	 * @param id the ID of student.
	 * @return true if row was deleted.
	 */
	public boolean deleteStudent(int id) {
		// #7 Write an sql statement that deletes student from database.
		boolean status = false;
		;
		try {

			PreparedStatement pStmnt = conn.prepareStatement("Delete from database_activity.student where id = ?");
			pStmnt.setInt(1, id);
			int rows = pStmnt.executeUpdate();
			conn.commit();
			pStmnt.close();
			if (rows == 1) {
				status = true;
			}

		} catch (SQLException e) {
			log.debug(e.getMessage());

		}

		return status;
	}

	public void closeConnecion() {
		// Close connection if and reset it to release connection to the
		// database server
		try {
			if (conn != null) {
				conn.close();
			}
			conn = null;
		} catch (SQLException e) {

			log.debug(e.getMessage());
		}
	}
}
