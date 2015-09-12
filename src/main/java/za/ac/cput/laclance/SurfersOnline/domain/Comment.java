package za.ac.cput.laclance.SurfersOnline.domain;

import javax.persistence.*;

@Embeddable
public class Comment {
    private String comment;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private User user;


}
