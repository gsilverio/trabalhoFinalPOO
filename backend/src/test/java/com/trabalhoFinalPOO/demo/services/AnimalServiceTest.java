package com.trabalhoFinalPOO.demo.services;

import com.trabalhoFinalPOO.demo.entities.Animal;
import com.trabalhoFinalPOO.demo.repositories.AnimalRespository;
import com.trabalhoFinalPOO.demo.services.exceptions.DatabaseException;
import com.trabalhoFinalPOO.demo.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class AnimalServiceTest {
    @InjectMocks
    private AnimalService animalService;

    @Mock
    private AnimalRespository animalRespository;

    private long existingId;
    private long nonExistingId;
    private long dependentId;

    private List<Animal> list = new ArrayList<>();
    private Animal animal = new Animal("Torin", 1.0, "Cachorro", true, true);

    @BeforeEach
    void setUp(){
        existingId = 1L;
        nonExistingId = 1000L;
        dependentId = 4L;

        Mockito.when(animalRespository.existsById(existingId)).thenReturn(true);
        Mockito.when(animalRespository.existsById(nonExistingId)).thenReturn(false);
        Mockito.when(animalRespository.existsById(dependentId)).thenReturn(true);

        Mockito.doNothing().when(animalRespository).deleteById(existingId);
        Mockito.doThrow(ResourceAccessException.class).when(animalRespository).deleteById(nonExistingId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(animalRespository).deleteById(dependentId);

        Mockito.when(animalRespository.findAll()).thenReturn(list);
        Mockito.when(animalRespository.save(ArgumentMatchers.any())).thenReturn(animal);
        Mockito.when(animalRespository.findById(existingId)).thenReturn(Optional.of(animal));
        Mockito.when(animalRespository.findById(nonExistingId)).thenReturn(Optional.empty());
    }

    @Test
    public void findByIdShowThrowResourceNotFoundExeptionWhenIdDoesNotExist(){
        Assertions.assertThrows(RuntimeException.class, ()->{
            Animal animal1 = animalService.findById(nonExistingId);
        });

    }
    @Test
    public void findByIdShouldReturnAnimalWhenIdExists(){
        Animal animal1 = animalService.findById(existingId);
        Assertions.assertNotNull(animal1);
    }

    @Test
    public void findAllShouldReturnAListOfAnimal(){

        list = animalService.findAll();
        list.add(animal);
        Assertions.assertNotNull(list);
        Mockito.verify(animalRespository,Mockito.times(1)).findAll();
    }
    @Test
    public void deleteShouldDoNothingWhenIdExists(){
        Assertions.assertDoesNotThrow(() -> {
            animalService.delete(existingId);
        });
    }

    @Test
    public void deleteShouldThrowResourceAccessExceptionWhenIdDoesNotExist(){
        Assertions.assertThrows(ResourceAccessException.class, () -> {
            animalService.delete(nonExistingId);
        });
    }

    @Test
    public void deleteShouldThrowDatabaseExceptionWhenDependentId(){
        Assertions.assertThrows(DatabaseException.class, () -> {
            animalService.delete(dependentId);
        });
    }
}