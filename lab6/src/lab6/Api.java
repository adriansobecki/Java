package lab6;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

public class Api {
	/**
	 * Metoda umo¿liwia pobranie danych z api pogodowego dla danego miasta
	 * @param city - nazwa miasta
	 * @return obiekt pogody dla miasta
	 * @throws Exception
	 */
	public static Weather get(String city) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
				"http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=08ba5dd31d0f7c44acd5ee3c8d364099"))
				.build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		return new Gson().fromJson(response.body().toString(), Weather.class);
	}
}
