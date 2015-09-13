package za.ac.cput.laclance.SurfersOnline.domain;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class Waves implements Serializable {
    private String wavesConditions;
    private int swellSize;
    private String swellDir;
    private int swellPulse;
    private String high;
    private String low;
    private int waterTemp;

    private Waves() {
    }

    public Waves(Builder builder) {
        wavesConditions = builder.wavesConditions;
        swellSize = builder.swellSize;
        swellDir = builder.swellDir;
        swellPulse = builder.swellPulse;
        high = builder.high;
        low = builder.low;
        waterTemp = builder.waterTemp;
    }

    public String getConditions() {
        return wavesConditions;
    }

    public int getSwellSize() {
        return swellSize;
    }

    public String getSwellDir() {
        return swellDir;
    }

    public int getSwellPulse() {
        return swellPulse;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public int getWaterTemp() {
        return waterTemp;
    }

    public static class Builder {
        private String wavesConditions;
        private int swellSize;
        private String swellDir;
        private int swellPulse;
        private String high;
        private String low;
        private int waterTemp;

        public Builder () {
        }

        public Builder conditions(String conditions) {
            this.wavesConditions = conditions;
            return this;
        }

        public Builder swellSize(int swellSize) {
            this.swellSize = swellSize;
            return this;
        }

        public Builder swellDir(String swellDir) {
            this.swellDir = swellDir;
            return this;
        }

        public Builder swellPulse(int swellPulse) {
            this.swellPulse = swellPulse;
            return this;
        }

        public Builder high(String high) {
            this.high = high;
            return this;
        }

        public Builder low(String low) {
            this.low = low;
            return this;
        }

        public Builder waterTemp(int waterTemp) {
            this.waterTemp = waterTemp;
            return this;
        }

        public Builder copy(Waves Waves) {
            this.wavesConditions = Waves.getConditions();
            this.swellSize = Waves.getSwellSize();
            this.swellDir = Waves.getSwellDir();
            this.swellPulse = Waves.getSwellPulse();
            this.high = Waves.getHigh();
            this.low = Waves.getLow();
            this.waterTemp = Waves.getWaterTemp();
            return this;
        }

        public Waves build() {
            return new Waves(this);
        }
    }
}
