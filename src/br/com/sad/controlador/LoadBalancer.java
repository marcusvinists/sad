/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.controlador;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author vini
 */
public class LoadBalancer {

    private List<ServerSlaveInfo> listaSlaveServers;

    public LoadBalancer() {
        listaSlaveServers = new LinkedList<>();
    }

    public List<ServerSlaveInfo> retornarServidoresMenosArquivos() {
        List<ServerSlaveInfo> mu = new LinkedList<>();
        Collections.sort(listaSlaveServers);
        for (int i = 0; i < 3; i++) {
            mu.add(listaSlaveServers.get(i));
        }
        return mu;
    }

    public boolean adicionarServidor(ServerSlaveInfo s) {
        boolean adiciona = true;
        for (ServerSlaveInfo info : listaSlaveServers) {
            if (s.getIdServidor() == info.getIdServidor()) {
                adiciona = false;
            }
        }
        if (adiciona) {
            this.listaSlaveServers.add(s);
        }
        return adiciona;
    }

    public List<ServerSlaveInfo> getListaSlaveServers() {
        return listaSlaveServers;
    }

}
