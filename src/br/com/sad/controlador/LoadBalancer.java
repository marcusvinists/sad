/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.controlador;

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
    
    
    
    public List<ServerSlaveInfo> retornarServidoresMenosUsados(){
        int[] ids = new int[3];
        List<ServerSlaveInfo> mu = new LinkedList<>();
        
        
        for (ServerSlaveInfo info : listaSlaveServers) {
            if(mu.isEmpty() || mu.size() < 3){
                mu.add(info);
            }else{
                for(ServerSlaveInfo infoMu : mu){
                    if(info.getListaDeArquivos().size() < infoMu.getListaDeArquivos().size()){
                        infoMu = info;
                    }
                }
            }
        }
        
        return mu;
    }
    
    public boolean adicionarServidor(ServerSlaveInfo s){
        boolean adiciona = true;
        for (ServerSlaveInfo info : listaSlaveServers) {
            if(s.getIdServidor() == info.getIdServidor()){
                adiciona = false;
            }
        }
        if(adiciona){
            this.listaSlaveServers.add(s);
        }
        return adiciona;
    }

    public List<ServerSlaveInfo> getListaSlaveServers() {
        return listaSlaveServers;
    }
    
    
}
