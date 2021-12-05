/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linguagens.formais;

import com.linguagens.formais.model.Tabela;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Usuario
 */
public class DeterminizaAFND {

    int countLinha = 0;

    public void run(ArrayList<String[]> AFND) {

        ArrayList<String[]> AFD = new ArrayList<>();
        int countWhile = 1;

        String[] linha01 = AFND.get(0);

        String[] novaLinha = new String[4];
        novaLinha[0] = linha01[0];
        novaLinha[1] = linha01[1];
        novaLinha[2] = splitAndMerge(linha01[2]);
        novaLinha[3] = splitAndMerge(linha01[3]);

        AFD.add(novaLinha);
        countLinha++;

        String[] novaLinha01 = new String[4];
        novaLinha01[1] = novaLinha[2];
        AFD.add(novaLinha01);
        countLinha++;

        String[] novaLinha02 = new String[4];
        novaLinha02[1] = novaLinha[3];
        AFD.add(novaLinha02);
        countLinha++;

        do {
            String[] linha = AFD.get(countWhile);

            if (linhaDiferenteParaPopular(linha[1], AFD)) {

                AFD.set(countWhile, processaComplexo(linha[1], AFND, AFD, linha));
            }
            countWhile++;

        } while (countWhile != countLinha);

        verificaEstadoFinal(AFND, AFD);

        for (String[] strings : AFD) {
            for (int i = 0; i < strings.length; i++) {
                System.out.print(strings[i] + " ");
            }
            System.out.println("");
        }

    }

    private void verificaEstadoFinal(ArrayList<String[]> AFND, ArrayList<String[]> AFD) {

        for (String[] linhaAFND : AFND) {
            for (String[] linhaAFD : AFD) {
      
                if ("*".equals(linhaAFND[0]) && linhaAFD[1].contains(linhaAFND[1])) {
                    linhaAFD[0] = "*";
                } 
            }
        }
    }

    private boolean linhaDiferente(String string, ArrayList<String[]> AFD) {
        for (String[] strings : AFD) {

            if (string.equals(strings[1])) {
                return false;
            }
        }
        return true;
    }

    private boolean linhaDiferenteParaPopular(String string, ArrayList<String[]> AFD) {

        for (String[] strings : AFD) {

            if (string.equals(strings[1]) && strings[0] == null) {
                return true;
            }
        }
        return false;
    }

    private String splitAndMerge(String string) {
        return merge(string.split(","));
    }

    private String merge(String[] string) {
        String teste = "";

        for (int i = 0; i < string.length; i++) {
            teste = teste.concat(string[i]);
        }

        return teste;
    }

    private String[] processaComplexo(String novoEstado, ArrayList<String[]> AFND, ArrayList<String[]> AFD, String[] linha) {

        String flagEstado = "";
        String estadoZero = "";
        String estadoUm = "";

        for (String[] strings : AFND) {

            if (novoEstado.contains(strings[1])) {
                if (!estadoZero.contains(splitAndMerge(strings[2]))) {
                    estadoZero = estadoZero.concat(splitAndMerge(strings[2]));
                }
                if (!estadoUm.contains(splitAndMerge(strings[3]))) {
                    estadoUm = estadoUm.concat(splitAndMerge(strings[3]));
                }

            }

        }

        String[] novaLinha = new String[4];
        novaLinha[0] = flagEstado;
        novaLinha[1] = novoEstado;
        novaLinha[2] = estadoZero;
        novaLinha[3] = estadoUm;

        if (linhaDiferente(estadoZero, AFD)) {
            String[] novaLinhaZero = new String[4];
            novaLinhaZero[1] = estadoZero;
            AFD.add(novaLinhaZero);
            countLinha++;
        }

        if (linhaDiferente(estadoUm, AFD)) {
            String[] novaLinhaUm = new String[4];
            novaLinhaUm[1] = estadoUm;
            AFD.add(novaLinhaUm);
            countLinha++;
        }

        return novaLinha;
    }

}
