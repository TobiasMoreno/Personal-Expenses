package org.personal.repositories;

import org.personal.entities.TipoGastoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoGastoJpaRepository  extends JpaRepository<TipoGastoEntity, Long> {
}
