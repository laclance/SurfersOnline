package za.ac.cput.laclance.SurfersOnline.model;

import org.springframework.hateoas.ResourceSupport;

public class CommentResource extends ResourceSupport {
    private Long resid;
    private String comment;
    private String user;
    private String date;

    public CommentResource (Builder builder){
        this.comment = builder.comment;
        this.user = builder.user;
        this.date = builder.date;
    }

    private CommentResource(){
    }

    public String getComment() {
        return this.comment;
    }

    public String getUser() {
        return this.user;
    }

    public String getDate() {
        return this.date;
    }

    public static class Builder {
        private long resid;
        private String comment;
        private String user;
        private String date;

        public Builder (String comment, String user)
        {
            this.comment = comment;
            this.user = user;
        }

        public Builder resid(Long resid) {
            this.resid = resid;
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

        public Builder copy (CommentResource comment)
        {
            this.comment = comment.getComment();
            this.user = comment.getUser();
            this.date = comment.getDate();
            return this;
        }

        public CommentResource build (){return new CommentResource(this);}
    }

}
