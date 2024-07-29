package br.com.ufg.poo.utilitarias;

import br.com.ufg.poo.modelos.impl.Artista;
import br.com.ufg.poo.modelos.impl.Colecao;
import br.com.ufg.poo.modelos.impl.Escultura;
import br.com.ufg.poo.modelos.base.ObraDeArte;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MenuUtil {

    public static int exibirMenuPrincipal() {
        String[] opcoes = {
                "Cadastrar novo Artista",
                "Cadastrar nova Obra",
                "Adicionar Obra a Coleção",
                "Visualizar Artistas Cadastrados",
                "Visualizar Obras Cadastradas",
                "Salvar Dados",
                "Sair"
        };

        // Criar um JList com as opções
        JList<String> list = new JList<>(opcoes);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(opcoes.length);

        // Adicionar o JList a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(list);

        // Mostrar o JOptionPane com o JList
        int option = JOptionPane.showOptionDialog(
                null,
                scrollPane,
                "Menu Principal",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Selecionar", "Cancelar"},
                "Selecionar"
        );

        if (option == JOptionPane.OK_OPTION) {
            return list.getSelectedIndex();
        } else {
            return -1; // Indica que o usuário cancelou ou fechou a janela
        }
    }

    public static void cadastrarNovoArtista() {
        String nome = JOptionPane.showInputDialog("Digite o nome do artista:");
        String dataNascimentoStr = JOptionPane.showInputDialog("Digite a data de nascimento do artista (dd/MM/yyyy):");
        String dataFalecimentoStr = JOptionPane.showInputDialog("Digite a data de falecimento do artista (dd/MM/yyyy) ou deixe vazio se ainda estiver vivo:");
        String nacionalidade = JOptionPane.showInputDialog("Digite a nacionalidade do artista:");
        String biografia = JOptionPane.showInputDialog("Digite a biografia do artista:");

        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate dataFalecimento = dataFalecimentoStr.isEmpty() ? null : LocalDate.parse(dataFalecimentoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Artista artista = new Artista(nome, dataNascimento, dataFalecimento, nacionalidade, biografia);
        GerenciadorDeArtistas.adicionarArtista(artista);
    }

    public static void mostrarArtistas(String artistas) {
        JOptionPane.showMessageDialog(null, artistas, "Artistas Cadastrados", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarObras(String obras) {
        JOptionPane.showMessageDialog(null, obras, "Obras Cadastradas", JOptionPane.INFORMATION_MESSAGE);
    }

    public static Artista selecionarOuCadastrarArtista() {
        int opcao = JOptionPane.showConfirmDialog(
                null,
                "Deseja selecionar um artista existente?",
                "Selecionar Artista",
                JOptionPane.YES_NO_OPTION
        );

        if (opcao == JOptionPane.YES_OPTION) {
            return GerenciadorDeArtistas.selecionarArtista();
        } else {
            cadastrarNovoArtista();
            return GerenciadorDeArtistas.selecionarArtista();
        }
    }
}
