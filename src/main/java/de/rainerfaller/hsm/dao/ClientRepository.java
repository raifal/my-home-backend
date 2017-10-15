package de.rainerfaller.hsm.dao;

import de.rainerfaller.hsm.dto.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, String> {

}
