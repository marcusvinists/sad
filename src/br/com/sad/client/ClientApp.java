/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.client;

import br.com.sad.util.ResponseManager;
import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;
import br.com.sad.util.Operations;
import br.com.sad.util.OperationsEnum;
import br.com.sad.util.Request;
import br.com.sad.util.RequestManager;
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
            Request req;
            Response res = new Response();
            ResponseManager rm = new ResponseManager();
            RequestManager reqM = new RequestManager();
            
            System.out.println("Bem vindo ao SAD - Sistema de Arquivos Distrbuidos");
            Scanner scan = new Scanner(System.in);
            int portClient = 2011;
            Operations cs = (Operations) Naming.lookup("rmi://localhost:" + portClient + "/cs");

            while (true) {
                System.out.println("********************************");
                System.out.println("Selecione a operaçao desejada:");
                System.out.println("(1)deletar");
                System.out.println("(2)listar todos arquivos");
                System.out.println("(3)criar arquivo");
                System.out.println("(4)ler arquivo");
                System.out.println("(5)sair");
                System.out.println("********************************");
                System.out.println(" ");
                int servico = scan.nextInt();
                req = reqM.setRequestInfo(servico);
                switch (servico) {
                    case 1:
                         res = cs.removeFiles(req);
                        break;
                    case 2:
                         res = cs.listFiles();
                        break;
                    case 3:
                         res = cs.createFiles(req);
                        break;
                    case 4:
                         res = cs.readFiles(req);
                         break;
                    case 5:
                        System.exit(0);
                        break;
                }
                rm.checkResponse(res, req, servico);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

}
