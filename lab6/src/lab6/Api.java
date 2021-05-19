package lab6;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;
//import com.google.gson.Gson;

public class Api {
	public static void get(String city) throws Exception {
	    HttpClient client = HttpClient.newHttpClient();
	    HttpRequest request = HttpRequest.newBuilder()
	          .uri(URI.create("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=08ba5dd31d0f7c44acd5ee3c8d364099"))
	          .build();

	    HttpResponse<String> response =
	          client.send(request, BodyHandlers.ofString());

	    System.out.println(response.body());
	    String json=response.body().toString();

        //Weather data = new Gson().fromJson(json, Weather.class);

        // Show it.
        //System.out.println(data);
	}
}
