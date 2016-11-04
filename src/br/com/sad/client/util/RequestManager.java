/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.client.util;

import br.com.sad.controller_client.Controller;
import br.com.sad.controller_client_slave.OperationsEnum;
import br.com.sad.controller_client_slave.Request;
import br.com.sad.controller_client_slave.Response;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 *
 * @author vini
 */
public class RequestManager {

    private final int PORT_CLIENT = 2011;

    public Response MakeRequest(long userId) throws NotBoundException, MalformedURLException, RemoteException {
        Response res = null;
        Request req = new Request();
        req.setUserId(userId);
        Scanner scan = new Scanner(System.in);

            Controller ct = (Controller) Naming.lookup("rmi://localhost:" + PORT_CLIENT + "/ct");

            System.out.println("********************************");
            System.out.println("Selecione a operaçao desejada:");
            System.out.println("(1)deletar");
            System.out.println("(2)listar todos arquivos");
            System.out.println("(3)criar arquivo");
            System.out.println("(4)ler arquivo");
            System.out.println("(5)sair");
            System.out.println("********************************");
            System.out.println(" ");
            String servicoStr = scan.nextLine();
            try {
                int servico = Integer.parseInt(servicoStr);

                OperationsEnum operationUnum = OperationsEnum.retornarEnum(servico);
                req.setOperation(operationUnum);

                if (operationUnum != OperationsEnum.list && operationUnum != OperationsEnum.exit) {
                    System.out.println("Digite o nome do arquivo");
                    String fileName = scan.nextLine();
                    req.setFileName(fileName);
                }
                if (operationUnum == OperationsEnum.create) {
                    System.out.println("Digite o conteúdo do arquivo");
                    req.setFileTxt(scan.nextLine());
                }

                if (operationUnum != OperationsEnum.not_impl) {
                    res = ct.makeRequest(req);
                    if(operationUnum == OperationsEnum.exit){
                        System.out.println("Bye O/");
                        System.exit(0);
                    }
                } else {
                    System.out.println("Valor passado é inválido, favor digitar apenas "
                            + "valores numéricos solicitados");
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor passado é inválido, favor digitar apenas "
                        + "valores numéricos solicitados");
            }
        
        return res;
    }

}
