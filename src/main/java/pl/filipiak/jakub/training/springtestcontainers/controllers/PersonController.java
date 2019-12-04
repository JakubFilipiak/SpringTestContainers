package pl.filipiak.jakub.training.springtestcontainers.controllers;

import org.springframework.web.bind.annotation.*;
import pl.filipiak.jakub.training.springtestcontainers.models.dtos.AddPersonDto;
import pl.filipiak.jakub.training.springtestcontainers.models.dtos.PersonDto;
import pl.filipiak.jakub.training.springtestcontainers.services.PersonService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public long addPerson(@RequestBody @Valid AddPersonDto addDto) {
        return personService.addPerson(addDto).getId();
    }

    @GetMapping
    public List<PersonDto> getPersons() {
        return personService.getPersons();
    }

    @GetMapping("/{id}")
    public PersonDto getPersonById(@PathVariable long id) {
        return personService.getPersonById(id);
    }

    @PutMapping
    public void updatePerson(@RequestBody @Valid PersonDto dto) {
        personService.updatePerson(dto);
    }

    @DeleteMapping("/{id}")
    public void deletePersonById(@PathVariable long id) {
        personService.deletePersonById(id);
    }
}
