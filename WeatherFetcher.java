import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

// Gson imports to parse JSON response
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WeatherFetcher {
    // Replace with your own OpenWeatherMap API key
    private static final String API_KEY = "e5e0dabec8b199efaaae2c27f923ba12";
    private static final String GEO_URL = "http://api.openweathermap.org/geo/1.0/direct";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Input city and country
            System.out.print("Enter city name: ");
            String city = scanner.nextLine().trim();
            System.out.print("Enter country code (e.g., IN for India): ");
            String country = scanner.nextLine().trim();

            // Encode city and country for URL safety (handles spaces, etc.)
            String query = URLEncoder.encode(city + "," + country, StandardCharsets.UTF_8);

            // Build the Geocoding API URL
            String geoUrl = String.format("%s?q=%s&limit=1&appid=%s", GEO_URL, query, API_KEY);

            // Fetch coordinates
            String geoResponse = sendGetRequest(geoUrl);
            JsonArray geoArray = JsonParser.parseString(geoResponse).getAsJsonArray();
            if (geoArray.size() == 0) {
                System.out.println("City not found.");
                return;
            }

            JsonObject location = geoArray.get(0).getAsJsonObject();
            double lat = location.get("lat").getAsDouble();
            double lon = location.get("lon").getAsDouble();

            // Build Weather API URL
            String weatherUrl = String.format("%s?lat=%.4f&lon=%.4f&appid=%s&units=metric", WEATHER_URL, lat, lon, API_KEY);

            // Fetch weather data
            String weatherResponse = sendGetRequest(weatherUrl);
            JsonObject weatherJson = JsonParser.parseString(weatherResponse).getAsJsonObject();

            JsonObject main = weatherJson.getAsJsonObject("main");
            JsonObject weather = weatherJson.getAsJsonArray("weather").get(0).getAsJsonObject();

            // Display results
            System.out.println("\nWeather Info for " + city + ":");
            System.out.println("Temperature: " + main.get("temp").getAsDouble() + " °C");
            System.out.println("Humidity: " + main.get("humidity").getAsInt() + " %");
            System.out.println("Description: " + weather.get("description").getAsString());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Sends GET request to provided URL string using URI to avoid deprecation
    private static String sendGetRequest(String urlStr) throws Exception {
        URI uri = new URI(urlStr);
        URL url = uri.toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
        }

        return response.toString();
    }
}
