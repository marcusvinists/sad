/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.controlador;

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
        if(Controlador.balance.adicionarServidor(info)){
            System.out.println("Servidor adicionado, id:" + info.getIdServidor() );
        }else{
            System.err.println("Erro, servidor não adicionado! verificar id do servidor");
        }
    }
    
}
