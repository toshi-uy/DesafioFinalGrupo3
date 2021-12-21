package grupo3.desafioFinalBootcamp.services;

import grupo3.desafioFinalBootcamp.exceptions.NoPackages;
import grupo3.desafioFinalBootcamp.exceptions.PackageNotFound;
import grupo3.desafioFinalBootcamp.models.DTOs.PackageDTO;
import grupo3.desafioFinalBootcamp.models.DTOs.StatusDTO;
import grupo3.desafioFinalBootcamp.models.FlightReservation;
import grupo3.desafioFinalBootcamp.models.Hotel;
import grupo3.desafioFinalBootcamp.models.HotelBooking;
import grupo3.desafioFinalBootcamp.models.Package;
import grupo3.desafioFinalBootcamp.repositories.FlightReservationRepository;
import grupo3.desafioFinalBootcamp.repositories.HotelBookingRepository;
import grupo3.desafioFinalBootcamp.repositories.TouristicPackageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TouristicPackageImp implements TouristicPackage {

    TouristicPackageRepository repo;
    HotelBookingRepository hotelRepo;
    FlightReservationRepository flightRepo;
    ModelMapper mapper = new ModelMapper();

    public TouristicPackageImp(TouristicPackageRepository repo, HotelBookingRepository hotelRepo, FlightReservationRepository flightRepo){
        this.repo = repo;
        this.hotelRepo = hotelRepo;
        this.flightRepo = flightRepo;
    }

    public List<PackageDTO> getAllPackages() throws NoPackages {
        List<Package> packageList = repo.findAll();
        if (packageList.isEmpty()) {
            throw new NoPackages();
        }
        return packageList.stream().map(packageDTO -> mapper.map(packageDTO, PackageDTO.class)).collect(Collectors.toList());
    }

    public StatusDTO editPackage(Integer packageNumber, PackageDTO packageDTO) throws PackageNotFound {
        Package pack = repo.findById(packageNumber).get();
        if(pack == null){
            throw new PackageNotFound();
        }

        Package edit = mapper.map(packageDTO, Package.class);
        repo.save(edit);
        return new StatusDTO("Paquete Turístico modificado correctamente.");
    }

    public StatusDTO deleteByID(Integer packageNumber) throws PackageNotFound {
        Package check = repo.getById(packageNumber);
        if (check == null) {
            throw new PackageNotFound();
        }
        repo.deleteById(packageNumber);
        return new StatusDTO("Paquete Turístico dado de baja correctamente.");
    }

    public StatusDTO newPackage(PackageDTO packageDTO){
        HotelBooking hb1 = hotelRepo.findById(packageDTO.getBookingsOrReservationsId().getBookResId1()).get();
        FlightReservation fr1 = flightRepo.findById(packageDTO.getBookingsOrReservationsId().getBookResId1()).get();
//        if(hb1 == null && fr1 == null)
//            throw new Exception(); //una nueva?

        HotelBooking hb2 = hotelRepo.findById(packageDTO.getBookingsOrReservationsId().getBookResId2()).get();
        FlightReservation fr2 = flightRepo.findById(packageDTO.getBookingsOrReservationsId().getBookResId2()).get();
//        if(hb2 == null && fr2 == null)
//            throw new Exception(); //una nueva?

        //si llegas hasta aca es porque tengo 2 reservas
        Package elPackage = mapper.map(packageDTO, Package.class);
        repo.save(elPackage);
        return new StatusDTO("Paquete Turístico dado de alta correctamente");
    }
}
