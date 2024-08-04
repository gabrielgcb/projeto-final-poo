package br.com.ufg.poo.modelos.impl;

import br.com.ufg.poo.modelos.base.ObraDeArte;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um artista que pode criar obras de arte.
 */
public class Artista {
    private static int contadorID;
    private final int id;
    private String nome;
    private LocalDate dataNascimento;
    private LocalDate dataFalecimento;
    private String nacionalidade;
    private String biografia;
    private List<ObraDeArte> obras;

    /**
     * Construtor para criar um artista com todas as informações fornecidas.
     *
     * @param nome           Nome do artista.
     * @param dataNascimento Data de nascimento do artista.
     * @param dataFalecimento Data de falecimento do artista (pode ser null se ainda vivo).
     * @param nacionalidade  Nacionalidade do artista.
     * @param biografia      Biografia do artista.
     */
    public Artista(String nome, LocalDate dataNascimento, LocalDate dataFalecimento, String nacionalidade, String biografia) {
        this.id = ++contadorID;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.dataFalecimento = dataFalecimento;
        this.nacionalidade = nacionalidade;
        this.biografia = biografia;
        this.obras = new ArrayList<>();
    }

    /**
     * Construtor para criar um artista com apenas o nome.
     *
     * @param nome Nome do artista.
     */
    public Artista(String nome) {
        this.id = ++contadorID;
        this.nome = nome;
        this.obras = new ArrayList<>();
    }

    /**
     * Adiciona uma obra à lista de obras do artista.
     *
     * @param obra A obra de arte a ser adicionada.
     */
    public void adicionarObra(ObraDeArte obra) {
        obras.add(obra);
    }

    /**
     * Obtém o ID do artista.
     *
     * @return O ID do artista.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtém o nome do artista.
     *
     * @return O nome do artista.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém a data de nascimento do artista.
     *
     * @return A data de nascimento do artista.
     */
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Obtém a data de falecimento do artista.
     *
     * @return A data de falecimento do artista ou null se ainda vivo.
     */
    public LocalDate getDataFalecimento() {
        return dataFalecimento;
    }

    /**
     * Obtém a nacionalidade do artista.
     *
     * @return A nacionalidade do artista.
     */
    public String getNacionalidade() {
        return nacionalidade;
    }

    /**
     * Obtém a biografia do artista.
     *
     * @return A biografia do artista.
     */
    public String getBiografia() {
        return biografia;
    }

    /**
     * Obtém a lista de obras do artista.
     *
     * @return A lista de obras do artista.
     */
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

    /**
     * Converte os dados do artista em uma string formatada para salvar em arquivo.
     *
     * @return A string formatada.
     */
    public String toArquivo() {
        return String.join("|",
                String.valueOf(id),
                nome,
                dataNascimento != null ? dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "",
                dataFalecimento != null ? dataFalecimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "",
                nacionalidade,
                biografia);
    }

    /**
     * Cria uma instância de {@link Artista} a partir de uma string formatada de arquivo.
     *
     * @param linha A string formatada contendo os dados do artista.
     * @return Uma nova instância de {@link Artista}.
     * @throws IllegalArgumentException Se o formato da linha for inválido.
     */
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
