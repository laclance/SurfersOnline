package za.ac.cput.laclance.SurfersOnline.services;

import java.util.List;

public interface Services<S, ID> {

    public S findById(ID id);

    public S save(S entity);

    public S update(S entity);

    public void delete(S entity);

    public List<S> findAll();
}
