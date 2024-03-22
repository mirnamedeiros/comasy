package pds.comasy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pds.comasy.dto.PersonDto;
import pds.comasy.service.PersonService;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //CREATE PERSON
    @PostMapping
    public ResponseEntity<PersonDto> addPerson(@RequestBody PersonDto personDto) {
        return new ResponseEntity<>(personService.createPerson(personDto), HttpStatus.CREATED);
    }

    //GET PERSON
    @GetMapping("/{cpf}")
    public ResponseEntity<PersonDto> getPersonByCpf(@PathVariable String cpf) throws Exception {
        return ResponseEntity.ok(personService.getPersonByCpf(cpf));
    }

    // UPDATE PERSON
    @PutMapping("/{cpf}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable String cpf, @RequestBody PersonDto personDto) throws Exception {
        personDto.setCpf(cpf);
        return ResponseEntity.ok(personService.updatePerson(personDto));
    }
}
