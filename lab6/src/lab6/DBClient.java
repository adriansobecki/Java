package lab6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class DBClient {

	private String url = "jdbc:mysql://localhost:3306/java_project?autoReconnect=true&useSSL=false";
	private String user = "root";
	private String passwd = "";

	public ArrayList<HashMap<String, String>> getWeathersForCity(String cityName) throws SQLException {
		Connection db = DriverManager.getConnection(url, user, passwd);

		String sql = "select * from weathers where city_name = '" + cityName + "'";
		Statement stm = db.createStatement();
		ResultSet res = stm.executeQuery(sql);

		ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();

		while (res.next()) {
			HashMap<String, String> oneRow = new HashMap<String, String>();
			oneRow.put("city_name", res.getString("city_name"));
			oneRow.put("temp", res.getString("temp"));
			oneRow.put("create_time", res.getString("create_time"));

			results.add(oneRow);
		}

		return results;
	}

	public void addNewWeather(String cityName, float temp) throws SQLException {
		Connection db = DriverManager.getConnection(url, user, passwd);

		String sql = "INSERT INTO `weathers`(`city_name`, `temp`) VALUES ('" + cityName + "', " + temp + ")";
		Statement stm = db.createStatement();
		stm.execute(sql);
	}
}
