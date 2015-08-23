package za.ac.cput.laclance.SurfersOnline.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Area implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private BasicInfo basicInfo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "area_id")
    private List<Location> locations;

    private Area() {
    }

    public Long getId() {
        return id;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public Area(Builder builder) {
        id = builder.id;
        basicInfo = builder.basicInfo;
        locations = builder.locations;
    }

    public static class Builder {
        private Long id;
        private BasicInfo basicInfo;
        private List<Location> locations;

        public Builder (BasicInfo basicInfo) {
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

        public Builder locations(List<Location> locations) {
            this.locations = locations;
            return this;
        }

        public Builder copy(Area area) {
            this.id = area.getId();
            this.basicInfo = area.getBasicInfo();
            this.locations = area.getLocations();
            return this;
        }

        public Area build() {
            return new Area(this);
        }
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", basicInfo=" + basicInfo.getName() +
                ", locations=" + locations +
                '}';
    }
}
