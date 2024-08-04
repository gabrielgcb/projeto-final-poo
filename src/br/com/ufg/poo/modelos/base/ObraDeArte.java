package br.com.ufg.poo.modelos.base;

import br.com.ufg.poo.interfaces.Avaliavel;
import br.com.ufg.poo.interfaces.Exibivel;
import br.com.ufg.poo.modelos.impl.Artista;
import br.com.ufg.poo.modelos.impl.Escultura;
import br.com.ufg.poo.modelos.impl.Pintura;
import br.com.ufg.poo.utilitarias.GerenciadorDeArtistas;

import java.util.HashMap;
import java.util.Map;

public abstract class ObraDeArte implements Exibivel, Avaliavel {
    private static int contadorID;
    private final int id;
    private String titulo;
    private Artista artista;
    private int ano;
    private String descricao;

    private static final Map<Integer, ObraDeArte> obrasMap = new HashMap<>();

    public ObraDeArte(String titulo, Artista artista, int ano, String descricao) {
        this.titulo = titulo;
        this.artista = artista;
        if (artista != null) {
            artista.adicionarObra(this);
        }
        this.ano = ano;
        this.descricao = descricao;
        this.id = ++contadorID;
        obrasMap.put(this.id, this);
    }

    public ObraDeArte(String titulo, int ano, String descricao) {
        this.titulo = titulo;
        this.ano = ano;
        this.descricao = descricao;
        this.id = ++contadorID;
        obrasMap.put(this.id, this);
    }

    @Override
    public void exibirInformacoes() {
        System.out.printf("""
                Título da obra: %s
                Artista: %s
                Ano: %d
                Descrição: %s 
                Id: %d
                """, titulo, artista != null ? artista.getNome() : "Desconhecido", ano, descricao, id
        );
    }

    @Override
    public String toString() {
        return String.format("Título: %s, Artista: %s, Ano: %d, Descrição: %s",
                titulo, artista != null ? artista.getNome() : "Desconhecido", ano, descricao);
    }

    public String toArquivo() {
        return String.join("|",
                titulo,
                artista != null ? String.valueOf(artista.getId()) : "",
                String.valueOf(ano),
                descricao,
                String.valueOf(id));
    }

    public static ObraDeArte fromArquivo(String linha) {
        String[] partes = linha.split("\\|");

        // Verificar se a linha contém o número correto de partes
        if (partes.length < 5) {
            throw new IllegalArgumentException("Formato de linha inválido: " + linha);
        }

        try {
            String titulo = partes[0];
            Artista artista = GerenciadorDeArtistas.getArtistaPorId(Integer.parseInt(partes[1]));
            int ano = Integer.parseInt(partes[2]);
            String descricao = partes[3];
            String material = partes[4];
            double altura = Double.parseDouble(partes[5]);

            return new Escultura(titulo, artista, ano, descricao, material, altura);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato numérico inválido na linha: " + linha);
        }
    }


    public static ObraDeArte getObraPorId(int id) {
        return obrasMap.get(id);
    }
}
