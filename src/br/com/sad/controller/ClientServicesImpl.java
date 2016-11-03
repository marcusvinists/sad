/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.controller;

import br.com.sad.controller_slave.ServerSlaveInfo;
import br.com.sad.controller.app.ControllerApp;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;
import br.com.sad.controller_slave.Operations;
import br.com.sad.controller_client_slave.Request;
import br.com.sad.controller_client_slave.Response;
import br.com.sad.controller_client_slave.ResponseEnum;

/**
 *
 * @author vini
 */
public class ClientServicesImpl extends UnicastRemoteObject implements Operations {

    private Operations op;
    private Response resp;

    public ClientServicesImpl() throws RemoteException {
        super();
        resp = new Response();
    }

    @Override
    public Response listFiles() throws RemoteException {
         
        List<String> lista = new LinkedList<>();
        //tratamento e chamar metodos slave
        for (ServerSlaveInfo info : ControllerApp.balance.getSlaveServersList()) {
            try {
                op = (Operations) Naming.lookup("rmi://localhost:" + info.getPorta() + "/ope/" + info.getIdServidor());
            } catch (NotBoundException ex) {
                ex.printStackTrace();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            Response respSlave = op.listFiles();
            List listarArquivos = respSlave.getListeResponse();
            for (Object str : listarArquivos) {
                boolean insere = true;
                if (!lista.isEmpty()) {
                    for (String str2 : lista) {
                        if (str2.equalsIgnoreCase(str.toString())) {
                            insere = false;
                        }
                    }
                }
                if (insere) {
                    lista.add(str.toString());
                }
            }
        }
        resp.setStatus(ResponseEnum.success);
        resp.setListeResponse(lista);
        return resp;
    }

    @Override
    public Response removeFiles(Request request) throws RemoteException {
        for (ServerSlaveInfo info : ControllerApp.balance.getSlaveServersList()) {
            try {
                op = (Operations) Naming.lookup("rmi://localhost:" + info.getPorta() + "/ope/" + info.getIdServidor());
            } catch (NotBoundException ex) {
                ex.printStackTrace();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            resp = op.removeFiles(request);
            for (int i = 0; i < info.getListaDeArquivos().size(); i++) {
                if (info.getListaDeArquivos().get(i).equals(request.getFileName())) {
                    info.getListaDeArquivos().remove(i);
                }
            }
        }
        resp.setStatus(ResponseEnum.success);
        return resp;
    }

    @Override
    public Response readFiles(Request request) throws RemoteException {
        for (ServerSlaveInfo info : ControllerApp.balance.getSlaveServersList()) {
            try {
                op = (Operations) Naming.lookup("rmi://localhost:" + info.getPorta() + "/ope/" + info.getIdServidor());
            } catch (NotBoundException ex) {
                ex.printStackTrace();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            return op.readFiles(request);
        }
        resp.setStatus(ResponseEnum.error);
        return resp;
    }

    @Override
    public Response createFiles(Request request) throws RemoteException {
        List<ServerSlaveInfo> mu = ControllerApp.balance.getSlaveServersLeastFiles();
        for (ServerSlaveInfo info : mu) {
            try {
                op = (Operations) Naming.lookup("rmi://localhost:" + info.getPorta() + "/ope/" + info.getIdServidor());
            } catch (NotBoundException ex) {
                ex.printStackTrace();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            resp = op.createFiles(request);
            info.getListaDeArquivos().add(request.getFileName());
        }
        resp.setStatus(ResponseEnum.success);
        return resp;
    }
    
    public boolean haveThreeSlaves(){
        return ControllerApp.balance.getSlaveServersList().size() >= 3;
    }

}
