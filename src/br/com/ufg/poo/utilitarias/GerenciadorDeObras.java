package br.com.ufg.poo.utilitarias;

import br.com.ufg.poo.modelos.base.ObraDeArte;
import br.com.ufg.poo.excecoes.ObraJaExisteException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorDeObras {
    private static final List<ObraDeArte> obras = new ArrayList<>();

    public static void adicionarObra(ObraDeArte obra) throws ObraJaExisteException {
        if (obras.stream().anyMatch(o -> o.getTitulo().equalsIgnoreCase(obra.getTitulo()))) {
            throw new ObraJaExisteException("A obra com o título \"" + obra.getTitulo() + "\" já existe.");
        }
        obras.add(obra);
    }

    public static List<ObraDeArte> getObras() {
        return new ArrayList<>(obras);
    }

    public static String listarObras() {
        if (obras.isEmpty()) {
            return "Nenhuma obra cadastrada.";
        }
        return obras.stream().map(ObraDeArte::toString).collect(Collectors.joining("\n"));
    }
}
