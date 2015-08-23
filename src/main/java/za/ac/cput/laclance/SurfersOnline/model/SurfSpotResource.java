package za.ac.cput.laclance.SurfersOnline.model;

import org.springframework.hateoas.ResourceSupport;
import za.ac.cput.laclance.SurfersOnline.domain.BasicInfo;
import za.ac.cput.laclance.SurfersOnline.domain.Waves;

public class SurfSpotResource extends ResourceSupport {
    private Long resid;
    private BasicInfo basicInfo;
    private Waves waves;

    private SurfSpotResource () {}

    public Long getResid() {
        return resid;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public Waves getWaves() {
        return waves;
    }

    public SurfSpotResource(Builder builder) {
        resid = builder.resid;
        basicInfo = builder.basicInfo;
        waves = builder.waves;
    }

    public static class Builder {
        private Long resid;
        private BasicInfo basicInfo;
        private Waves waves;

        public Builder (BasicInfo basicInfo) {
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

        public Builder waves(Waves waves) {
            this.waves = waves;
            return this;
        }

        public Builder copy(SurfSpotResource surfspot) {
            this.resid = surfspot.getResid();
            this.basicInfo = surfspot.getBasicInfo();
            this.waves = surfspot.getWaves();
            return this;
        }

        public SurfSpotResource build() {
            return new SurfSpotResource(this);
        }
    }
}
