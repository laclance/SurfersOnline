package za.ac.cput.laclance.SurfersOnline.model;

import org.springframework.hateoas.ResourceSupport;
import za.ac.cput.laclance.SurfersOnline.domain.BasicInfo;
import za.ac.cput.laclance.SurfersOnline.domain.SurfSpot;
import za.ac.cput.laclance.SurfersOnline.domain.Weather;

import java.util.List;

public class LocationResource extends ResourceSupport {
    private Long resid;
    private BasicInfo basicInfo;
    private Weather weather;
    private List<SurfSpot> surfSpots;

    private LocationResource() {
    }

    public LocationResource(Builder builder) {
        resid = builder.resid;
        basicInfo = builder.basicInfo;
        weather = builder.weather;
        surfSpots = builder.surfSpots;
    }

    public Long getResid() {
        return resid;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public Weather getWeather() {
        return weather;
    }

    public List<SurfSpot> getSurfSpots() {
       return surfSpots;
    }


    public static class Builder {
        private Long resid;
        private BasicInfo basicInfo;
        private Weather weather;
        private List<SurfSpot> surfSpots;

        public Builder(BasicInfo basicInfo) {
            this.basicInfo = basicInfo;
        }

        public Builder resid(Long resid) {
            this.resid = resid;
            return this;
        }

        public Builder basicInfo(BasicInfo basicInfo) {
            this.basicInfo = basicInfo;
            return this;
        }

        public Builder weather(Weather weather) {
            this.weather = weather;
            return this;
        }

        public Builder surfSpots(List<SurfSpot> surfSpots) {
            this.surfSpots = surfSpots;
            return this;
        }


        public Builder copy(LocationResource location) {
            this.resid = location.getResid();
            this.basicInfo = location.getBasicInfo();
            this.weather = location.getWeather();
            this.surfSpots = location.getSurfSpots();
            return this;
        }

        public LocationResource build() {
            return new LocationResource(this);
        }
    }
}
