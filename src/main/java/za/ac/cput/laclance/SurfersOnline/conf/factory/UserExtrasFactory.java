package za.ac.cput.laclance.SurfersOnline.conf.factory;

import za.ac.cput.laclance.SurfersOnline.domain.UserExtras;

public class UserExtrasFactory {
    public static UserExtras createUserExtras(String style,
                                             String aboutMe,
                                             String facebook) {
        UserExtras extras = new UserExtras
                .Builder()
                .style(style)
                .aboutMe(aboutMe)
                .facebook(facebook)
                .build();

        return extras;
    }
}
