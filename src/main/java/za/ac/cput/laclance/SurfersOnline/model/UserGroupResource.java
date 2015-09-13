package za.ac.cput.laclance.SurfersOnline.model;

import org.springframework.hateoas.ResourceSupport;
import za.ac.cput.laclance.SurfersOnline.domain.BasicInfo;
import za.ac.cput.laclance.SurfersOnline.domain.User;

import java.util.List;

public class UserGroupResource extends ResourceSupport {
    private Long resid;
    private BasicInfo basicInfo;
    private List<User> users;

    private UserGroupResource() {
    }

    public UserGroupResource(Builder builder) {
        resid = builder.resid;
        basicInfo = builder.basicInfo;
        users = builder.users;
    }

    public Long getResid() {
        return resid;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public List<User> getUsers() {
        return users;
    }

    public static class Builder {
        private Long resid;
        private BasicInfo basicInfo;
        private List<User> users;

        public Builder(BasicInfo basicInfo) {
            this.basicInfo = basicInfo;
        }

        public Builder resid(Long resid) {
            this.resid = resid;
            return this;
        }

        public Builder basicInfo(BasicInfo basicInfo) {
            this.basicInfo = basicInfo;
            return this;
        }

        public Builder users(List<User> users) {
            this.users = users;
            return this;
        }

        public Builder copy(UserGroupResource userGroup) {
            this.resid = userGroup.getResid();
            this.basicInfo = userGroup.getBasicInfo();
            this.users = userGroup.getUsers();
            return this;
        }

        public UserGroupResource build() {
            return new UserGroupResource(this);
        }
    }
}
