package br.com.ufg.poo.modelos.impl;

import br.com.ufg.poo.modelos.base.ObraDeArte;

public class Escultura extends ObraDeArte {
    private String material;
    private double altura;

    public Escultura(String titulo, Artista artista, int ano, String descricao, String material, double altura) {
        super(titulo, artista, ano, descricao);
        this.material = material;
        this.altura = altura;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.printf("Material: %s, Altura: %.2f\n", material, altura);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Material: %s, Altura: %.2f", material, altura);
    }

    @Override
    public String toArquivo() {
        return super.toArquivo() + "|" + material + "|" + altura;
    }

    @Override
    public void avaliar(int nota) {

    }

    @Override
    public void exibirAvaliacao() {

    }
}
