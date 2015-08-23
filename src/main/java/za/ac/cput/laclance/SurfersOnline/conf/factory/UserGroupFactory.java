package za.ac.cput.laclance.SurfersOnline.conf.factory;

import za.ac.cput.laclance.SurfersOnline.domain.*;

import java.util.List;

public class UserGroupFactory {
    public static UserGroup createGroup(BasicInfo basicInfo,
                                          List<User> users) {
        UserGroup userGroup = new UserGroup
                .Builder(basicInfo)
                .users(users)
                .build();
        return userGroup;
    }
}
