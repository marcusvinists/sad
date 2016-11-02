/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.client.app;

import br.com.sad.client.util.RequestManager;
import br.com.sad.client.util.ResponseManager;
import br.com.sad.util.Response;

/**
 *
 * @author Fernando
 */
public class ClientApp {

    private static String nomeDoArquivo;
    private static String txt;

    public static void main(String args[]) {
        try {
            ResponseManager rm = new ResponseManager();
            RequestManager reqM = new RequestManager();

            System.out.println("Bem vindo ao SAD - Sistema de Arquivos Distrbuidos");
            while (true) {
                Response MakeRequest = reqM.MakeRequest();
                if (MakeRequest != null) {
                    rm.checkResponse(MakeRequest);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
