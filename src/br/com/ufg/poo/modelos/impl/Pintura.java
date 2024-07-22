package br.com.ufg.poo.modelos.impl;

import br.com.ufg.poo.modelos.base.ObraDeArte;
import br.com.ufg.poo.utilitarias.GerenciadorDeArtistas;

public class Pintura extends ObraDeArte {
    private static int contadorID;
    private final int id;
    private String tecnica;
    private String dimensoes;

    public Pintura(String titulo, Artista artista, int ano, String descricao, String tecnica, String dimensoes) {
        super(titulo, artista, ano, descricao);
        this.tecnica = tecnica;
        this.dimensoes = dimensoes;
        this.id = ++contadorID;
    }

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

    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    public String getDimensoes() {
        return dimensoes;
    }

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

    public static Pintura fromArquivo(String linha) {
        String[] partes = linha.split("\\|");
        Artista artista = partes[1].isEmpty() ? null : GerenciadorDeArtistas.getArtistaPorId(Integer.parseInt(partes[1]));

        return new Pintura(partes[0], artista, Integer.parseInt(partes[2]), partes[3], partes[4], partes[5]);
    }
}
