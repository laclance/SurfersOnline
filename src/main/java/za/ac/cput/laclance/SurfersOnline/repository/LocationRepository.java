package za.ac.cput.laclance.SurfersOnline.repository;

import org.springframework.data.repository.CrudRepository;
import za.ac.cput.laclance.SurfersOnline.domain.Location;

public interface LocationRepository extends CrudRepository<Location,Long> {
}
