package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.Resposta;
import br.com.alura.forum.model.StatusTopico;
import br.com.alura.forum.model.Topico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetalhesDoTopicoDTO {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;
    private StatusTopico statusTopico;
    private List<RespostaDTO> respostas = new ArrayList<>();

    public DetalhesDoTopicoDTO(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.nomeAutor = topico.getAutor().getNome();
        this.statusTopico = topico.getStatus();

//        topico.getRespostas().forEach(m -> respostas.add(new RespostaDTO(m))); forma tradicional com foreach e lambda (um pouco mais lento que com o stream)

        this.respostas = topico.getRespostas().stream().map(RespostaDTO::new).collect(Collectors.toList()); //forma com stream do java 8
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public StatusTopico getStatusTopico() {
        return statusTopico;
    }

    public List<RespostaDTO> getRespostas() {
        return respostas;
    }
}
