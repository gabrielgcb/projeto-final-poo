package br.com.ufg.poo.modelos.impl;

import br.com.ufg.poo.interfaces.Exibivel;
import br.com.ufg.poo.modelos.base.ObraDeArte;

public class Pintura extends ObraDeArte {
    private String tecnica;
    private String dimensoes;

    Pintura(String titulo, Artista artista, int ano, String descricao, String tecnica, String dimensoes) {
        super(titulo, artista, ano, descricao);
        this.tecnica = tecnica;
        this.dimensoes = dimensoes;
    }

    Pintura(String titulo, int ano, String descricao, String tecnica, String dimensoes) {
        super(titulo, ano, descricao);
        this.tecnica = tecnica;
        this.dimensoes = dimensoes;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.printf("""
                Tipo de obra: %s
                Técnica: %s
                Dimensões: %f
                %n""", this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.') + 1), tecnica, dimensoes
        );
    }

    @Override
    public void avaliar(int nota) {

    }

    @Override
    public void exibirAvaliacao() {

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


}
