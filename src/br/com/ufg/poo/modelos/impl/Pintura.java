package br.com.ufg.poo.modelos.impl;

import br.com.ufg.poo.modelos.base.ObraDeArte;
import br.com.ufg.poo.utilitarias.GerenciadorDeArtistas;

/**
 * Representa uma pintura, uma forma específica de obra de arte.
 */
public class Pintura extends ObraDeArte {
    private static int contadorID;
    private final int id;
    private String tecnica;
    private String dimensoes;

    /**
     * Construtor para criar uma nova pintura com um artista associado.
     *
     * @param titulo     O título da pintura.
     * @param artista    O artista que criou a pintura.
     * @param ano        O ano de criação da pintura.
     * @param descricao  A descrição da pintura.
     * @param tecnica    A técnica utilizada na pintura.
     * @param dimensoes  As dimensões da pintura.
     */
    public Pintura(String titulo, Artista artista, int ano, String descricao, String tecnica, String dimensoes) {
        super(titulo, artista, ano, descricao);
        this.tecnica = tecnica;
        this.dimensoes = dimensoes;
        this.id = ++contadorID;
    }

    /**
     * Construtor para criar uma nova pintura sem um artista associado.
     *
     * @param titulo     O título da pintura.
     * @param ano        O ano de criação da pintura.
     * @param descricao  A descrição da pintura.
     * @param tecnica    A técnica utilizada na pintura.
     * @param dimensoes  As dimensões da pintura.
     */
    public Pintura(String titulo, int ano, String descricao, String tecnica, String dimensoes) {
        super(titulo, ano, descricao);
        this.tecnica = tecnica;
        this.dimensoes = dimensoes;
        this.id = ++contadorID;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.printf("""
                Tipo de obra: %s
                Técnica: %s
                Dimensões: %s
                %n""", this.getClass().getSimpleName(), tecnica, dimensoes
        );
    }

    @Override
    public void avaliar(int nota) {
        // Implementação de avaliação
    }

    @Override
    public void exibirAvaliacao() {
        // Implementação de exibição de avaliação
    }

    /**
     * Obtém a técnica utilizada na pintura.
     *
     * @return A técnica utilizada.
     */
    public String getTecnica() {
        return tecnica;
    }

    /**
     * Define a técnica utilizada na pintura.
     *
     * @param tecnica A técnica a ser definida.
     */
    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    /**
     * Obtém as dimensões da pintura.
     *
     * @return As dimensões da pintura.
     */
    public String getDimensoes() {
        return dimensoes;
    }

    /**
     * Define as dimensões da pintura.
     *
     * @param dimensoes As dimensões a serem definidas.
     */
    public void setDimensoes(String dimensoes) {
        this.dimensoes = dimensoes;
    }

    @Override
    public String toArquivo() {
        return String.join("|",
                super.toArquivo(),
                tecnica,
                dimensoes
        );
    }

    /**
     * Cria uma instância de {@link Pintura} a partir de uma string formatada de arquivo.
     *
     * @param linha A string formatada contendo os dados da pintura.
     * @return Uma nova instância de {@link Pintura}.
     * @throws IllegalArgumentException Se o formato da linha for inválido.
     */
    public static Pintura fromArquivo(String linha) {
        String[] partes = linha.split("\\|");

        if (partes.length < 6) {
            throw new IllegalArgumentException("Formato de linha inválido: " + linha);
        }

        Artista artista = partes[1].isEmpty() ? null : GerenciadorDeArtistas.getArtistaPorId(Integer.parseInt(partes[1]));

        try {
            return new Pintura(partes[0], artista, Integer.parseInt(partes[2]), partes[3], partes[4], partes[5]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato numérico inválido na linha: " + linha, e);
        }
    }
}
