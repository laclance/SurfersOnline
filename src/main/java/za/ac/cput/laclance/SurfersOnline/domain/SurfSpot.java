package za.ac.cput.laclance.SurfersOnline.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class SurfSpot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BasicInfo basicInfo;

    @Embedded
    private Waves waves;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "surfspot_id")
    private List<Comment> comments;

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

    public List<Comment> getComments() {
        return comments;
    }

    public SurfSpot(Builder builder) {
        id = builder.id;
        basicInfo = builder.basicInfo;
        waves = builder.waves;
        comments = builder.comments;
    }

    public static class Builder {
        private Long id;
        private BasicInfo basicInfo;
        private Waves waves;
        private List<Comment> comments;

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

        public Builder comments(List<Comment> comments) {
            this.comments = comments;
            return this;
        }

        public Builder copy(SurfSpot surfspot) {
            this.id = surfspot.getId();
            this.basicInfo = surfspot.getBasicInfo();
            this.waves = surfspot.getWaves();
            this.comments = surfspot.getComments();
            return this;
        }

        public SurfSpot build() {
            return new SurfSpot(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SurfSpot)) return false;

        SurfSpot surfSpot = (SurfSpot) o;

        if (basicInfo != null ? !basicInfo.equals(surfSpot.basicInfo) : surfSpot.basicInfo != null) return false;
        if (comments != null ? !comments.equals(surfSpot.comments) : surfSpot.comments != null) return false;
        if (id != null ? !id.equals(surfSpot.id) : surfSpot.id != null) return false;
        if (waves != null ? !waves.equals(surfSpot.waves) : surfSpot.waves != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        return result;
    }

    @Override
    public String toString() {
        return "SurfSpot{" +
                "id=" + id +
                ", basicInfo=" + basicInfo +
                ", waves=" + waves +
                ", comments=" + comments +
                '}';
    }
}
