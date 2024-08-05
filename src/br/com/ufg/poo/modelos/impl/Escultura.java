package br.com.ufg.poo.modelos.impl;

import br.com.ufg.poo.modelos.base.ObraDeArte;

/**
 * Representa uma escultura, uma forma de obra de arte.
 */
public class Escultura extends ObraDeArte {
    private String material;
    private double altura;

    /**
     * Construtor para criar uma nova escultura.
     *
     * @param titulo     O título da escultura.
     * @param artista    O artista que criou a escultura.
     * @param ano        O ano de criação da escultura.
     * @param descricao  A descrição da escultura.
     * @param material   O material da escultura.
     * @param altura     A altura da escultura.
     */
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
    public void exibirAvaliacao() {
        if (avaliacao == null) {
            System.out.println("A obra ainda não foi avaliada.");
        } else {
            System.out.println("Nota da avaliação: " + avaliacao);
        }
    }
}
