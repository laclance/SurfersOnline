package za.ac.cput.laclance.SurfersOnline.domain;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class Weather implements Serializable {
    private String weatherConditions;
    private int temp;
    private int clouds;
    private int rain;
    private int humidity;

    private Weather() {
    }

    public Weather(Builder builder) {
        weatherConditions = builder.weatherConditions;
    }

    public String getConditions() {
        return weatherConditions;
    }

    public int getTemp() {
        return temp;
    }

    public int getClouds() {
        return clouds;
    }

    public int getRain() {
        return rain;
    }

    public int getHumidity() {
        return humidity;
    }

    public static class Builder {
        private String weatherConditions;
        private int temp;
        private int clouds;
        private int rain;
        private int humidity;

        public Builder () {
        }

        public Builder conditions(String conditions) {
            this.weatherConditions = conditions;
            return this;
        }

        public Builder temp(int temp) {
            this.temp = temp;
            return this;
        }

        public Builder clouds(int clouds) {
            this.clouds = clouds;
            return this;
        }

        public Builder rain(int rain) {
            this.rain = rain;
            return this;
        }

        public Builder humidity(int humidity) {
            this.humidity = humidity;
            return this;
        }

        public Builder copy(Weather weather) {
            this.weatherConditions = weather.getConditions();
            this.temp = weather.getTemp();
            this.clouds = weather.getClouds();
            this.rain = weather.getRain();
            this.humidity = weather.getHumidity();
            return this;
        }

        public Weather build() {
            return new Weather(this);
        }
    }
}

