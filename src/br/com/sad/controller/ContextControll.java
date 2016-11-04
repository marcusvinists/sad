/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.controller;

import br.com.sad.controller_client_slave.Request;
import br.com.sad.controller_slave.ServerSlaveInfo;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author vini
 */
public class ContextControll {

    private HashMap<Long, String> mapClients;
    private HashMap<Integer, ServerSlaveInfo> mapSlaves;
    private List<Integer> slavesOrder;

    public ContextControll() {
        this.mapClients = new HashMap<>();
        this.mapSlaves = new HashMap<>();
        this.slavesOrder = new LinkedList<Integer>();
    }

    public boolean addFileServer(ServerSlaveInfo serverSlaveInfo) {
        if (mapSlaves.containsKey(serverSlaveInfo.getIdServidor())) {
            return false;
        } else {
            mapSlaves.put(serverSlaveInfo.getIdServidor(), serverSlaveInfo);
            slavesOrder.add(serverSlaveInfo.getIdServidor());
            return true;
        }
    }

    public boolean rmvFileServer(ServerSlaveInfo serverSlaveInfo) {
        ServerSlaveInfo remove = mapSlaves.remove(serverSlaveInfo.getIdServidor());
        slavesOrder.remove(serverSlaveInfo.getIdServidor());
        return remove != null;
    }


    /**
     * check if the file is locked for other user.
     * @param request
     * @return true for block and false to no block.
     */
    public boolean checkBlock(Request request) {
        return mapClients.entrySet().stream().anyMatch((entry) -> 
                (entry.getValue().equals(request.getFileName()) 
                        && entry.getKey() != request.getUserId()));
    }

    public boolean addClientExclusiveBlock(Request request) {
      if(checkBlock(request)){
          return false;
      }else{
          mapClients.put(request.getUserId(), request.getFileName());
          return true;
      }
    }
    
    public boolean removeClientExclusiveBlock(Request request){
        String remove = mapClients.remove(request.getUserId());
        return remove != null;
    }

    public List<ServerSlaveInfo> getSlaveServersLeastFiles() {
        List<ServerSlaveInfo> mu = getSlaveServersList();
        Collections.sort(mu);
        return mu.subList(0, 3);
    }

    public List<ServerSlaveInfo> getSlaveServersList() {
        List<ServerSlaveInfo> list = new LinkedList<>();
        mapSlaves.entrySet().forEach((Entry<Integer, ServerSlaveInfo> entry) -> {
            //    System.out.println(entry.getKey() + "/" + entry.getValue());
            list.add(entry.getValue());
        });
        return list;
    }
    
    public ServerSlaveInfo getSlaveServerInfo(int id){
       return mapSlaves.get(id);
    }

    public List<Integer> getSlavesOrder() {
        return slavesOrder;
    }
    
    

}
