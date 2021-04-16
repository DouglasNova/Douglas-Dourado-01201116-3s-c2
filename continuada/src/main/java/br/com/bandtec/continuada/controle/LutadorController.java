package br.com.bandtec.continuada.controle;

import br.com.bandtec.continuada.dominio.Lutador;
import br.com.bandtec.continuada.repositorio.LutadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lutadores")
public class LutadorController {


    @Autowired
    private LutadorRepository repository;

    @GetMapping
    public ResponseEntity getLutadores(){
        return ResponseEntity.status(200).body(repository.OrderByForcaGolpeDesc());
    }

    @GetMapping("/contagem-vivos")
    public ResponseEntity getVivos(){
        return ResponseEntity.status(200).body(repository.findByVivoTrue());
    }

    @GetMapping("/mortos")
    public ResponseEntity getmortos(){
        return ResponseEntity.status(200).body(repository.findByVivoFalse());
    }

    @PostMapping("/{id}/concentrar")
    public ResponseEntity postLutador(@PathVariable int id){
        if (repository.existsById(id)){
            Optional<Lutador> optional = repository.findById(id);
            Lutador lutador = optional.get();

            if (lutador.getConcentracoesRealizadas() < 3){
                lutador.setConcentracoesRealizadas(lutador.getConcentracoesRealizadas() + 1);
                lutador.setVida(lutador.getVida() * 1.15);
                return ResponseEntity.status(200).build();
            }else{
                return ResponseEntity.status(400).body("Lutador já se concentrou 3 vezes!");
            }
        }else{
            return ResponseEntity.status(404).build();
        }

    }

    @PostMapping()
    public ResponseEntity postLutador(@RequestBody @Valid Lutador novoLutador){
        repository.save(novoLutador);
        return ResponseEntity.status(201).build();
    }

    @PostMapping
    public ResponseEntity postgolpe(@RequestBody int novoLutador){
        return ResponseEntity.status(201).build();
    }

}
