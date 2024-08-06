package com.trabalhoFinalPOO.demo.services;

import com.trabalhoFinalPOO.demo.entities.Animal;
import com.trabalhoFinalPOO.demo.entities.Pessoa;
import com.trabalhoFinalPOO.demo.repositories.AnimalRespository;
import com.trabalhoFinalPOO.demo.repositories.PessoaRepository;
import com.trabalhoFinalPOO.demo.services.exceptions.DatabaseException;
import com.trabalhoFinalPOO.demo.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    @Autowired
    private AnimalRespository animalRespository;
    @Autowired
    private PessoaRepository pessoaRepository;


    @Transactional(readOnly = true)
    public List<Animal> findAll(){
        List<Animal> entityList = animalRespository.findAll();
        return entityList;
    }
    @Transactional(readOnly = true)
    public Animal findById(Long id){
        Optional<Animal> obj  = animalRespository.findById(id);
        Animal entity = obj.orElseThrow(()-> new RuntimeException("Entity not found"));
        return entity;
    }
    @Transactional
    public Animal insert(Animal animal, Long id){
        Animal entity = new Animal();
        Pessoa pessoa = pessoaRepository.getReferenceById(id);
        entity.setNome(animal.getNome());
        entity.setIdade(animal.getIdade());
        entity.setTipoAnimal(animal.getTipoAnimal());
        entity.setCastrado(animal.getCastrado());
        entity.setVacinado(animal.getVacinado());
        entity.setPessoa(pessoa);
        entity = animalRespository.save(entity);

        return entity;
    }
    @Transactional
    public Animal update(Long id, Animal animal){
        try{
            Animal entity = animalRespository.getReferenceById(id);
            entity.setNome(animal.getNome());
            entity.setTipoAnimal(animal.getTipoAnimal());
            entity.setIdade(animal.getIdade());
            entity.setVacinado(animal.getVacinado());
            entity.setCastrado(animal.getCastrado());
            entity.setAdopted(animal.getAdopted());
            entity = animalRespository.save(entity);
            return entity;

        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id nao encontrado");
        }
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!animalRespository.existsById(id)){
            throw new ResourceAccessException("Recurso nao encontrado");
        }
        try {
            animalRespository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
