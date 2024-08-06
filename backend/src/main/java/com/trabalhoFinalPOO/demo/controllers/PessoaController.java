package com.trabalhoFinalPOO.demo.controllers;

import com.trabalhoFinalPOO.demo.dto.AnimalDTO;
import com.trabalhoFinalPOO.demo.dto.PessoaDTO;
import com.trabalhoFinalPOO.demo.entities.Animal;
import com.trabalhoFinalPOO.demo.entities.Doador;
import com.trabalhoFinalPOO.demo.entities.Pessoa;
import com.trabalhoFinalPOO.demo.entities.Receptor;
import com.trabalhoFinalPOO.demo.projetions.AnimalProjections;
import com.trabalhoFinalPOO.demo.repositories.AnimalRespository;
import com.trabalhoFinalPOO.demo.services.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name="Pessoa", description = "Endpoints para manusear a classe Pessoa.")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    @Operation(summary = "Traz a pessoa especifica pelo seu email.", description = "Traz a pessoa especifica pelo seu email.",
        tags = {"Pessoa"},
        responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {@Content(schema = @Schema(implementation = PessoaDTO.class))}),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthrorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        })
    public ResponseEntity<PessoaDTO> searchPessoaByEmail(@RequestParam String email, @RequestParam String password){
        PessoaDTO pessoa = pessoaService.searchPessoaByEmail(email, password);
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping("/doadores")
    @Operation(summary = "Traz todas as pessoas do tipo doadora.", description = "Traz todas as pessoas do tipo doadora.",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema=@Schema(implementation = Doador.class))
                    )}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthrorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<List<Doador>> findAll(){
        List<Doador> list = pessoaService.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/receptores")
    @Operation(summary = "Traz todas as pessoas do tipo receptora.", description = "Traz todas as pessoas do tipo receptora.",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema=@Schema(implementation = Receptor.class))
                    )}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthrorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<List<Receptor>> findAllReceptores(){
        List<Receptor> list = pessoaService.findAllReceptor();
        return ResponseEntity.ok(list);
    }
    @GetMapping(value = "/{id}")
    @Operation(summary = "Traz a pessoa especifica pelo seu id. Podendo ser doadora ou receptora.", description = "Traz a pessoa especifica pelo seu id. Podendo ser doadora ou receptora.",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {@Content(schema=@Schema(implementation = Pessoa.class))}),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthrorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Pessoa> findById(@PathVariable Long id){
        Pessoa entity = pessoaService.findById(id);
        return ResponseEntity.ok(entity);
    }
//    @GetMapping(value = "/receptor/{id}")
//    public ResponseEntity<Receptor> findByIdReceptor(@PathVariable Long id){
//        Receptor entity = pessoaService.findByIdReceptor(id);
//        return ResponseEntity.ok(entity);
//    }
    @GetMapping(value = "/{id}/animals")
    @Operation(summary = "Traz todos os animais contidos em cada usuarios(pessoa).", description = "Traz todos os animais contidos em cada usuarios(pessoa).",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema=@Schema(implementation = AnimalRespository.class))
                    )}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthrorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<List<AnimalProjections>> searchAnimalsByUser(@PathVariable Long id){
        List<AnimalProjections> listAnimal = pessoaService.searchAnimalsByUser(id);
        return ResponseEntity.ok(listAnimal);
    }

    @PostMapping("/adotar")
    @Operation(summary = "Metodo de inserir um animal na lista de animais da pessoa doadora, caso ela deseje adotar um animal.", description = "Metodo de inserir um animal na lista de animais da pessoa doadora, caso ela deseje adotar um animal.",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthrorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public void adotarAnimal(@RequestParam Long animalId, @RequestParam Long userId) {
        pessoaService.adotar(animalId, userId);
    }

    @PostMapping("/createDoador")
    @Operation(summary = "Metodo de criar uma nova pessoa do tipo Doador.", description = "Metodo de criar uma nova pessoa do tipo Doador.",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {@Content(schema=@Schema(implementation = Doador.class))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthrorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Doador> createDoador(@RequestBody Doador doador){
        Doador doador1 = pessoaService.createDoador(doador);
        return ResponseEntity.ok(doador1);
    }
    @PostMapping("/createReceptor")
    @Operation(summary = "Metodo de criar uma nova pessoa do tipo Receptor.", description = "Metodo de criar uma nova pessoa do tipo Receptor.",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {@Content(schema=@Schema(implementation = Receptor.class))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthrorized", responseCode = "401"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
                    @ApiResponse(description = "Internal Error", responseCode = "500")
            })
    public ResponseEntity<Receptor> createReceptor(@RequestBody Receptor receptor){
        Receptor receptor1 = pessoaService.createReceptor(receptor);
        return ResponseEntity.ok(receptor1);
    }

    @PutMapping(value = "/editReceptor/{id}")
    @Operation(summary = "Edita uma pessoa receptora atraves do seu id.", description = "Edita uma pessoa receptora atraves do seu id.",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {@Content(schema=@Schema(implementation = Receptor.class))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthrorized", responseCode = "401"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
                    @ApiResponse(description = "Internal Error", responseCode = "500")
            })
    public ResponseEntity<Receptor> updateReceptor(@PathVariable Long id, @RequestBody Receptor receptorBody) {
        Receptor newEntity = pessoaService.updateReceptor(id, receptorBody);
        return ResponseEntity.ok().body(newEntity);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Deleta uma pessoa em especifico.", description = "Deleta uma pessoa em especifico.",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthrorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Pessoa>delete(@PathVariable Long id){
        pessoaService.delete(id);
        return  ResponseEntity.noContent().build();
    }



}
