package bts.sio.api.controller;

import bts.sio.api.model.Sport;
import bts.sio.api.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SportController {

    @Autowired
    private SportService sportService;

    /**
     * Create - Add a new sport
     * @param sport An object sport
     * @return The sport object saved
     */
    @PostMapping("/sport/ajouter")
    public Sport createSport(@RequestBody Sport sport) {
        return sportService.saveSport(sport);
    }

    /**
     * Read - Get one sport
     * @param id The id of the sport
     * @return An sport object full filled
     */
    @GetMapping("/sport/consulter/{id}")
    public Sport getSport(@PathVariable("id") final Long id) {
        Optional<Sport> sport = sportService.getSport(id);
        return sport.orElse(null);
    }

    /**
     * Read - Get all sports
     * @return - An Iterable object of sport full filled
     */
    @GetMapping("/sport/lister")
    public Iterable<Sport> getLesSports() {
        return sportService.getLesSports();
    }

    /**
     * Update - Update an existing sport
     * @param id - The id of the sport to update
     * @param sport - The sport object updated
     */
    @PutMapping("/sport/modifier/{id}")
    public Sport updateSport(@PathVariable("id") final Long id, @RequestBody Sport sport) {
        Optional<Sport> e = sportService.getSport(id);
        if(e.isPresent()) {
            Sport currentSport = e.get();

            String nom = sport.getNom();
            if(nom != null) {
                currentSport.setNom(nom);
            }
            String descriptif = sport.getDescriptif();
            if(descriptif != null) {
                currentSport.setDescriptif(descriptif);
            }

            sportService.saveSport(currentSport);
            return currentSport;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an sport
     * @param id - The id of the sport to delete
     */
    @DeleteMapping("/sport/supprimer/{id}")
    public void deleteSport(@PathVariable("id") final Long id) {
        sportService.deleteSport(id);
    }

}