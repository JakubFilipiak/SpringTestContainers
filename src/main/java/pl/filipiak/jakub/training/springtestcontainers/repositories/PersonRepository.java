package pl.filipiak.jakub.training.springtestcontainers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.filipiak.jakub.training.springtestcontainers.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
