package za.ac.cput.laclance.SurfersOnline.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String comment;
    private String user;
    private String date;

    public Comment (Builder builder){
        comment = builder.comment;
        user = builder.user;
        date = builder.date;
    }

    private Comment(){
    }

    public String getComment() {
        return comment;
    }

    public String getUser() {
        return user;
    }

    public String getDate() {
        return date;
    }

    public static class Builder {
        private Long id;
        private String comment;
        private String user;
        private String date;

        public Builder (String comment, String user)
        {
            this.comment = comment;
            this.user = user;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder comment (String comment)
        {
            this.comment = comment;
            return this;
        }

        public Builder user (String user)
        {
            this.user = user;
            return this;
        }

        public Builder date (String date)
        {
            this.date = date;
            return this;
        }

        public Builder copy (Comment comment)
        {
            this.comment = comment.getComment();
            this.user = comment.getUser();
            this.date = comment.getDate();
            return this;
        }

        public Comment build (){return new Comment(this);}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment1 = (Comment) o;

        if (comment != null ? !comment.equals(comment1.comment) : comment1.comment != null) return false;
        if (date != null ? !date.equals(comment1.date) : comment1.date != null) return false;
        if (id != null ? !id.equals(comment1.id) : comment1.id != null) return false;
        if (user != null ? !user.equals(comment1.user) : comment1.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", user='" + user + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}

