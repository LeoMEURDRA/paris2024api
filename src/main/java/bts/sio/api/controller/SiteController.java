package bts.sio.api.controller;

import bts.sio.api.model.Site;
import bts.sio.api.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;

@RestController
@Tag(name = "Site", description = "API de gestion des sites")
public class SiteController {

    @Autowired
    private SiteService siteService;

    /**
     * Create - Add a new site
     * @param site An object site
     * @return The site object saved
     */
    @Operation(summary = "Créer un nouveau site",
            description = "Ajoute un nouveau site à la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Site créé avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Site.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Données invalides fournies",
                    content = @Content)
    })
    @PostMapping("/site/ajouter")
    public Site createSite(@RequestBody Site site) {
        return siteService.saveSite(site);
    }

    /**
     * Read - Get one site
     * @param id The id of the site
     * @return An Site object full filled
     */
    @Operation(summary = "Obtenir un site",
            description = "Retourne un site avec l'ID donné")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Site trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Site.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Site non trouvé",
                    content = @Content)
    })
    @GetMapping("/site/consulter/{id}")
    public Site getSite(@PathVariable("id") final Long id) {
        Optional<Site> site = siteService.getSite(id);
        return site.orElse(null);
    }

    /**
     * Read - Get all sites
     * @return - An Iterable object of Site full filled
     */
    @Operation(summary = "Obtenir tous les sites",
            description = "Retourne la liste de tous les sites")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Liste des sites trouvée",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Site.class)) })
    })
    @GetMapping("/site/lister")
    public Iterable<Site> getLesSites() {
        return siteService.getLesSites();
    }

    /**
     * Update - Update an existing site
     * @param id - The id of the site to update
     * @param site - The site object updated
     */
    @Operation(summary = "Mettre à jour un site",
            description = "Met à jour les informations d'un site existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Site mis à jour avec succès",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Site.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Site non trouvé",
                    content = @Content)
    })
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
     * Delete - Delete a site
     * @param id - The id of the site to delete
     */
    @Operation(summary = "Supprimer un site",
            description = "Supprime un site de la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Site supprimé avec succès",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Site non trouvé",
                    content = @Content)
    })
    @DeleteMapping("/site/supprimer/{id}")
    public void deleteSite(@PathVariable("id") final Long id) {
        siteService.deleteSite(id);
    }

}
