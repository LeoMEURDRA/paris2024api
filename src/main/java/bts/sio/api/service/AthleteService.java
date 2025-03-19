package bts.sio.api.service;

import bts.sio.api.model.Athlete;
import bts.sio.api.model.Pays;
import bts.sio.api.repository.AthleteRepository;
import bts.sio.api.repository.PaysRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;
    @Autowired
    private PaysRepository paysRepository;

    public Optional<Athlete> getAthlete(final Long id) {
        return athleteRepository.findById(id);
    }

    public Iterable<Athlete> getLesAthletes() {
        return athleteRepository.findAll();
    }

    public List<Athlete> getLesAthletesByPays(Long paysId) {
        Pays pays = paysRepository.findById(paysId).orElse(null); // Or you could use Optional if you want
        if (pays == null) {
            throw new RuntimeException("Pays with ID " + paysId + " not found");
        }
        return athleteRepository.findByPays(pays);
    }

    public void deleteAthlete(final Long id) {
        athleteRepository.deleteById(id);
    }

    public Athlete saveAthlete(Athlete athlete) {
        return athleteRepository.save(athlete);
    }

}