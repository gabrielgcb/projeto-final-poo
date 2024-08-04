package br.com.ufg.poo.modelos.impl;

import br.com.ufg.poo.modelos.base.ObraDeArte;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Artista {
    private static int contadorID;
    private final int id;
    private String nome;
    private LocalDate dataNascimento;
    private LocalDate dataFalecimento;
    private String nacionalidade;
    private String biografia;
    private List<ObraDeArte> obras;

    // Construtor padrão para criar um novo artista com todas as informações
    public Artista(String nome, LocalDate dataNascimento, LocalDate dataFalecimento, String nacionalidade, String biografia) {
        this.id = ++contadorID;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.dataFalecimento = dataFalecimento;
        this.nacionalidade = nacionalidade;
        this.biografia = biografia;
        this.obras = new ArrayList<>();
    }

    // Construtor para criar um artista "Desconhecido"
    public Artista(String nome) {
        this.id = ++contadorID;
        this.nome = nome;
        this.obras = new ArrayList<>();
    }

    // Adiciona uma obra à lista de obras do artista
    public void adicionarObra(ObraDeArte obra) {
        obras.add(obra);
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public LocalDate getDataFalecimento() {
        return dataFalecimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public String getBiografia() {
        return biografia;
    }

    public List<ObraDeArte> getObras() {
        return obras;
    }

    @Override
    public String toString() {
        return String.format("Nome: %s, Data de Nascimento: %s, Data de Falecimento: %s, Nacionalidade: %s, Biografia: %s",
                nome,
                dataNascimento != null ? dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "Desconhecida",
                dataFalecimento != null ? dataFalecimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "Ainda Vivo",
                nacionalidade,
                biografia);
    }

    // Converte os dados do artista em uma string formatada para salvar em arquivo
    public String toArquivo() {
        return String.join("|",
                String.valueOf(id),
                nome,
                dataNascimento != null ? dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "",
                dataFalecimento != null ? dataFalecimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "",
                nacionalidade,
                biografia);
    }

    // Cria um objeto Artista a partir de uma string formatada de arquivo
    public static Artista fromArquivo(String linha) {
        String[] partes = linha.split("\\|");

        // Verificar se a linha contém o número correto de partes
        if (partes.length < 6) {
            throw new IllegalArgumentException("Formato de linha inválido: " + linha);
        }

        LocalDate dataNascimento = partes[2].isEmpty() ? null : LocalDate.parse(partes[2], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate dataFalecimento = partes[3].isEmpty() ? null : LocalDate.parse(partes[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return new Artista(partes[1], dataNascimento, dataFalecimento, partes[4], partes[5]);
    }

}
