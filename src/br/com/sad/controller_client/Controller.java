/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.controller_client;

import br.com.sad.controller_client_slave.Request;
import br.com.sad.controller_client_slave.Response;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author vini
 */
public interface Controller extends Remote{
    public Response makeRequest(Request request) throws RemoteException;
}
