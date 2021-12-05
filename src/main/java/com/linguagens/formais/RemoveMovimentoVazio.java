/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linguagens.formais;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class RemoveMovimentoVazio {
    
    public void run(ArrayList<String[]> AFNDV) {

        ArrayList<String[]> AFND = new ArrayList<>();
        
        for (String[] strings : AFNDV) {
            String[] novaLinha = new String[4];
            novaLinha[0] = strings[0];
            novaLinha[1] = strings[1];
            AFND.add(novaLinha);
        }
        
        for (int i = AFNDV.size()-1; i >= 0; i--) {
            String[] string = AFNDV.get(i);
            if(!string[4].isBlank()){
                String[] split = string[4].split(",");
                AFND.set(i, removeVazio(split, AFNDV, AFND, i));
            } else {
                AFND.set(i, setaPadrao(AFNDV, AFND, i));
            }
            
        }
        
        for (String[] strings : AFND) {
            for (int i = 0; i < strings.length; i++) {
                System.out.print(strings[i] + " ");
            }
            System.out.println("");
        }
        
    }
    
    private String[] setaPadrao(ArrayList<String[]> AFNDV, ArrayList<String[]> AFND, int posicao) {
        String[] linhaAFNDV = AFNDV.get(posicao);
        String[] linha = AFND.get(posicao);
        
        linha[2] = linhaAFNDV[2];
        linha[3] = linhaAFNDV[3];
        
        return linha;
    }

    private String[] removeVazio(String[] split, ArrayList<String[]> AFNDV, ArrayList<String[]> AFND, Integer posicao) {
        
        String[] linhaAFNDV = AFNDV.get(posicao);
        String[] linha = AFND.get(posicao);
        
        String flagEstado = linha[0];
        String estadoZero = linhaAFNDV[2];
        String estadoUm = linhaAFNDV[3];
        
        for (String[] strings : AFNDV) {
            
            for (int i = 0; i < split.length; i++) {
                if(split[i].equals(strings[1])){
                    if(estadoZero.isBlank()){
                        estadoZero = estadoZero.concat(strings[2]);
                        if(!strings[3].isBlank()){
                            estadoZero = estadoZero.concat("," + strings[3]);
                        }
                    } else {
                        if(!strings[2].isBlank()){
                            estadoZero = estadoZero.concat("," + strings[2]);
                        }
                        if(!strings[3].isBlank()){
                            estadoZero = estadoZero.concat("," + strings[3]);
                        }
                    }
                    
                    if(estadoUm.isBlank()){
                        estadoUm = estadoUm.concat(strings[3]);
                        if(!strings[2].isBlank()){
                            estadoUm = estadoUm.concat("," + strings[2]);
                        }        
                    } else {
                        if(!strings[3].isBlank()){
                            estadoUm = estadoUm.concat("," + strings[3]);
                        }
                        if(!strings[2].isBlank()){
                            estadoUm = estadoUm.concat("," + strings[2]);
                        }  
                    }
                    if("*".equals(strings[0])){
                        flagEstado = flagEstado.concat(",*");
                    }
                }
            }

        }
        
         
        String[] novaLinha = new String[4];
        if("*".equals(linha[0])){
            novaLinha[0] = linha[0];
        } else {
            novaLinha[0] = flagEstado;
        }
        novaLinha[1] = linha[1];
        novaLinha[2] = estadoZero;
        novaLinha[3] = estadoUm;
        return novaLinha;
    }
 
}
