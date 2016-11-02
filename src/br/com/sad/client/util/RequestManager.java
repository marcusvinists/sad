/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.client.util;

import br.com.sad.util.Operations;
import br.com.sad.util.OperationsEnum;
import br.com.sad.util.Request;
import br.com.sad.util.Response;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author vini
 */
public class RequestManager {

    public Response MakeRequest() throws NotBoundException, MalformedURLException, RemoteException {
        Response res = null;
        Scanner scan = new Scanner(System.in);
        int portClient = 2011;
        Operations cs = (Operations) Naming.lookup("rmi://localhost:" + portClient + "/cs");

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

            Request req = new Request();

            if (servico == 1 || servico == 4 || servico == 3) {
                System.out.println("Digite o nome do arquivo");
                String fileName = scan.nextLine();
                req.setFileName(fileName);

            }
            if (servico == 3) {
                System.out.println("Digite o conteúdo do arquivo");
                req.setFileTxt(scan.nextLine());
            }

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
                default:
                    System.exit(0);
                    break;
            }
            if (res != null) {
                res.setOperation(OperationsEnum.retornarEnum(servico));
            }

        } catch (NumberFormatException e) {
            System.out.println("Valor passado inválido, favor digitar apenas "
                    + "valores numéricos solicitados");
        }

        return res;
    }

}
