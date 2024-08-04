package br.com.ufg.poo;

import br.com.ufg.poo.excecoes.ObraJaExisteException;
import br.com.ufg.poo.modelos.base.ObraDeArte;
import br.com.ufg.poo.modelos.impl.Artista;
import br.com.ufg.poo.modelos.impl.Escultura;
import br.com.ufg.poo.utilitarias.GerenciadorDeArtistas;
import br.com.ufg.poo.utilitarias.GerenciadorDeObras;
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
                case 2 -> visualizarArtistas();
                case 3 -> visualizarObras();
                case 4 -> salvarDados();
                case 5 -> continuar = false;
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

        try {
            GerenciadorDeObras.adicionarObra(obra);
            obra.exibirInformacoes();
        } catch (ObraJaExisteException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
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
                if (!linha.trim().isEmpty()) {
                    try {
                        Artista artista = Artista.fromArquivo(linha);
                        GerenciadorDeArtistas.adicionarArtista(artista);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Erro ao carregar artista: " + e.getMessage());
                    }
                }
            }

            while ((linha = readerObras.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    try {
                        ObraDeArte obra = ObraDeArte.fromArquivo(linha);
                        GerenciadorDeObras.adicionarObra(obra);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Erro ao carregar obra: " + e.getMessage());
                    } catch (ObraJaExisteException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }
    }
}
