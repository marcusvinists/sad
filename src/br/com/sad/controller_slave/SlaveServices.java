/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.controller_slave;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author vini
 */
public interface SlaveServices extends Remote{
    
    public void registrarNovoServidor(ServerSlaveInfo info)throws RemoteException;
    
}
