package bts.sio.api.controller;

import bts.sio.api.model.Site;
import bts.sio.api.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SiteController {

    @Autowired
    private SiteService siteService;

    /**
     * Create - Add a new site
     * @param site An object site
     * @return The site object saved
     */
    @PostMapping("/site/ajouter")
    public Site createSite(@RequestBody Site site) {
        return siteService.saveSite(site);
    }

    /**
     * Read - Get one site
     * @param id The id of the site
     * @return An Site object full filled
     */
    @GetMapping("/site/consulter/{id}")
    public Site getSite(@PathVariable("id") final Long id) {
        Optional<Site> site = siteService.getSite(id);
        return site.orElse(null);
    }

    /**
     * Read - Get all sites
     * @return - An Iterable object of Site full filled
     */
    @GetMapping("/site/lister")
    public Iterable<Site> getLesSites() {
        return siteService.getLesSites();
    }

    /**
     * Update - Update an existing site
     * @param id - The id of the site to update
     * @param site - The site object updated
     */
    @PutMapping("/site/modifier/{id}")
    public Site updateSite(@PathVariable("id") final Long id, @RequestBody Site site) {
        Optional<Site> oly = siteService.getSite(id);
        if(oly.isPresent()) {
            Site currentSite = oly.get();

            String nom = site.getNom();
            if(nom != null) {
                currentSite.setNom(nom);
            }
            String ville = site.getVille();
            if(ville != null) {
                currentSite.setVille(ville);
            }

            siteService.saveSite(currentSite);
            return currentSite;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an site
     * @param id - The id of the site to delete
     */
    @DeleteMapping("/site/supprimer/{id}")
    public void deleteSite(@PathVariable("id") final Long id) {
        siteService.deleteSite(id);
    }

}
