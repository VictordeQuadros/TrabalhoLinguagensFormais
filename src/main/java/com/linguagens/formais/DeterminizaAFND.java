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
    
    public void run(ArrayList<String[]> AFND) {
        
        ArrayList<String[]> AFD = new ArrayList<>();
        
        String[] linha01 = AFND.get(0);
        
        
        String[] novaLinha = new String[4];
        novaLinha[0] = linha01[0];
        novaLinha[1] = linha01[1];
        novaLinha[2] = splitAndMerge(linha01[2]);
        novaLinha[3] = splitAndMerge(linha01[3]);
        
        AFD.add(novaLinha);
        
        String[] novaLinha01 = new String[4];
        novaLinha01[1] = novaLinha[2];
        AFD.add(novaLinha01);
        
        String[] novaLinha02 = new String[4];
        novaLinha02[1] = novaLinha[3];
        AFD.add(novaLinha02);
        
//        processaComplexo(novaLinha[2], AFND, AFD);
//        processaComplexo(novaLinha[3], AFND, AFD);

        for (String[] strings : AFD) {
            for (int i = 0; i < strings.length; i++) {
                System.out.print(strings[i] + " ");
            }
            System.out.println("");
        }
        
        for (String[] strings : AFD) {
            if(linhaDiferenteParaPopular(strings[1], AFD)){
                
            }
        }
        
        if(!novaLinha[2].equals(novaLinha[3])){
            
        }
    }
    
    private boolean linhaDiferente(String string, ArrayList<String[]> AFD) {  
        for (String[] strings : AFD) {
            
            if(string.equals(strings[1])){
                return false;
            }
        }
        return true;
    }
    
    private boolean linhaDiferenteParaPopular(String string, ArrayList<String[]> AFD) {
        
        
        for (String[] strings : AFD) {
            
            if(string.equals(strings[1]) && strings[0] == null){
                return true;
            }
        }
        return false;
    }
    
    private String splitAndMerge(String string){
        return merge(string.split(","));
    }
    
    private String merge(String[] string){
        String teste = "";
        
        for (int i = 0; i < string.length; i++) {
            teste = teste.concat(string[i]);
        }
        
        return teste;
    }
    
    private void processaComplexo(String novoEstado, ArrayList<String[]> AFND, ArrayList<String[]> AFD){
        
        String flagEstado = "";
        String estadoZero = "";
        String estadoUm = "";
        
        for (String[] strings : AFND) {
            
            if(novoEstado.contains(strings[1])){
                if("*".equals(strings[0])){
                    flagEstado = "*";
                } else {
                    flagEstado = " ";
                }
                estadoZero = estadoZero.concat(splitAndMerge(strings[2]));
                estadoUm = estadoUm.concat(splitAndMerge(strings[3]));
            }
            
        }
        
        String[] novaLinha = new String[4];
        novaLinha[0] = flagEstado;
        novaLinha[1] = novoEstado;
        novaLinha[2] = estadoZero;
        novaLinha[3] = estadoUm;
        
        AFD.add(novaLinha);
        
        
    }
                   
}
