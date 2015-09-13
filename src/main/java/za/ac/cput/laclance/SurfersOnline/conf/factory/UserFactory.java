package za.ac.cput.laclance.SurfersOnline.conf.factory;

import za.ac.cput.laclance.SurfersOnline.domain.*;
import java.util.Map;

public class UserFactory {
    public static User createUser(Map<String,String> values,
                                  UserBasics userbasics,
                                  UserContact usercontact,
                                  UserExtras userextras) {
        User user = new User
                .Builder(values)
                .userbasics(userbasics)
                .usercontact(usercontact)
                .userextras(userextras)
                .build();

        return user;
    }
}
