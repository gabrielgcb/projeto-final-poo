package br.com.ufg.poo.modelos.base;

import br.com.ufg.poo.interfaces.Avaliavel;
import br.com.ufg.poo.interfaces.Exibivel;
import br.com.ufg.poo.modelos.impl.Artista;
import br.com.ufg.poo.modelos.impl.Pintura;
import br.com.ufg.poo.utilitarias.GerenciadorDeArtistas;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe abstrata que representa uma obra de arte.
 * Implementa as interfaces {@link Exibivel} e {@link Avaliavel}.
 */
public abstract class ObraDeArte implements Exibivel, Avaliavel {
    private static int contadorID;
    private final int id;
    private String titulo;
    private Artista artista;
    private int ano;
    private String descricao;

    private static final Map<Integer, ObraDeArte> obrasMap = new HashMap<>();

    /**
     * Construtor para criar uma nova obra de arte com um artista associado.
     *
     * @param titulo     O título da obra.
     * @param artista    O artista que criou a obra.
     * @param ano        O ano de criação da obra.
     * @param descricao  A descrição da obra.
     */
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

    /**
     * Construtor para criar uma nova obra de arte sem um artista associado.
     *
     * @param titulo     O título da obra.
     * @param ano        O ano de criação da obra.
     * @param descricao  A descrição da obra.
     */
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

    /**
     * Converte os dados da obra para uma string formatada para salvar em arquivo.
     *
     * @return A string formatada.
     */
    public String toArquivo() {
        return String.join("|",
                titulo,
                artista != null ? String.valueOf(artista.getId()) : "",
                String.valueOf(ano),
                descricao,
                String.valueOf(id));
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

        try {
            String titulo = partes[0];
            Artista artista = partes[1].isEmpty() ? null : GerenciadorDeArtistas.getArtistaPorId(Integer.parseInt(partes[1]));
            int ano = Integer.parseInt(partes[2]);
            String descricao = partes[3];
            String tecnica = partes[4];
            String dimensoes = partes[5];

            return new Pintura(titulo, artista, ano, descricao, tecnica, dimensoes);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato numérico inválido na linha: " + linha);
        }
    }

    /**
     * Obtém uma obra de arte pelo seu ID.
     *
     * @param id O ID da obra de arte.
     * @return A obra de arte correspondente ao ID fornecido.
     */
    public static ObraDeArte getObraPorId(int id) {
        return obrasMap.get(id);
    }
}
