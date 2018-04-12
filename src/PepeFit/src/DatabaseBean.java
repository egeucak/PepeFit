


import com.mysql.jdbc.PreparedStatement;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.xml.crypto.Data;

import java.sql.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import static java.util.Arrays.asList;

@ManagedBean
@RequestScoped

public class DatabaseBean {
	private String ss = null;
	private String bb = null;

	public void setSS(String p) {
		this.ss = p;
	}

	public void setBB(String p) {
		this.bb = p;
	}

	public void km() {
		System.out.println(ss + " " + bb);
	}


	public String a() {
		return "FCCC";
	}

	private Connection dbConnection = null;

	public DatabaseBean() throws SQLException {
		dbConnection = dbConnection();
		dbConnection.setAutoCommit(false);
		if (dbConnection == null) {
			System.out.println("Cannot connect to database (Maybe from Auth )");
		} else {
			System.out.println("Connected to the DATABASE");
		}
	}


//	public void listables() throws SQLException {
//
//		Connection connection = getConnection();
//        if(connection != null){
//			Statement statement = connection.createStatement();
//
//			ResultSet resultSet = statement.executeQuery("Select * From Member");
//			while (resultSet.next()) {
//				System.out.println(resultSet.getString(1));
//				System.out.println(resultSet.getString(2));
//				System.out.println(resultSet.getString(3));
//				System.out.println(resultSet.getString(4));
//			}
//		}else{
//        	System.out.println("SİKEM AQ");
//		}
//
//	}



	public Connection dbConnection() {
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
							+ "socketFactory=com.google.cloud.sql.mysql.SocketFactory&relaxAutoCommit=true",
					databaseName, instanceConnectionName);

			con = DriverManager.getConnection(jdbcUrl, username, password);
			System.out.println("Connection completed.");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());


		}
		return con;

	}

	/**
	 * This function do the binding part of a SQL query.
	 * Just simple put 'vararg' (infinite arg) then it creates the binded query.
	 *
	 * @param prepStmt PreparedStatement instance for a DB query
	 * @param vars     Variables to bind into SQL query.
	 * @return PreparedStatement
	 */
	public PreparedStatement bindvars(PreparedStatement prepStmt, Object... vars) {

		try {
			int i = 1;

			for (Object var : vars) {

				// GELECEKTE OLABİLECEK NULL OLAYLARINA BAK. OLMADI GELMEDEN ÖNCE KONTROL ETMEYE ÇALIŞ
				if (var.getClass() == Integer.class) {
					int var_to_int = (Integer) var;
					prepStmt.setInt(i, var_to_int);
				} else if (var.getClass() == String.class) {
					String var_to_str = var.toString();
					prepStmt.setString(i, var_to_str);
				} else if (var.getClass() == Long.class) {
					Long var_to_long = (Long) var;
					prepStmt.setLong(i, var_to_long);
				} else if (var.getClass() == Date.class) {
					Date var_to_Date = (Date) var;
					prepStmt.setDate(i, var_to_Date);
				}
				i++;
			}

		} catch (SQLException e) {
			System.out.println("Exception on BindVars ERROR " + e.getMessage());
		}

		return prepStmt;
	}

	/**
	 * While writing queries, don't use directly
	 * "Insert into table values("...","...");" (in the start and end " and ' can both work)
	 * Instead use like this;
	 * Insert into table values(\"..\",\",..\");
	 *
	 * Because there can be EL Unbalanced Exception.
	 *
	 * @param sql_query Unbounded sql query
	 * @param exec_type select = 0 | (insert, update, delete ..)  = 1
	 * @param vars      Bind variables for completing the query
	 * @return ResultSet will use in execute_fetch_all() for taking tables from DB
	 * @throws SQLException
	 */
	public ResultSet execute(String sql_query, int exec_type, Object... vars) throws SQLException {

//        if(dbConnection != null){
//            Statement statement = dbConnection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery(sql_query);
//
//        }else{
//            System.out.println("SİKEM AQ");
//        }


//		for(int i=0;i<vars.length;i++){
//			prepStmt.setString(i+1,vars[i]);
//		}



		ResultSet resultSet = null;

		PreparedStatement prepStmt = null;

		try {
			System.out.println("SQL Statement:\n\t" + sql_query);
			prepStmt = (PreparedStatement) dbConnection.prepareStatement(sql_query);

			// Sıkıntı cıkarsa prepStmt = bindvars(prepStmt,vars) dene
			if (vars[0] != null){
				bindvars(prepStmt, vars);
			}


			System.out.println("After Statement:\n\t" + prepStmt.toString());


			if (exec_type == -1) {
				// NORMAL QUERIES. SELECT * FROM ...
				resultSet = prepStmt.executeQuery();

			} else if (exec_type == 1) {
				// INSERT, UPDATE OR DELETE . We don't have any returns
				prepStmt.executeUpdate();
				dbConnection.commit();
			}

		} catch (SQLException e) {

			if (dbConnection != null) {
				try {
					System.err.print("Transaction is being rolled back ERROR " + e.getMessage());
					dbConnection.rollback();
				} catch (SQLException excep) {
					System.out.println("Error occured while rollbacking ERROR " + excep.getMessage());
				}
			}
		}

		return resultSet;


	}

	public ArrayList<LinkedHashMap<String,Object>> execute_fetch_all(String sql_query, int exec_type, Object... vars) throws SQLException {
		ArrayList<LinkedHashMap<String,Object>> query_results = new ArrayList<LinkedHashMap<String, Object>>();
//
//        TreeMap<String, HashMap<String, String>> selectResult =
//                new TreeMap<String, HashMap<String, String>>();
		ResultSet resultSet = execute(sql_query, exec_type, vars);

		ResultSetMetaData rsmd = resultSet.getMetaData();
		int returned_coloumn_count = rsmd.getColumnCount();

		while (resultSet.next()) {
			LinkedHashMap<String,Object> row = new LinkedHashMap<String, Object>();
			for (int i = 1;i<=returned_coloumn_count;i++){
				String column_name = rsmd.getColumnName(i).toUpperCase();

				row.put(column_name,resultSet.getObject(i));
			}
			query_results.add(row);
		}
		int p = 0;

		while(p < query_results.size()){
			System.out.println(query_results.get(p++));
		}


		return query_results;
	}


	public void commit_trans() throws SQLException {
		this.dbConnection.commit();
	}

	public void destruct_connection() {
		try {
			if (dbConnection != null) {
				dbConnection.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}


}
