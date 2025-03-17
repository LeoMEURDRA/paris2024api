package bts.sio.api.controller;

import bts.sio.api.model.Pays;
import bts.sio.api.service.PaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PaysController {

    @Autowired
    private PaysService paysService;

    /**
     * Create - Add a new pays
     * @param pays An object pays
     * @return The pays object saved
     */
    @PostMapping("/pays/ajouter")
    public Pays createPays(@RequestBody Pays pays) {
        return paysService.savePays(pays);
    }

    /**
     * Read - Get one pays
     * @param id The id of the pays
     * @return An Pays object full filled
     */
    @GetMapping("/pays/consulter/{id}")
    public Pays getPays(@PathVariable("id") final Long id) {
        Optional<Pays> pays = paysService.getPays(id);
        return pays.orElse(null);
    }

    /**
     * Read - Get all pays
     * @return - An Iterable object of Pays full filled
     */
    @GetMapping("/pays/lister")
    public Iterable<Pays> getLesPays() {
        return paysService.getLesPays();
    }

    /**
     * Update - Update an existing pays
     * @param id - The id of the pays to update
     * @param pays - The pays object updated
     */
    @PutMapping("/pays/modifier/{id}")
    public Pays updatePays(@PathVariable("id") final Long id, @RequestBody Pays pays) {
        Optional<Pays> oly = paysService.getPays(id);
        if(oly.isPresent()) {
            Pays currentPays = oly.get();

            String nom = pays.getNom();
            if(nom != null) {
                currentPays.setNom(nom);
            }
            String code = pays.getCode();
            if(code != null) {
                currentPays.setCode(code);
            }

            paysService.savePays(currentPays);
            return currentPays;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an pays
     * @param id - The id of the pays to delete
     */
    @DeleteMapping("/pays/supprimer/{id}")
    public void deletePays(@PathVariable("id") final Long id) {
        paysService.deletePays(id);
    }

}
