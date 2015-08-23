package za.ac.cput.laclance.SurfersOnline.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BasicInfo implements Serializable {
    private String name;
    private String description;

    public BasicInfo(Builder builder) {
        name = builder.name;
        description = builder.description;
    }

    private BasicInfo() {}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static class Builder {
        private String name;
        private String description;

        public Builder(String name) {
            this.name = name;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder copy(BasicInfo bi) {
            this.name = bi.name;
            this.description = bi.description;
            return this;
        }

        public BasicInfo build() {
            return new BasicInfo(this);
        }
    }

    @Override
    public String toString() {
        return "BasicInfo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

