package com.trabalhoFinalPOO.demo.controllers;

import com.trabalhoFinalPOO.demo.dto.PessoaDTO;
import com.trabalhoFinalPOO.demo.entities.Animal;
import com.trabalhoFinalPOO.demo.entities.Doador;
import com.trabalhoFinalPOO.demo.entities.Pessoa;
import com.trabalhoFinalPOO.demo.repositories.PessoaRepository;
import com.trabalhoFinalPOO.demo.services.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.type.ListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/animals")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name="Animal", description = "Endpoints para manusear a classe Animal.")
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @Autowired
    private PessoaRepository pessoaRepository;
    @GetMapping
    @Operation(summary = "Traz todos os animais disponiveis para adoção", description = "Traz todos os animais disponiveis para adoção",
            tags = {"Animal"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema=@Schema(implementation = Animal.class))
                    )}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthrorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<List<Animal>> findAll(){
        List<Animal> animalList = animalService.findAll();
        return ResponseEntity.ok().body(animalList);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Traz o animal especifico pelo seu id.", description = "Traz o animal especifico pelo seu id.",
            tags = {"Animal"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {@Content(schema = @Schema(implementation = Animal.class))}),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthrorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public  ResponseEntity<Animal> findById(@PathVariable Long id){
        Animal animal = animalService.findById(id);
        return ResponseEntity.ok(animal);

    }
    @PostMapping
    @Operation(summary = "Metodo de criar uma novo animal.", description = "Metodo de criar uma novo animal.",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {@Content(schema=@Schema(implementation = Doador.class))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthrorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Animal> insert(@RequestBody Animal animal, @RequestParam Long id){
        animal = animalService.insert(animal, id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(animal.getId()).toUri();
        Pessoa pessoa = pessoaRepository.getReferenceById(id);
        animal.setPessoa(pessoa);
        return ResponseEntity.created(uri).body(animal);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Edita um animal especifico caso haja necessidade.", description = "Edita um animal especifico caso haja necessidade.",
            tags = {"Animal"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {@Content(schema=@Schema(implementation = Animal.class))
                    }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthrorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Animal> update(@PathVariable Long id, @RequestBody Animal animal){
        animal = animalService.update(id, animal);
        return ResponseEntity.ok().body(animal);
    }

    @DeleteMapping(value="/{id}")
    @Operation(summary = "Deleta um animal especifico caso haja necessidade.", description = "Deleta um animal especifico caso haja necessidade.",
            tags = {"Animal"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthrorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Animal> delete(@PathVariable Long id){
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
