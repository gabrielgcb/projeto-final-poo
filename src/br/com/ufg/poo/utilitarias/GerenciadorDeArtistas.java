package br.com.ufg.poo.utilitarias;

import br.com.ufg.poo.modelos.impl.Artista;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorDeArtistas {
    private static final List<Artista> artistas = new ArrayList<>();

    // Adiciona um novo artista à lista
    public static void adicionarArtista(Artista artista) {
        artistas.add(artista);
    }

    // Seleciona um artista da lista usando um diálogo
    public static Artista selecionarArtista() {
        if (artistas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum artista cadastrado.");
            return null;
        }

        String[] nomes = artistas.stream().map(Artista::getNome).toArray(String[]::new);
        String escolhido = (String) JOptionPane.showInputDialog(
                null,
                "Selecione um artista:",
                "Selecionar Artista",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nomes,
                nomes[0]
        );

        if (escolhido != null) {
            for (Artista artista : artistas) {
                if (artista.getNome().equals(escolhido)) {
                    return artista;
                }
            }
        }
        return null;
    }

    // Obtém um artista pelo ID
    public static Artista getArtistaPorId(int id) {
        for (Artista artista : artistas) {
            if (artista.getId() == id) {
                return artista;
            }
        }
        return null;
    }

    // Retorna a lista de todos os artistas
    public static List<Artista> getTodosArtistas() {
        return new ArrayList<>(artistas);
    }

    // Retorna a lista de artistas (método utilizado para salvar os dados)
    public static List<Artista> getArtistas() {
        return new ArrayList<>(artistas);
    }

    // Lista os artistas cadastrados como uma string formatada
    public static String listarArtistas() {
        if (artistas.isEmpty()) {
            return "Nenhum artista cadastrado.";
        }
        return artistas.stream().map(Artista::toString).collect(Collectors.joining("\n"));
    }
}
