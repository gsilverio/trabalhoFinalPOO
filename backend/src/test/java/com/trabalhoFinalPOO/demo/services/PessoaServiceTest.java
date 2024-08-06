package com.trabalhoFinalPOO.demo.services;

import com.trabalhoFinalPOO.demo.repositories.AnimalRespository;
import com.trabalhoFinalPOO.demo.repositories.PessoaRepository;
import com.trabalhoFinalPOO.demo.services.exceptions.DatabaseException;
import com.trabalhoFinalPOO.demo.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.ResourceAccessException;

@ExtendWith(SpringExtension.class)
public class PessoaServiceTest {
    @InjectMocks
    private PessoaService pessoaService;
    @Mock
    private PessoaRepository pessoaRepository;
    private long existingId;
    private long nonExistingId;
    private long dependentId;

    @BeforeEach
    void setUp(){
        existingId = 1L;
        nonExistingId = 1000L;
        dependentId = 5L;

        Mockito.when(pessoaRepository.existsById(existingId)).thenReturn(true);
        Mockito.when(pessoaRepository.existsById(nonExistingId)).thenReturn(false);
        Mockito.when(pessoaRepository.existsById(dependentId)).thenReturn(true);

        Mockito.doNothing().when(pessoaRepository).deleteById(existingId);
        Mockito.doThrow(ResourceAccessException.class).when(pessoaRepository).deleteById(nonExistingId);
        Mockito.doThrow(DatabaseException.class).when(pessoaRepository).deleteById(dependentId);
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists(){
        Assertions.assertDoesNotThrow(() -> {
            pessoaService.delete(existingId);
        });
    }
    @Test
    public void deleteShouldThrowResourceAccessExceptionWhenIdDoesNotExist(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            pessoaService.delete(nonExistingId);
        });
    }
    @Test
    public void deleteShouldThrowDatabaseExceptionWhenDependentId(){
        Assertions.assertThrows(DatabaseException.class, () -> {
            pessoaRepository.deleteById(dependentId);
        });
    }
}
