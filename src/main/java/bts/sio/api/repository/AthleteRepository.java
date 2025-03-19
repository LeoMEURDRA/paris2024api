package bts.sio.api.repository;

import bts.sio.api.model.Athlete;
import bts.sio.api.model.Pays;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AthleteRepository extends CrudRepository<Athlete, Long> {

    List<Athlete> findByPays(Pays pays);

}