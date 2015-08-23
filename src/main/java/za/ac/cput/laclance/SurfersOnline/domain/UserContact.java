
package za.ac.cput.laclance.SurfersOnline.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserContact implements Serializable {
    private String email;
    private String contactNo;

    private UserContact(){
    }

    public String getEmail() {
        return email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public UserContact(Builder builder) {
        email = builder.email;
        contactNo = builder.contactNo;
    }

    public static class Builder {
        private String email;
        private String contactNo;

        public Builder(){}

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder contactNo(String contactNo) {
            this.contactNo = contactNo;
            return this;
        }

        public Builder copy(UserContact uc) {
            this.email = uc.email;
            this.contactNo = uc.contactNo;
            return this;
        }

        public UserContact build() {
            return new UserContact(this);
        }
    }
}


