package br.com.ufg.poo;

import br.com.ufg.poo.modelos.base.ObraDeArte;
import br.com.ufg.poo.modelos.impl.Artista;
import br.com.ufg.poo.modelos.impl.Escultura;
import br.com.ufg.poo.modelos.impl.Colecao;
import br.com.ufg.poo.utilitarias.GerenciadorDeArtistas;
import br.com.ufg.poo.utilitarias.GerenciadorDeObras;
import br.com.ufg.poo.utilitarias.GerenciadorDeColecoes;
import br.com.ufg.poo.utilitarias.InputUtil;
import br.com.ufg.poo.utilitarias.MenuUtil;

import javax.swing.*;
import java.io.*;
import java.util.List;

public class MuseuMain {
    private static final String ARQUIVO_ARTISTAS = "artistas.txt";
    private static final String ARQUIVO_OBRAS = "obras.txt";

    public static void main(String[] args) {
        carregarDados();
        boolean continuar = true;

        while (continuar) {
            int escolha = MenuUtil.exibirMenuPrincipal();

            switch (escolha) {
                case 0 -> MenuUtil.cadastrarNovoArtista();
                case 1 -> cadastrarNovaObra();
                case 2 -> adicionarObraColecao();
                case 3 -> visualizarArtistas();
                case 4 -> visualizarObras();
                case 5 -> salvarDados();
                case 6 -> continuar = false;
                default -> JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }

    private static void cadastrarNovaObra() {
        Artista artista = MenuUtil.selecionarOuCadastrarArtista();

        String tituloObra = InputUtil.lerString("Digite o título da obra: ");
        int anoObra = InputUtil.lerInt("Digite o ano da obra: ");
        String descricaoObra = InputUtil.lerString("Digite a descrição da obra: ");
        String materialObra = InputUtil.lerString("Digite o material da obra: ");
        double alturaObra = InputUtil.lerDouble("Digite a altura da obra: ");

        ObraDeArte obra = new Escultura(tituloObra, artista, anoObra, descricaoObra, materialObra, alturaObra);
        GerenciadorDeObras.adicionarObra(obra);
        obra.exibirInformacoes();
    }

    private static void adicionarObraColecao() {
        List<Colecao> colecoes = GerenciadorDeColecoes.getTodasColecoes();
        List<Artista> artistas = GerenciadorDeArtistas.getTodosArtistas();

        // Adicionar opção "Desconhecido"
        Artista desconhecido = new Artista("Desconhecido");
        artistas.add(desconhecido);

        // Seleção de coleção
        Colecao[] arrayColecoes = new Colecao[colecoes.size() + 1];
        colecoes.toArray(arrayColecoes);
        arrayColecoes[colecoes.size()] = new Colecao("Nova Coleção");
        Colecao colecaoSelecionada = (Colecao) JOptionPane.showInputDialog(
                null,
                "Selecione uma coleção:",
                "Adicionar Obra à Coleção",
                JOptionPane.QUESTION_MESSAGE,
                null,
                arrayColecoes,
                arrayColecoes[0]
        );

        if (colecaoSelecionada == null) {
            return;
        }

        if (colecaoSelecionada.getNome().equals("Nova Coleção")) {
            String nomeNovaColecao = JOptionPane.showInputDialog("Digite o nome da nova coleção:");
            if (nomeNovaColecao != null && !nomeNovaColecao.trim().isEmpty()) {
                colecaoSelecionada = new Colecao(nomeNovaColecao);
                GerenciadorDeColecoes.adicionarColecao(colecaoSelecionada);
            } else {
                JOptionPane.showMessageDialog(null, "Nome da coleção inválido.");
                return;
            }
        }

        // Seleção de artista
        Artista[] arrayArtistas = new Artista[artistas.size()];
        artistas.toArray(arrayArtistas);
        Artista artistaSelecionado = (Artista) JOptionPane.showInputDialog(
                null,
                "Selecione um artista:",
                "Adicionar Obra à Coleção",
                JOptionPane.QUESTION_MESSAGE,
                null,
                arrayArtistas,
                arrayArtistas[0]
        );

        if (artistaSelecionado == null) {
            return;
        }

        // Dados da obra
        String tituloObra = JOptionPane.showInputDialog("Digite o título da obra:");
        int anoObra = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano da obra:"));
        String descricaoObra = JOptionPane.showInputDialog("Digite a descrição da obra:");
        String materialObra = JOptionPane.showInputDialog("Digite o material da obra:");
        double alturaObra = Double.parseDouble(JOptionPane.showInputDialog("Digite a altura da obra:"));

        ObraDeArte novaObra = new Escultura(tituloObra, artistaSelecionado.equals(desconhecido) ? null : artistaSelecionado, anoObra, descricaoObra, materialObra, alturaObra);
        colecaoSelecionada.adicionarObra(novaObra);
        JOptionPane.showMessageDialog(null, "Obra adicionada à coleção com sucesso.");
    }

    private static void visualizarArtistas() {
        String artistas = GerenciadorDeArtistas.listarArtistas();
        MenuUtil.mostrarArtistas(artistas);
    }

    private static void visualizarObras() {
        String obras = GerenciadorDeObras.listarObras();
        MenuUtil.mostrarObras(obras);
    }

    private static void salvarDados() {
        try (PrintWriter writerArtistas = new PrintWriter(new FileWriter(ARQUIVO_ARTISTAS));
             PrintWriter writerObras = new PrintWriter(new FileWriter(ARQUIVO_OBRAS))) {

            List<Artista> artistas = GerenciadorDeArtistas.getArtistas();
            for (Artista artista : artistas) {
                writerArtistas.println(artista.toArquivo());
            }

            List<ObraDeArte> obras = GerenciadorDeObras.getObras();
            for (ObraDeArte obra : obras) {
                writerObras.println(obra.toArquivo());
            }

            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar dados: " + e.getMessage());
        }
    }

    private static void carregarDados() {
        try (BufferedReader readerArtistas = new BufferedReader(new FileReader(ARQUIVO_ARTISTAS));
             BufferedReader readerObras = new BufferedReader(new FileReader(ARQUIVO_OBRAS))) {

            String linha;
            while ((linha = readerArtistas.readLine()) != null) {
                Artista artista = Artista.fromArquivo(linha);
                GerenciadorDeArtistas.adicionarArtista(artista);
            }

            while ((linha = readerObras.readLine()) != null) {
                ObraDeArte obra = ObraDeArte.fromArquivo(linha);
                GerenciadorDeObras.adicionarObra(obra);
            }

        } catch (FileNotFoundException e) {
            // Arquivo não encontrado, pode ser a primeira execução
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }
    }
}
