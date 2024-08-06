package com.trabalhoFinalPOO.demo.services;

import com.trabalhoFinalPOO.demo.dto.AnimalDTO;
import com.trabalhoFinalPOO.demo.dto.DoadorDTO;
import com.trabalhoFinalPOO.demo.dto.PessoaDTO;
import com.trabalhoFinalPOO.demo.dto.ReceptorDTO;
import com.trabalhoFinalPOO.demo.entities.*;
import com.trabalhoFinalPOO.demo.projetions.AnimalProjections;
import com.trabalhoFinalPOO.demo.repositories.*;
import com.trabalhoFinalPOO.demo.services.exceptions.DatabaseException;
import com.trabalhoFinalPOO.demo.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private DoadorRepository doadorRepository;

    @Autowired
    private ReceptorRepository receptorRepository;

    @Autowired
    private AnimalRespository animalRespository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional(readOnly = true)
    public List<Doador> findAll(){
        List<Doador> pessoaList = doadorRepository.findAll();
        return pessoaList;
    }

    @Transactional(readOnly = true)
    public List<Receptor> findAllReceptor(){
        List<Receptor> receptorList = receptorRepository.findAll();

        return receptorList;
    }

    @Transactional(readOnly = true)
    public PessoaDTO searchPessoaByEmail(String email, String password){
        Pessoa pessoa = pessoaRepository.searchByEmail(email);

        if(pessoa.getSenha().matches(password)){
            if(pessoa instanceof Doador){
                DoadorDTO doadorDTO = new DoadorDTO(pessoa.getId(), pessoa.getNome(), pessoa.getTelefone(), pessoa.getEmail(), ((Doador) pessoa).getTipo() );
                return doadorDTO;
            }  else if (pessoa instanceof Receptor){
                ReceptorDTO receptorDTO = new ReceptorDTO(pessoa.getId(), pessoa.getNome(), pessoa.getTelefone(), pessoa.getEmail(), ((Receptor) pessoa).getTipo());
                return receptorDTO;
            } else
                return new PessoaDTO(pessoa.getId(), pessoa.getNome(), pessoa.getTelefone(), pessoa.getEmail());

        } else {
            throw new ResourceNotFoundException("Usuario nao encontrado");
        }
    }


    @Transactional
    public Doador createDoador(@RequestBody Doador doador){
        return pessoaRepository.save(doador);
    }
    @Transactional
    public Receptor createReceptor(@RequestBody Receptor receptor){
        return pessoaRepository.save(receptor);
    }

    @Transactional
    public Pessoa findById(Long id) {
        Optional<Pessoa> obj  = pessoaRepository.findById(id);
        Pessoa entity = obj.orElseThrow(()-> new RuntimeException("Entity not found"));
        return entity;
    }

//    @Transactional
//    public Receptor findByIdReceptor(Long id) {
//        Optional<Receptor> obj  = receptorRepository.findById(id);
//        Receptor entity = obj.orElseThrow(()-> new RuntimeException("Entity not found"));
//        return entity;
//    }
    @Transactional
    public List<AnimalProjections> searchAnimalsByUser(Long id){
        List<AnimalProjections> list  = pessoaRepository.searchAnimalByProfile(id);
        return list;
    }

    @Transactional
    public Pessoa searchByEmail(String email){
        Pessoa pessoa = pessoaRepository.searchByEmail(email);
        return pessoa;
    }


    @Transactional
    public void adotar(Long animalId, Long doadorId){
        Animal animal = animalRespository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
        Receptor receptor = receptorRepository.findById(doadorId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        animal.setPessoa(receptor);
        animal.setAdopted(true);
        receptorRepository.save(receptor);
    }
    @Transactional
    public Receptor updateReceptor(Long id, Receptor entityBody) {
        try {
            Pessoa entity = pessoaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Id " + id + " não encontrado"));
            Receptor receptor = (Receptor) entity;
            receptor.setNome(entityBody.getNome());
            receptor.setSenha(entityBody.getSenha());
            receptor.setEmail(entityBody.getEmail());
            receptor.setAnimais(entityBody.getAnimais());
            receptor.setTelefone(entityBody.getTelefone());

            Endereco newEnd = entityBody.getEnd();
            if (newEnd != null) {
                Endereco endAtual = receptor.getEnd();
                if (endAtual != null) {

                    endAtual.setRua(newEnd.getRua());
                    endAtual.setNumero(newEnd.getNumero());
                    endAtual.setPonteDeReferencia(newEnd.getPonteDeReferencia());
                    endAtual.setBairro(newEnd.getBairro());
                } else {
                    newEnd.setPessoa(receptor);
                    receptor.setEnd(enderecoRepository.save(newEnd));
                }
            } else {
                receptor.setEnd(null);
            }

            return pessoaRepository.save(receptor);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id " + id + " não encontrado");
        }
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!pessoaRepository.existsById(id)){
            throw new ResourceNotFoundException("Usuario nao encontrado");
        }
        try {
            pessoaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha na integridade referencial");
        }

    }
}
