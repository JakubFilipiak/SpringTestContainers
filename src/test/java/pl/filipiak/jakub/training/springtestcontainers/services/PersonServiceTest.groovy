package pl.filipiak.jakub.training.springtestcontainers.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import pl.filipiak.jakub.training.springtestcontainers.models.dtos.AddPersonDto
import pl.filipiak.jakub.training.springtestcontainers.models.dtos.PersonDto
import pl.filipiak.jakub.training.springtestcontainers.utils.ResourceNotFoundException
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("test")
class PersonServiceTest extends Specification {

    @Autowired
    PersonService personService

    def "Should correct save Person in db"() {
        given:
        AddPersonDto personToSave = createRandomAddPersonDto()

        when:
        PersonDto personSaved = personService.addPerson(personToSave)

        then:
        personSaved != null
        personSaved.getId() != null
        personSaved.getName() == personToSave.getName()
        personSaved.getSurname() == personToSave.getSurname()
        personToSave.getAge() == personToSave.getAge()
    }

    def "Should correct read saved Person from DB"() {
        given:
        PersonDto personSaved = personService.addPerson(createRandomAddPersonDto())

        when:
        PersonDto personRead = personService.getPersonById(personSaved.getId())

        then:
        personRead != null
        personRead == personSaved
    }

    def "Should throw exception when trying to read Person from DB by wrong id"() {
        given:
        Long randomId = Math.abs(new Random().nextLong())

        when:
        personService.getPersonById(randomId)

        then:
        thrown(ResourceNotFoundException.class)
    }

    def "Should correct read saved Persons from DB"() {
        given:
        personService.addPerson(createRandomAddPersonDto())
        personService.addPerson(createRandomAddPersonDto())
        personService.addPerson(createRandomAddPersonDto())

        when:
        List<PersonDto> personsRead = personService.getPersons()

        then:
        personsRead != null
        personsRead.size() >= 3
    }

    def "Should correct update saved Person in DB"() {
        given:
        PersonDto personToUpdate = personService.addPerson(createRandomAddPersonDto())
        PersonDto personWithNewValues = createRandomPersonDtoWithGivenId(personToUpdate.getId())

        when:
        PersonDto personUpdated = personService.updatePerson(personWithNewValues)

        then:
        personUpdated != null
        personUpdated != personToUpdate
        personUpdated == personWithNewValues
    }

    def "Should throw exception when trying to update saved Person in DB by wrong id"() {
        given:
        Long randomId = Math.abs(new Random().nextLong())
        PersonDto personToUpdate = createRandomPersonDtoWithGivenId(randomId)

        when:
        personService.updatePerson(personToUpdate)

        then:
        thrown(ResourceNotFoundException.class)
    }

    def "Should correct delete saved Person from DB"() {
        given:
        PersonDto personToDelete = personService.addPerson(createRandomAddPersonDto())

        when:
        personService.deletePersonById(personToDelete.getId())
        personService.getPersonById(personToDelete.getId())

        then:
        thrown(ResourceNotFoundException.class)
    }

    def "Should throw exception when trying to delete saved Person from DB by wrong id"() {
        given:
        Long randomId = Math.abs(new Random().nextLong())

        when:
        personService.deletePersonById(randomId)

        then:
        thrown(ResourceNotFoundException.class)
    }

    def createRandomAddPersonDto() {
        String name = String.valueOf(new Random().nextInt())
        String surname = String.valueOf(new Random().nextInt())
        Integer age = Math.abs(new Random().nextInt())
        return new AddPersonDto(name, surname, age)
    }

    def createRandomPersonDtoWithGivenId(Long id) {
        String name = String.valueOf(new Random().nextInt())
        String surname = String.valueOf(new Random().nextInt())
        Integer age = Math.abs(new Random().nextInt())
        return new PersonDto(id, name, surname, age)
    }
}
