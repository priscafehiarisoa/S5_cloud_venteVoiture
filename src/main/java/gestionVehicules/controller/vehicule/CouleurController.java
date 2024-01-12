package gestionVehicules.controller.vehicule;

import gestionVehicules.model.vehicule.Carburant;
import gestionVehicules.model.vehicule.Couleur;
import gestionVehicules.repository.CarburantRepository;
import gestionVehicules.repository.CouleurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/couleur")
public class CouleurController {
    @Autowired
    CouleurRepository couleurRepository;

    @PostMapping
    public void insertCouleur(@RequestBody Couleur couleur){
        int id=couleurRepository.getNextval();
        couleur.setId_couleur(couleurRepository.getSequence(3,"CLR",id));
        couleurRepository.save(couleur);
    }

    @GetMapping
    public List<Couleur> getAllCouleurs(){
        return couleurRepository.findAll();
    }

    @PutMapping("/{id}")
    public Couleur updateCouleur(@RequestBody Couleur couleur, @PathVariable String id) {
        return couleurRepository.findById(String.valueOf(id)).map(
                entity1 -> {
                    entity1.setNom_couleur(couleur.getNom_couleur());

                    return couleurRepository.save(entity1);
                }
        ).orElseGet(() -> {
                    couleur.setId_couleur(String.valueOf(id));
                    return couleurRepository.save(couleur);

                }
        );
    }

    @DeleteMapping("/{id}")
    public void deleteCouleur(@PathVariable String id) throws IllegalAccessException {
        couleurRepository.deleteById(id);
    }
}
