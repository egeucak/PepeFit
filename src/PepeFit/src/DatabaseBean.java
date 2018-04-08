


import com.mysql.jdbc.PreparedStatement;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.sql.*;
import java.lang.*;

@ManagedBean
@RequestScoped

public class DatabaseBean {

	public String a() {
		return "FCCC";
	}

	public void listables() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("Select * From Member");
        while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString(4));}

	}

	public Connection getConnection() {
		String instanceConnectionName = "pepefit-1:us-east1:pepefit-mysql-1";

		// TODO: fill this in
		// The database from which to list tables.
		String databaseName = "pepefit";

		String username = "root";

		// TODO: fill this in
		// This is the password that was set via the Cloud Console or empty if never set
		// (not recommended).
		String password = "pepefithacettepe12345";

		Connection con = null;

		try {
			
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			String jdbcUrl = String.format(
					"jdbc:mysql://google/%s?cloudSqlInstance=%s&"
							+ "socketFactory=com.google.cloud.sql.mysql.SocketFactory",
					databaseName, instanceConnectionName);

			con = DriverManager.getConnection(jdbcUrl, username, password);
			System.out.println("Connection completed.");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());


		}
        return con;

	}


}
