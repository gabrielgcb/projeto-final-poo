package br.com.ufg.poo.modelos.impl;

import br.com.ufg.poo.modelos.base.ObraDeArte;

import java.util.List;

public class Colecao {
    private static int contadorID;
    private final int id;
    private String nome;
    private List<ObraDeArte> obras;

    public Colecao(String nome, List<ObraDeArte> obras) {
        this.nome = nome;
        this.obras = obras;
        this.id = ++contadorID;
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

    public List<ObraDeArte> getObras() {
        return obras;
    }

    public void setObras(List<ObraDeArte> obras) {
        this.obras = obras;
    }
}
