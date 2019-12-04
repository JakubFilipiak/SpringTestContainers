package pl.filipiak.jakub.training.springtestcontainers.assemblers;

import org.springframework.stereotype.Component;
import pl.filipiak.jakub.training.springtestcontainers.models.Person;
import pl.filipiak.jakub.training.springtestcontainers.models.dtos.AddPersonDto;
import pl.filipiak.jakub.training.springtestcontainers.models.dtos.PersonDto;

@Component
public class PersonAssembler {

    public Person entityFromAddDto(AddPersonDto addDto) {
        String name = retrieveName(addDto);
        String surname = retrieveSurname(addDto);
        int age = retrieveAge(addDto);
        return Person.builder()
                .name(name)
                .surname(surname)
                .age(age)
                .build();
    }

    public Person entityFromEditDto(PersonDto dto) {
        Long id = retrieveId(dto);
        String name = retrieveName(dto);
        String surname = retrieveSurname(dto);
        int age = retrieveAge(dto);
        return Person.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .age(age)
                .build();
    }

    public PersonDto dtoFromEntity(Person entity) {
        long id = retrieveId(entity);
        String name = retrieveName(entity);
        String surname = retrieveSurname(entity);
        int age = retrieveAge(entity);
        return PersonDto.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .age(age)
                .build();
    }

    private Long retrieveId(PersonDto dto) {
        return dto.getId();
    }

    private long retrieveId(Person entity) {
        return entity.getId();
    }

    private String retrieveName(AddPersonDto addDto) {
        return addDto.getName();
    }

    private String retrieveName(PersonDto dto) {
        return dto.getName();
    }

    private String retrieveName(Person entity) {
        return entity.getName();
    }

    private String retrieveSurname(AddPersonDto addDto) {
        return addDto.getSurname();
    }

    private String retrieveSurname(PersonDto dto) {
        return dto.getSurname();
    }

    private String retrieveSurname(Person entity) {
        return entity.getSurname();
    }

    private int retrieveAge(AddPersonDto addDto) {
        return addDto.getAge();
    }

    private int retrieveAge(PersonDto dto) {
        return dto.getAge();
    }

    private int retrieveAge(Person entity) {
        return entity.getAge();
    }
}
