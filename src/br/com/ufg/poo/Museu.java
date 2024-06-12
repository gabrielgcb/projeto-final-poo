package br.com.ufg.poo;

import br.com.ufg.poo.modelos.base.ObraDeArte;
import br.com.ufg.poo.modelos.impl.Artista;
import br.com.ufg.poo.modelos.impl.Escultura;
import br.com.ufg.poo.utilitarias.InputUtil;

import java.time.LocalDate;

public class Museu {
    public static void main(String[] args) {

        String nomeArtista = InputUtil.lerString("Digite o nome do artista: ");
        LocalDate dataNascimentoArtista = InputUtil.lerData("Digite a data de nascimento do artista (dd/MM/yyyy): ");
        LocalDate dataFalecimentoArtista = InputUtil.lerData("Digite a data de falecimento do artista (dd/MM/yyyy), ou deixe em branco se ainda vivo:");
        String nacionalidadeArtista = InputUtil.lerString("Digite a nacionalidade do artista: ");
        String biografiaArtista = InputUtil.lerString("Digite a biografia do artista: ");

        Artista artista;
        if(dataFalecimentoArtista != null) {
            artista = new Artista(nomeArtista, dataNascimentoArtista, dataFalecimentoArtista, nacionalidadeArtista, biografiaArtista);
        } else {
            artista = new Artista(nomeArtista, dataNascimentoArtista, nacionalidadeArtista, biografiaArtista);
        }

        String tituloObra = InputUtil.lerString("Digite o título da obra: ");
        int anoObra = InputUtil.lerInt("Digite o ano da obra: ");
        String descricaoObra = InputUtil.lerString("Digite a descrição da obra: ");
        String materialObra = InputUtil.lerString("Digite o material da obra: ");
        double alturaObra = InputUtil.lerDouble("Digite a altura da obra: ");

        ObraDeArte obra = new Escultura(tituloObra, anoObra, descricaoObra, materialObra, alturaObra);

//        Artista artista = new Artista(
//                "Michelangelo",
//                LocalDate.of(1800, 05, 10),
//                LocalDate.of(1900, 05, 10),
//                "Italiano",
//                "Um grande artista"
//        );
//
//        Escultura obra = new Escultura(
//                "David",
//                artista,
//                1504,
//                "Obra inspirada num anjo",
//                "Mármore",
//                5.04
//        );
//
//        Escultura obra2 = new Escultura(
//                "David2",
//                artista,
//                1504,
//                "Obra inspirada num anjo",
//                "Mármore",
//                5.04
//        );

        artista.exibirInformacoes();

//
//        ObraDeArte pieta = new Escultura(
//                "Pieta",
//                michelangelo,
//                1499,
//                "Pieta é uma famosa escultura de Michelangelo, localizada na Basílica de São Pedro.",
//                "Mármore",
//                1.74
//        );

//        List<ObraDeArte> obras = new ArrayList<>();
//
//        obras.add(anjo);
//        obras.add(david);
//        obras.add(pieta);
//
//        michelangelo.setObras(obras);


    }
}