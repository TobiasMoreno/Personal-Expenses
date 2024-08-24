package org.personal.repositories;

import org.personal.entities.GastoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GastoJpaRepository extends JpaRepository<GastoEntity, Long> {
}
