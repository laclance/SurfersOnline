package za.ac.cput.laclance.SurfersOnline.repository;

import org.springframework.data.repository.CrudRepository;
import za.ac.cput.laclance.SurfersOnline.domain.Comment;

public interface CommentRepository  extends CrudRepository<Comment, Long> {
}
