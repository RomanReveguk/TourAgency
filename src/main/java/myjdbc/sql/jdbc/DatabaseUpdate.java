package myjdbc.sql.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUpdate {

	// ============ Queries
	private static final String INSERT_USERS = "insert into users(user_id, user_name, user_surname, user_date_of_birth, mail,password,role) values(?,?,?,?,?,?,?)";
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/touragency";
	private static final String PARAMS = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8";
	public static final String USER = "root";
	public static final String PASSWORD = "root";

	// ============ INSERT_USER in TABLE USERS=====================================================
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection conn = DriverManager.getConnection(URL + PARAMS, USER, PASSWORD);
				PreparedStatement statement = conn.prepareStatement(INSERT_USERS);) {
			statement.setInt(1, 10);
			statement.setString(2, "Fedya");
			statement.setString(3, "Fedotov");
			statement.setString(4, "2001-10.10");
			statement.setString(5, "fedotov@gmail.com");
			statement.setString(6, "123");
			statement.setString(7, "client");
			statement.executeUpdate();
			
//===================WHY addBAtch==========================			
//			statement.setInt(1, 4);
//			statement.setString(2, "misha");
//			statement.addBatch();
//			statement.setInt(1, 5);
//			statement.setString(2, "grisha");
//			statement.addBatch();
//			statement.executeBatch();
			
		}

	}

}
