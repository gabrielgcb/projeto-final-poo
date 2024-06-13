package br.com.ufg.poo.modelos.base;

import br.com.ufg.poo.interfaces.Avaliavel;
import br.com.ufg.poo.interfaces.Exibivel;
import br.com.ufg.poo.modelos.impl.Artista;

public abstract class ObraDeArte implements Exibivel, Avaliavel {
    private static int contadorID;
    private final int id;
    private String titulo;
    private Artista artista;
    private int ano;
    private String descricao;

    public ObraDeArte(String titulo, Artista artista, int ano, String descricao) {
        this.titulo = titulo;
        this.artista = artista;
        artista.adicionarObra(this);
        this.ano = ano;
        this.descricao = descricao;
        this.id = ++contadorID;
    }

    public ObraDeArte(String titulo, int ano, String descricao) {
        this.titulo = titulo;
        this.ano = ano;
        this.descricao = descricao;
        this.id = ++contadorID;
    }

    @Override
    public void exibirInformacoes() {
        System.out.printf("""
                Título da obra: %s
                Artista: %s
                Ano: %d
                Descricao: %s 
                Id: %d
                """, titulo, artista.getNome(), ano, descricao, id
        );
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
