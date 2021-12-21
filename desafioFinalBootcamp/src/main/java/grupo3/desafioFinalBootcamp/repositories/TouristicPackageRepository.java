package grupo3.desafioFinalBootcamp.repositories;

import grupo3.desafioFinalBootcamp.models.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristicPackageRepository extends JpaRepository<Package, Integer> {
}
