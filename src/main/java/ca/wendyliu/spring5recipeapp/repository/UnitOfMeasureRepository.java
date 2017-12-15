package ca.wendyliu.spring5recipeapp.repository;

import ca.wendyliu.spring5recipeapp.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    /** Spring JDBC will auto-generate this method! */
    Optional<UnitOfMeasure> findByDescription(String description);
}
