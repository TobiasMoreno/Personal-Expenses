package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.ResponseDummyDTO;
import ar.edu.utn.frc.tup.lciii.dtos.SaveDummyDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DummyService {

    List<ResponseDummyDTO> getDummyList();
    ResponseDummyDTO getDummyById(Long id);
    ResponseDummyDTO createDummy(SaveDummyDTO dummy) throws IllegalArgumentException;
    ResponseDummyDTO updateDummy(Long id, SaveDummyDTO dummy);
    void deleteDummyById(Long id);
}
