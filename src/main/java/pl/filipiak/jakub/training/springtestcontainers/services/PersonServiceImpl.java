package pl.filipiak.jakub.training.springtestcontainers.services;

import org.springframework.stereotype.Service;
import pl.filipiak.jakub.training.springtestcontainers.models.Person;
import pl.filipiak.jakub.training.springtestcontainers.repositories.PersonRepository;
import pl.filipiak.jakub.training.springtestcontainers.assemblers.PersonAssembler;
import pl.filipiak.jakub.training.springtestcontainers.models.dtos.AddPersonDto;
import pl.filipiak.jakub.training.springtestcontainers.models.dtos.PersonDto;
import pl.filipiak.jakub.training.springtestcontainers.utils.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    private PersonAssembler personAssembler;

    public PersonServiceImpl(PersonRepository personRepository, PersonAssembler personAssembler) {
        this.personRepository = personRepository;
        this.personAssembler = personAssembler;
    }

    @Override
    public PersonDto addPerson(AddPersonDto addDto) {
        Person person = personAssembler.entityFromAddDto(addDto);
        Person personSaved = personRepository.save(person);
        return personAssembler.dtoFromEntity(personSaved);
    }

    @Override
    public List<PersonDto> getPersons() {
        return personRepository.findAll()
                .stream()
                .map(personAssembler::dtoFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto getPersonById(long id) {
        Optional<Person> personOpt = personRepository.findById(id);
        if (personOpt.isPresent()) {
            return personAssembler.dtoFromEntity(personOpt.get());
        }
        throw new ResourceNotFoundException(id);

    }

    @Override
    public PersonDto updatePerson(PersonDto dto) {
        Optional<Person> personOpt = personRepository.findById(dto.getId());
        if (personOpt.isPresent()) {
            Person personForUpdate = personAssembler.entityFromEditDto(dto);
            Person personUpdated = personRepository.save(personForUpdate);
            return personAssembler.dtoFromEntity(personUpdated);
        }
        throw new ResourceNotFoundException(dto.getId());
    }

    @Override
    public void deletePersonById(long id) {
        Optional<Person> personOpt = personRepository.findById(id);
        if (personOpt.isPresent()) {
            personRepository.deleteById(id);
            return;
        }
        throw new ResourceNotFoundException(id);
    }
}
