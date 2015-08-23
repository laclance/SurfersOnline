package za.ac.cput.laclance.SurfersOnline.model;

import org.springframework.hateoas.ResourceSupport;
import za.ac.cput.laclance.SurfersOnline.domain.User;
import za.ac.cput.laclance.SurfersOnline.domain.UserBasics;
import za.ac.cput.laclance.SurfersOnline.domain.UserContact;
import za.ac.cput.laclance.SurfersOnline.domain.UserExtras;
import java.util.Map;

public class UserResource extends ResourceSupport {
    private Long resid;
    private String username;
    private String password;

    private UserBasics userbasics;
    private UserContact usercontact;
    private UserExtras userextras;

    private UserResource() {}

    public Long getResid() {
        return resid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserBasics getUserBasics() {
        return userbasics;
    }

    public UserContact getUserContact() {
        return usercontact;
    }

    public UserExtras getUserExtras() {
        return userextras;
    }

    public UserResource(Builder builder) {
        username = builder.username;
        password = builder.password;
        userbasics = builder.userbasics;
        usercontact = builder.usercontact;
        userextras = builder.userextras;
    }

    public static class Builder {
        private Long resid;
        private String username;
        private String password;
        private UserBasics userbasics;
        private UserContact usercontact;
        private UserExtras userextras;

        public Builder(Map<String,String> values) {
            this.username = values.get("username");
            this.password = values.get("password");
        }

        public Builder resid(Long resid) {
            this.resid = resid;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder userbasics(UserBasics userbasics) {
            this.userbasics = userbasics;
            return this;
        }

        public Builder usercontact(UserContact usercontact) {
            this.usercontact = usercontact;
            return this;
        }

        public Builder userextras(UserExtras userextras) {
            this.userextras = userextras;
            return this;
        }

        public Builder copy(UserResource user) {
            this.resid = user.getResid();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.userbasics = user.getUserBasics();
            this.usercontact = user.getUserContact();
            this.userextras = user.getUserExtras();
            return this;
        }

        public UserResource build() {
            return new UserResource(this);
        }
    }
}
