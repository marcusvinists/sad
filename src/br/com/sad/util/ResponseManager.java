/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.util;

import br.com.sad.util.Request;
import br.com.sad.util.Response;
import br.com.sad.util.ResponseEnum;

/**
 *
 * @author vini
 */
public class ResponseManager {

    /**
     * Método checa se mensagem respondeu erro ou se respondeu ok. imprime
     * resposta ou mensagem de erro;
     *
     * @param response
     */
    public void checkResponse(Response response, Request req, int servico) {
        System.out.println(" ");
        System.out.println("********************************");
        if (response.getStatus() == ResponseEnum.error) {
            System.out.println("A operação não foi realizada.");
            System.out.println("Motivo: ");
            System.out.println(response.getMessage());
        } else {
            switch (servico) {
                case 1:
                    System.out.println("Arquivo removido com sucesso");
                    break;
                case 2:
                    if (response.getListeResponse().size() > 0) {
                        System.out.println("Lista de arquivos: ");
                    } else {
                        System.out.println("Nenhum arquivo a ser exibido!");
                    }
                    int i = 1;
                    for (Object str : response.getListeResponse()) {
                        System.out.println(i + "-" + str.toString());
                        i += 1;
                    }
                    break;
                case 3:
                    System.out.println("Arquivo " + req.getFileName() + " criado.");
                    break;
                case 4:

                    if (response.getTxtResponse() != null) {
                        System.out.println("Conteúdo do arquivo: ");
                        System.out.println(response.getTxtResponse());
                    } else {
                        System.out.println("Arquivo não encontrado");
                    }
                    break;
                case 5:
                    System.out.println("Saindo.....");
                    System.out.println("Obrigado por usar o incrível SAD!");
                    break;
                default:
                    System.out.println("Comando não reconhecido.");
                    System.out.println("Favor digitar comando entre 1-5.");
                    break;
            }

        }
        System.out.println("********************************");
        System.out.println("");
    }

}
