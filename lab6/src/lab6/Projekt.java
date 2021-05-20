package lab6;

import java.util.ArrayList;
import java.util.HashMap;

public class Projekt {

	private static DBClient db;
	private static UI ui;

	public static void main(String[] args) {

		try {
			db = new DBClient();
			ui = new UI();

			Api.get("Wroclaw");

			
//			db.addNewWeather("Wroc³aw", 14);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

	}

}
