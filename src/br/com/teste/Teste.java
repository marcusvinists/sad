/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.teste;

import br.com.sad.controlador.LoadBalancer;
import br.com.sad.controlador.ServerSlaveInfo;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author vini
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LoadBalancer balance = new LoadBalancer();

        List<String> listaDeArquivos = new LinkedList<>();
        listaDeArquivos.add("asdasd");

        for (int i = 1; i < 4; i++) {
            ServerSlaveInfo info = new ServerSlaveInfo(i, listaDeArquivos, "/opt/exemplo", 3000 + i);
            balance.adicionarServidor(info);
        }

        listaDeArquivos = new LinkedList<>();
        ServerSlaveInfo infoSemArq = new ServerSlaveInfo(4, listaDeArquivos, "/opt/exemplo", 3004);
        balance.adicionarServidor(infoSemArq);

//        for(ServerSlaveInfo info: balance.getListaSlaveServers()){
//            System.out.println(info.getIdServidor());
//        }
        List<ServerSlaveInfo> mu = balance.retornarServidoresMenosArquivos();

        mu.stream().forEach((ServerSlaveInfo info) -> {
            System.out.println(info.getIdServidor());
        });

    }

}
