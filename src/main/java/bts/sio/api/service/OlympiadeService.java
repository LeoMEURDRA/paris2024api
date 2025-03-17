package bts.sio.api.service;

import bts.sio.api.model.Olympiade;
import bts.sio.api.repository.OlympiadeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class OlympiadeService {

    @Autowired
    private OlympiadeRepository olympiadeRepository;

    public Optional<Olympiade> getOlympiade(final Long id) {
        return olympiadeRepository.findById(id);
    }

    public Iterable<Olympiade> getLesOlympiades() {
        return olympiadeRepository.findAll();
    }

    public void deleteOlympiade(final Long id) {
        olympiadeRepository.deleteById(id);
    }

    public Olympiade saveOlympiade(Olympiade olympiade) {
        return olympiadeRepository.save(olympiade);
    }

}