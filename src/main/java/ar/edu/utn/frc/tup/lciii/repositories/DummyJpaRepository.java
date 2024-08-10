package ar.edu.utn.frc.tup.lciii.repositories;

import ar.edu.utn.frc.tup.lciii.entities.DummyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DummyJpaRepository extends JpaRepository<DummyEntity, Long> {
	Optional<DummyEntity> findByDummy(String something);
}
