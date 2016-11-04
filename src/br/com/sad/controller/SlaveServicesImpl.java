/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.controller;

import br.com.sad.controller_slave.ServerSlaveInfo;
import br.com.sad.controller_slave.SlaveServices;
import br.com.sad.controller.app.ControllerApp;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author vini
 */
public class SlaveServicesImpl extends UnicastRemoteObject implements SlaveServices{
    
    public SlaveServicesImpl() throws RemoteException {
		super();
	}

    @Override
    public void registrarNovoServidor(ServerSlaveInfo info) throws RemoteException {
        if(ControllerApp.ctControll.addFileServer(info)){
            System.out.println("Servidor adicionado, id:" + info.getIdServidor() );
        }else{
            System.err.println("Erro, servidor não adicionado! verificar id do servidor");
        }
    }
    
}
