package za.ac.cput.laclance.SurfersOnline.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class SurfSpot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BasicInfo basicInfo;

    @Embedded
    private Waves waves;

    private SurfSpot () {}

    public Long getId() {
        return id;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public Waves getWaves() {
        return waves;
    }

    public SurfSpot(Builder builder) {
        id = builder.id;
        basicInfo = builder.basicInfo;
        waves = builder.waves;
    }

    public static class Builder {
        private Long id;
        private BasicInfo basicInfo;
        private Waves waves;

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

        public Builder waves(Waves waves) {
            this.waves = waves;
            return this;
        }

        public Builder copy(SurfSpot surfspot) {
            this.id = surfspot.getId();
            this.basicInfo = surfspot.getBasicInfo();
            this.waves = surfspot.getWaves();
            return this;
        }

        public SurfSpot build() {
            return new SurfSpot(this);
        }
    }
}
