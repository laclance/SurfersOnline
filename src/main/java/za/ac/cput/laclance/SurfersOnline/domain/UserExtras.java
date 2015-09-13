package za.ac.cput.laclance.SurfersOnline.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserExtras implements Serializable {
    private String style;
    private String aboutMe;
    private String facebook;

    private UserExtras(){
    }

    public String getStyle() {
        return style;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public String getFacebook() {
        return facebook;
    }

    public UserExtras(Builder builder) {
        style = builder.style;
        aboutMe = builder.aboutMe;
        facebook = builder.facebook;
    }

    public static class Builder {
        private String style;
        private String aboutMe;
        private String facebook;

        public Builder (){}

        public Builder style(String style) {
            this.style = style;
            return this;
        }

        public Builder aboutMe(String aboutMe) {
            this.aboutMe = aboutMe;
            return this;
        }

        public Builder facebook(String facebook) {
            this.facebook = facebook;
            return this;
        }

        public Builder copy(UserExtras ue) {
            this.style = ue.style;
            this.aboutMe = ue.aboutMe;
            this.facebook = ue.facebook;
            return this;
        }

        public UserExtras build() {
            return new UserExtras(this);
        }
    }
}


