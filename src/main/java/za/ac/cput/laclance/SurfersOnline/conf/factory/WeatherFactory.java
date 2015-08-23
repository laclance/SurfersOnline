package za.ac.cput.laclance.SurfersOnline.conf.factory;

import za.ac.cput.laclance.SurfersOnline.domain.Weather;

public class WeatherFactory {
    public static Weather createWeather(String conditions,
                                     int temp,
                                     int clouds,
                                     int rain,
                                     int humidity) {
        Weather weather = new Weather
                .Builder()
                .conditions(conditions)
                .temp(temp)
                .clouds(clouds)
                .rain(rain)
                .humidity(humidity)
                .build();

        return weather;
    }
}
