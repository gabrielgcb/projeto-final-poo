package br.com.ufg.poo.utilitarias;

import br.com.ufg.poo.modelos.impl.Artista;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorDeArtistas {
    private static final List<Artista> artistas = new ArrayList<>();

    public static void adicionarArtista(Artista artista) {
        artistas.add(artista);
    }

    public static Artista selecionarArtista() {
        if (artistas.isEmpty()) {
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

    public static Artista getArtistaPorId(int id) {
        for (Artista artista : artistas) {
            if (artista.getId() == id) {
                return artista;
            }
        }
        return null;
    }

    public static List<Artista> getArtistas() {
        return new ArrayList<>(artistas);
    }

    public static String listarArtistas() {
        if (artistas.isEmpty()) {
            return "Nenhum artista cadastrado.";
        }
        return artistas.stream().map(Artista::toString).collect(Collectors.joining("\n"));
    }
}
