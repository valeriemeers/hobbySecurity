package be.qnh.hobby.repository;

import be.qnh.hobby.domain.CakeTin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BakingRepository extends CrudRepository<CakeTin, Long> {

    public Iterable<CakeTin> findCakeTinByMaterialOrderByShape(String material);
}
