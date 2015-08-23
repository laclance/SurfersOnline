package za.ac.cput.laclance.SurfersOnline.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserBasics implements Serializable {
    private String firstName;
    private String lastName;
    private char gender;
    private String dob;

    private UserBasics() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public char getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public UserBasics(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        gender = builder.gender;
        dob = builder.dob;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private char gender;
        private String dob;

        public Builder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder gender(char gender) {
            this.gender = gender;
            return this;
        }

        public Builder dob(String dob) {
            this.dob = dob;
            return this;
        }

        public Builder copy(UserBasics ub) {
            this.firstName = ub.firstName;
            this.lastName = ub.lastName;
            this.gender = ub.gender;
            this.dob = ub.dob;
            return this;
        }

        public UserBasics build() {
            return new UserBasics(this);
        }
    }
}

