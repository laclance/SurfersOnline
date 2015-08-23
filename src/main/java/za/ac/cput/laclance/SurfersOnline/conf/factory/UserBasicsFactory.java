package za.ac.cput.laclance.SurfersOnline.conf.factory;

import za.ac.cput.laclance.SurfersOnline.domain.UserBasics;

import java.util.Map;

public class UserBasicsFactory {
    public static UserBasics createUserBasics(Map<String,String> values,
                                         char gender,
                                         String dob) {
        UserBasics basics = new UserBasics
                .Builder(values.get("firstName"), values.get("lastName"))
                .gender(gender)
                .dob(dob)
                .build();

        return basics;
    }
}
