package za.ac.cput.laclance.SurfersOnline.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class UserGroup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private BasicInfo basicInfo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "group_comment_id")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "group_user_id")
    private List<User> users;

    private UserGroup() {
    }

    public UserGroup(Builder builder) {
        id = builder.id;
        basicInfo = builder.basicInfo;
        comments = builder.comments;
        users = builder.users;
    }

    public Long getId() {
        return id;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public  List<Comment> getComments() {
        return comments;
    }

    public List<User> getUsers() {
        return users;
    }

    public static class Builder {
        private Long id;
        private BasicInfo basicInfo;
        private List<Comment> comments;
        private List<User> users;

        public Builder(BasicInfo basicInfo) {
            this.basicInfo = basicInfo;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder basicInfo(BasicInfo basicInfo) {
            this.basicInfo = basicInfo;
            return this;
        }

        public Builder comments(List<Comment> comments) {
            this.comments = comments;
            return this;
        }

        public Builder users(List<User> users) {
            this.users = users;
            return this;
        }

        public Builder copy(UserGroup userGroup) {
            this.id = userGroup.getId();
            this.basicInfo = userGroup.getBasicInfo();
            this.comments = userGroup.getComments();
            this.users = userGroup.getUsers();
            return this;
        }

        public UserGroup build() {
            return new UserGroup(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserGroup userGroup = (UserGroup) o;

        if (basicInfo != null ? !basicInfo.equals(userGroup.basicInfo) : userGroup.basicInfo != null) return false;
        if (comments != null ? !comments.equals(userGroup.comments) : userGroup.comments != null) return false;
        if (id != null ? !id.equals(userGroup.id) : userGroup.id != null) return false;
        if (users != null ? !users.equals(userGroup.users) : userGroup.users != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        return result;
    }

<<<<<<< HEAD
    @Override
    public String toString() {
        return "UserGroup{" +
                "id=" + id +
                ", basicInfo=" + basicInfo +
                ", comments=" + comments +
                ", users=" + users +
                '}';
    }
=======
>>>>>>> 6f61c1bd8eafb3177eb6bd2feaa8b79461e38527
}
