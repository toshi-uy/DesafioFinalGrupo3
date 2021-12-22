package grupo3.desafioFinalBootcamp.controller;

import grupo3.desafioFinalBootcamp.exceptions.NoPackages;
import grupo3.desafioFinalBootcamp.exceptions.PackageNotFound;
import grupo3.desafioFinalBootcamp.models.DTOs.PackageDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;
import grupo3.desafioFinalBootcamp.services.TouristicPackage;
import grupo3.desafioFinalBootcamp.services.TouristicPackageImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TouristicPackageController {

    final TouristicPackage service;

    public TouristicPackageController(TouristicPackage service) {
        this.service = service;
    }

    @PostMapping("/touristicpackage/new")
    public ResponseEntity<StatusDTO> newPackage(@RequestBody PackageDTO packageDTO){
        return new ResponseEntity<>(service.newPackage(packageDTO), HttpStatus.OK);
    }

    @PutMapping("/touristicpackage/edit")
    public ResponseEntity<StatusDTO> editPackage(@RequestParam Integer packageNumber, @RequestBody PackageDTO packageDTO) throws PackageNotFound {
        return new ResponseEntity<>(service.editPackage(packageNumber, packageDTO), HttpStatus.OK);
    }

    @GetMapping("/touristicpackages")
    public ResponseEntity<List<PackageDTO>> getAllPackages() throws NoPackages {
        return new ResponseEntity<>(service.getAllPackages(), HttpStatus.OK);
    }

    @DeleteMapping("/touristicpackage/delete")
    public ResponseEntity<StatusDTO> deletePackage(@RequestParam Integer packageNumber) throws PackageNotFound {
        return new ResponseEntity<>(service.deleteByID(packageNumber), HttpStatus.OK);
    }
}
