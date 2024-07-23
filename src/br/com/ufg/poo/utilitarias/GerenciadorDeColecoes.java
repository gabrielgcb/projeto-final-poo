package br.com.ufg.poo.utilitarias;

import br.com.ufg.poo.modelos.impl.Colecao;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeColecoes {
    private static List<Colecao> colecoes = new ArrayList<>();

    public static void adicionarColecao(Colecao colecao) {
        colecoes.add(colecao);
    }

    public static List<Colecao> getTodasColecoes() {
        return new ArrayList<>(colecoes);
    }

    public static Colecao getColecaoPorId(int id) {
        for (Colecao colecao : colecoes) {
            if (colecao.getId() == id) {
                return colecao;
            }
        }
        return null;
    }
}
