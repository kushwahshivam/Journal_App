package net.engineeringdigest.journalApp.api.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WeatherResponse {
    private Current current;

    @Getter
    @Setter
    @ToString
    public static class Current {
        private double temp_c;
    }

}
