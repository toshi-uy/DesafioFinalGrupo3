package grupo3.desafioFinalBootcamp.models.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private Integer id;
    private String dni;
    private String name;
    private String lastname;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date birthDate;
    private String mail;

    public PersonDTO(String dni, String name, String lastname, Date birthDate, String mail) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.mail = mail;
    }
}
