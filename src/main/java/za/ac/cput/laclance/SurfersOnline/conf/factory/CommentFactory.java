package za.ac.cput.laclance.SurfersOnline.conf.factory;

import za.ac.cput.laclance.SurfersOnline.domain.Comment;
import java.util.Map;

public class CommentFactory {
    public static Comment createComment(Map<String, String> values, String date)
    {
        Comment comm = new Comment
                .Builder(values.get("comment"), values.get("username"))
                .date(date)
                .build();

        return comm;
    }
}
