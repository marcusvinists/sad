/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.util;

import java.util.Scanner;

/**
 *
 * @author vini
 */
public class RequestManager {
    public Request setRequestInfo(int servico){
        Request req = new Request();
         Scanner scan = new Scanner(System.in);
        if (servico == 1 || servico == 4 || servico == 3) {
            System.out.println("Digite o nome do arquivo");
            req.setFileName(scan.nextLine());
         
        }
        if (servico == 3) {
            System.out.println("Digite o conteúdo do arquivo");
            req.setFileTxt(scan.nextLine());
        }
        return req;
    }
    
 
}
