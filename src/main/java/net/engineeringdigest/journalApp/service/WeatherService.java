package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;


    public WeatherResponse getWeather(String city) {
        String apiTemplate = appCache.appCache.get(AppCache.keys.WEATHER_API.toString());
        if (apiTemplate == null || apiTemplate.isEmpty()) {
            throw new RuntimeException("Weather API URL not found in AppCache");
        }

        // 2️⃣ Replace placeholders
        String finalAPI = apiTemplate
                .replace(Placeholders.CITY, city)
                .replace(Placeholders.API_KEY, apiKey);

        try {
            // 3️⃣ Call Weather API
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);

            if (response.getBody() != null) {
                return response.getBody();
            } else {
                throw new RuntimeException("Failed to fetch weather: HTTP " + response.getStatusCode());
            }

        } catch (Exception e) {
            throw new RuntimeException("Exception while calling Weather API: " + e.getMessage(), e);
        }
    }
}
