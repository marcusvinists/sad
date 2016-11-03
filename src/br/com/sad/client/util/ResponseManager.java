/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.client.util;

import static br.com.sad.controller_client_slave.OperationsEnum.create;
import static br.com.sad.controller_client_slave.OperationsEnum.list;
import static br.com.sad.controller_client_slave.OperationsEnum.read;
import static br.com.sad.controller_client_slave.OperationsEnum.remove;
import br.com.sad.controller_client_slave.Response;
import br.com.sad.controller_client_slave.ResponseEnum;






/**
 *
 * @author vini
 */
public class ResponseManager {

    /**
     * Método checa se mensagem respondeu erro ou se respondeu ok. imprime
     * resposta ou mensagem de erro;
     *
     * @param response Objeto preenchido pelo requestManager
     */
    public void checkResponse(Response response) {
        System.out.println(" ");
        System.out.println("********************************");
        if (response.getStatus() == ResponseEnum.error) {
            System.out.println("A operação não foi realizada.");
            System.out.println("Motivo: ");
            System.out.println(response.getMessage());
        } else {

            switch (response.getOperation()) {
                case remove:
                    System.out.println("Arquivo removido com sucesso");
                    break;
                case list:
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
                case create:
                    System.out.println("Arquivo criado!");
                    break;
                case read:

                    if (response.getTxtResponse() != null) {
                        System.out.println("Conteúdo do arquivo: ");
                        System.out.println(response.getTxtResponse());
                    } else {
                        System.out.println("Arquivo não encontrado");
                    }
                    break;
                case exit:
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
