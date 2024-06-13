package br.com.ufg.poo.modelos.impl;

import br.com.ufg.poo.interfaces.Exibivel;
import br.com.ufg.poo.modelos.base.ObraDeArte;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Artista implements Exibivel {
    private static int contadorID;
    private final int id;
    private String nome;
    private LocalDate dataDeNascimento;
    private LocalDate dataDeFalecimento;
    private String nacionalidade;
    private String biografia;
    private List<ObraDeArte> obras;

    public Artista(String nome, LocalDate dataDeNascimento, LocalDate dataDeFalecimento, String nacionalidade, String biografia) {
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.dataDeFalecimento = dataDeFalecimento;
        this.nacionalidade = nacionalidade;
        this.biografia = biografia;
        this.obras = new ArrayList<>();
        this.id = ++contadorID;
    }

    public Artista(String nome, LocalDate dataDeNascimento, String nacionalidade, String biografia) {
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.nacionalidade = nacionalidade;
        this.biografia = biografia;
        this.obras = new ArrayList<>();
        this.id = ++contadorID;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        if(dataDeNascimento != null) {
            System.out.println("Data de Nascimento: " + dataDeNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        if(dataDeFalecimento != null) {
            System.out.println("Data de Falecimento: " + dataDeFalecimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        System.out.println("Biografia: " + biografia);
        System.out.println("Obras: ");
        for (ObraDeArte obra : obras) {
            System.out.println("-- " + obra.getTitulo());
        }
    }

    public void adicionarObra(ObraDeArte obra) {
        this.obras.add(obra);
    }

    public void removerObra(ObraDeArte obra) {
        this.obras.remove(obra);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public LocalDate getDataDeFalecimento() {
        return dataDeFalecimento;
    }

    public void setDataDeFalecimento(LocalDate dataDeFalecimento) {
        this.dataDeFalecimento = dataDeFalecimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public List<ObraDeArte> getObras() {
        return obras;
    }

    public void setObras(List<ObraDeArte> obras) {
        this.obras = obras;
    }
}
