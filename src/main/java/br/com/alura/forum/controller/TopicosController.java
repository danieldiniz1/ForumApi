package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.DetalhesDoTopicoDTO;
import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/nome")
    public List<TopicoDTO> listaPeloNome(String nomeCurso) {
        if (nomeCurso == null){
            return TopicoDTO.converter(topicoRepository.findAll());
        }
        return TopicoDTO.converter(topicoRepository.findByCursoNome(nomeCurso));
     }

     @GetMapping("/id")
    public String listaPeloId(Long id){
        return  topicoRepository.findById(id).get().getTitulo();
     }

     @PostMapping("/cadastro")
     public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder){
        Topico topico  = topicoForm.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
     }

     @GetMapping("/{id}")
     public ResponseEntity<DetalhesDoTopicoDTO> detalhar(@PathVariable Long id) {
         Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()){
            return ResponseEntity.ok(new DetalhesDoTopicoDTO(topico.get()));
        }
        return ResponseEntity.notFound().build();
     }

     @PutMapping("/{id}") @Transactional
     public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
         Optional<Topico> topicoValid = topicoRepository.findById(id);
         if (topicoValid.isPresent()){
             Topico topico = form.atualizar(id, topicoRepository);
             return ResponseEntity.ok(new TopicoDTO(topico));
         }
         return ResponseEntity.notFound().build();
     }

     @DeleteMapping("/{id}") @Transactional
     public ResponseEntity remover(@PathVariable Long id){
         Optional<Topico> topico = topicoRepository.findById(id);
         if (topico.isPresent()){
             topicoRepository.deleteById(id);
             return ResponseEntity.ok().build();
         }
         return ResponseEntity.notFound().build();
     }

}
