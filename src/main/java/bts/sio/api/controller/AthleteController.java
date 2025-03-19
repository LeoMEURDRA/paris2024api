package bts.sio.api.controller;

import bts.sio.api.model.Athlete;
import bts.sio.api.model.Pays;
import bts.sio.api.model.Sport;
import bts.sio.api.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    /**
     * Create - Add a new athlete
     * @param athlete An object athlete
     * @return The athlete object saved
     */
    @PostMapping("/athlete/ajouter")
    public Athlete createAthlete(@RequestBody Athlete athlete) {
        return athleteService.saveAthlete(athlete);
    }

    /**
     * Read - Get one athlete
     * @param id The id of the athlete
     * @return An Athlete object full filled
     */
    @GetMapping("/athlete/consulter/{id}")
    public Athlete getAthlete(@PathVariable("id") final Long id) {
        Optional<Athlete> athlete = athleteService.getAthlete(id);
        return athlete.orElse(null);
    }

    /**
     * Read - Get all athletes
     * @return - An Iterable object of Athlete full filled
     */
    @GetMapping("/athlete/lister")
    public Iterable<Athlete> getLesAthletes() {
        return athleteService.getLesAthletes();
    }

    /**
     * Read - Get all athletes from a sport
     * @return - An Iterable object of Athlete full filled
     */
    @GetMapping("/athlete/pays/{paysId}")
    public List<Athlete> getAthletes(@PathVariable Long paysId) {
        return athleteService.getLesAthletesByPays(paysId);
    }

    /**
     * Update - Update an existing athlete
     * @param id - The id of the athlete to update
     * @param athlete - The athlete object updated
     */
    @PutMapping("/athletes/modifier/{id}")
    public Athlete updateAthlete(@PathVariable("id") final Long id, @RequestBody Athlete athlete) {
        Optional<Athlete> ath = athleteService.getAthlete(id);
        if(ath.isPresent()) {
            Athlete currentAthlete = ath.get();

            String nom = athlete.getNom();
            if(nom != null) {
                currentAthlete.setNom(nom);
            }
            String prenom = athlete.getPrenom();
            if(prenom != null) {
                currentAthlete.setPrenom(prenom);
            }
            Pays pays = athlete.getPays();
            if(pays != null) {
                currentAthlete.setPays(pays);
            }
            Sport sport = athlete.getSport();
            if(sport != null) {
                currentAthlete.setSport(sport);
            }

            athleteService.saveAthlete(currentAthlete);
            return currentAthlete;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an athlete
     * @param id - The id of the athlete to delete
     */
    @DeleteMapping("/athlete/supprimer/{id}")
    public void deleteAthlete(@PathVariable("id") final Long id) {
        athleteService.deleteAthlete(id);
    }

}