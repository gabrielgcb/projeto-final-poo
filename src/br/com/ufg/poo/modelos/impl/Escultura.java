package br.com.ufg.poo.modelos.impl;

import br.com.ufg.poo.interfaces.Exibivel;
import br.com.ufg.poo.modelos.base.ObraDeArte;

public class Escultura extends ObraDeArte {
    private static int contadorID;
    private final int id;
    private String material;
    private double altura;

    public Escultura(String titulo, Artista artista, int ano, String descricao, String material, double altura) {
        super(titulo, artista, ano, descricao);
        this.material = material;
        this.altura = altura;
        this.id = ++contadorID;
    }

    public Escultura(String titulo, int ano, String descricao, String material, double altura) {
        super(titulo, ano, descricao);
        this.material = material;
        this.altura = altura;
        this.id = ++contadorID;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.printf("""
                Tipo de obra: %s
                Material: %s
                Altura: %fm
                %n""", this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.') + 1), material, altura
        );
    }

    @Override
    public void avaliar(int nota) {

    }

    @Override
    public void exibirAvaliacao() {

    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }


}
