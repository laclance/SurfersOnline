package za.ac.cput.laclance.SurfersOnline.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    @Embedded
    private UserBasics userbasics;
    @Embedded
    private UserContact usercontact;
    @Embedded
    private UserExtras userextras;

    private User() {}

    public Long getId() {
        return id;
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

    public User(Builder builder) {
        id = builder.id;
        username = builder.username;
        password = builder.password;
        userbasics = builder.userbasics;
        usercontact = builder.usercontact;
        userextras = builder.userextras;
    }

    public static class Builder {
        private Long id;
        private String username;
        private String password;
        private UserBasics userbasics;
        private UserContact usercontact;
        private UserExtras userextras;

        public Builder(Map<String,String> values) {
            this.username = values.get("username");
            this.password = values.get("password");
        }

        public Builder id(Long id) {
            this.id = id;
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

        public Builder copy(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.userbasics = user.getUserBasics();
            this.usercontact = user.getUserContact();
            this.userextras = user.getUserExtras();
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userbasics=" + userbasics +
                ", usercontact=" + usercontact +
                ", userextras=" + userextras +
                '}';
    }
}

