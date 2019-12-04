package pl.filipiak.jakub.training.springtestcontainers.services;

import pl.filipiak.jakub.training.springtestcontainers.models.dtos.AddPersonDto;
import pl.filipiak.jakub.training.springtestcontainers.models.dtos.PersonDto;

import java.util.List;

public interface PersonService {

    PersonDto addPerson(AddPersonDto addDto);

    List<PersonDto> getPersons();

    PersonDto getPersonById(long id);

    PersonDto updatePerson(PersonDto dto);

    void deletePersonById(long id);
}
