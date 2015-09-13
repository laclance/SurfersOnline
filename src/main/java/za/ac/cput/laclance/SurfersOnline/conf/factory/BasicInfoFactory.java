package za.ac.cput.laclance.SurfersOnline.conf.factory;

import za.ac.cput.laclance.SurfersOnline.domain.BasicInfo;

public class BasicInfoFactory {
    public static BasicInfo createBasicInfo(String name,
             String description) {

        BasicInfo basicInfo = new BasicInfo
                .Builder(name)
                .description(description)
                .build();

        return basicInfo;
    }
}
