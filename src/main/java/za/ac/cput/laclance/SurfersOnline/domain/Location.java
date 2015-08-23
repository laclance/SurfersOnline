package za.ac.cput.laclance.SurfersOnline.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Embedded
    private BasicInfo basicInfo;

    @Embedded
    private Weather weather;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private List<SurfSpot> surfSpots;

    private Location() {
    }

    public Location(Builder builder) {
        id = builder.id;
        basicInfo = builder.basicInfo;
        weather = builder.weather;
        surfSpots = builder.surfSpots;
    }

    public Long getId() {
        return id;
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
        private Long id;
        private BasicInfo basicInfo;
        private Weather weather;
        private List<SurfSpot> surfSpots;

        public Builder(BasicInfo basicInfo) {
            this.basicInfo = basicInfo;
        }

        public Builder id(Long id) {
            this.id = id;
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

        public Builder copy(Location Location) {
            this.id = Location.getId();
            this.basicInfo = Location.getBasicInfo();
            this.weather = Location.getWeather();
            this.surfSpots = Location.getSurfSpots();
            return this;
        }

        public Location build() {
            return new Location(this);
        }
    }
}
