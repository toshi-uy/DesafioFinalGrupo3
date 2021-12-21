package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.exceptions.NoPackages;
import grupo3.desafioFinalBootcamp.exceptions.PackageNotFound;
import grupo3.desafioFinalBootcamp.models.DTOs.PackageDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;

import java.util.List;

public interface TouristicPackage {
    List<PackageDTO> getAllPackages() throws NoPackages;
    StatusDTO editPackage(Integer packageNumber, PackageDTO packageDTO) throws PackageNotFound;
    StatusDTO deleteByID(Integer packageId) throws PackageNotFound;
    StatusDTO newPackage(PackageDTO packageDTO);
}
