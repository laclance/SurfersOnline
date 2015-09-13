package za.ac.cput.laclance.SurfersOnline.conf.factory;

import za.ac.cput.laclance.SurfersOnline.domain.UserContact;

public class UserContactFactory {
    public static UserContact createUserContact(String email,
                                               String contactNo) {
        UserContact contact = new UserContact
                .Builder()
                .email(email)
                .contactNo(contactNo)
                .build();

        return contact;
    }
}
