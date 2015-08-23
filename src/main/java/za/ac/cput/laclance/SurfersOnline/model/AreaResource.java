package za.ac.cput.laclance.SurfersOnline.model;

import org.springframework.hateoas.ResourceSupport;
import za.ac.cput.laclance.SurfersOnline.domain.BasicInfo;
import za.ac.cput.laclance.SurfersOnline.domain.Location;
import za.ac.cput.laclance.SurfersOnline.domain.User;

import java.util.List;

public class AreaResource extends ResourceSupport {
    private Long resid;
    private BasicInfo basicInfo;
    private List<Location> locations;

    private AreaResource() {
    }

    public AreaResource(Builder builder) {
        resid = builder.resid;
        basicInfo = builder.basicInfo;
        locations = builder.locations;
    }

    public Long getResid() {
        return resid;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public static class Builder {
        private Long resid;
        private BasicInfo basicInfo;
        private List<Location> locations;

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

        public Builder locations(List<Location> locations) {
            this.locations = locations;
            return this;
        }

        public Builder copy(AreaResource area) {
            this.resid = area.getResid();
            this.basicInfo = area.getBasicInfo();
            this.locations = area.getLocations();
            return this;
        }

        public AreaResource build() {
            return new AreaResource(this);
        }
    }
}
