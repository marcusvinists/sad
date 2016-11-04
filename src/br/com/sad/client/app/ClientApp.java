/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.client.app;

import br.com.sad.client.util.RequestManager;
import br.com.sad.client.util.ResponseManager;
import br.com.sad.controller_client_slave.Response;
import java.util.Scanner;

/**
 *
 * @author Fernando
 */
public class ClientApp {

    public static long USER_ID = -1;

    public static void main(String args[]) {
        try {
            String userIdTxt = null;
            ResponseManager rm = new ResponseManager();
            RequestManager reqM = new RequestManager();
            Scanner scan = new Scanner(System.in);

            System.out.println("******************************************************");
            System.out.println("  Bem vindo ao SAD - Sistema de Arquivos Distrbuidos  ");
            System.out.println("******************************************************");

            while (userIdTxt == null) {
                if (userIdTxt == null) {
                    System.out.println("Favor informar o ID de seu usuário");
                    userIdTxt = scan.nextLine();

                    try {
                        USER_ID = Long.parseLong(userIdTxt);
                    } catch (NumberFormatException ex) {
                        System.out.println("ID inválido.");
                        userIdTxt = null;
                    }
                }
            }

            while (true) {
                Response MakeRequest = reqM.MakeRequest(USER_ID);
                if (MakeRequest != null) {
                    rm.checkResponse(MakeRequest);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
