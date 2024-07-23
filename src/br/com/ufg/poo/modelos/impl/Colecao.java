package br.com.ufg.poo.modelos.impl;

import br.com.ufg.poo.modelos.base.ObraDeArte;

import java.util.ArrayList;
import java.util.List;

public class Colecao {
    private static int contadorID;
    private final int id;
    private String nome;
    private List<ObraDeArte> obras;

    public Colecao(String nome) {
        this.nome = nome;
        this.id = ++contadorID;
        this.obras = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ObraDeArte> getObras() {
        return obras;
    }

    public void adicionarObra(ObraDeArte obra) {
        this.obras.add(obra);
    }

    @Override
    public String toString() {
        return nome;
    }
}
