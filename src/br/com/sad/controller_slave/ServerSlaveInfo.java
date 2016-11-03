/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.controller_slave;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author vini
 */
public class ServerSlaveInfo implements Serializable, Comparable<ServerSlaveInfo>{
    
    private int idServidor;
    private List<String> listaDeArquivos;
    private String pathArquivos;
    private int porta;

    public ServerSlaveInfo(int idServidor, List<String> listaDeArquivos, String pathArquivos, int porta) {
        this.idServidor = idServidor;
        this.listaDeArquivos = listaDeArquivos;
        this.pathArquivos = pathArquivos;
        this.porta = porta;
    }

    public int getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(int idServidor) {
        this.idServidor = idServidor;
    }

    public List<String> getListaDeArquivos() {
        return listaDeArquivos;
    }

    public void setListaDeArquivos(List<String> listaDeArquivos) {
        this.listaDeArquivos = listaDeArquivos;
    }

    public String getPathArquivos() {
        return pathArquivos;
    }

    public void setPathArquivos(String pathArquivos) {
        this.pathArquivos = pathArquivos;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }
    /**
     * Compara qual servidor com menor número de arquivos
     * @param t
     * @return 
     */
    @Override
    public int compareTo(ServerSlaveInfo t) {
        int compareQuantity = t.getListaDeArquivos().size();
        //ascending order
        return this.getListaDeArquivos().size() - compareQuantity;
    }
}
