package org.personal.jpa;

import org.junit.jupiter.api.Test;
import org.personal.entities.GastoEntity;
import org.personal.repositories.GastoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GastoJpaRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GastoJpaRepository gastoJpaRepository;


}