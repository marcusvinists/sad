/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.controller;

import br.com.sad.controller.app.ControllerApp;
import br.com.sad.controller_client.Controller;
import br.com.sad.controller_client_slave.Request;
import br.com.sad.controller_client_slave.Response;
import br.com.sad.controller_client_slave.ResponseEnum;
import br.com.sad.controller_slave.Operations;
import br.com.sad.controller_slave.ServerSlaveInfo;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author vini
 */
public class ControllerImpl extends UnicastRemoteObject implements Controller, Operations {

    private Operations op;
    private Response resp;

    public ControllerImpl() throws RemoteException {
        super();
        resp = new Response();
    }

    @Override
    public Response makeRequest(Request request) throws RemoteException {
        Response res = new Response();

        if (haveThreeSlaves()) {
            if (!ControllerApp.ctControll.checkBlock(request)) {
                switch (request.getOperation()) {
                    case remove:
                        res = removeFiles(request);
                        break;
                    case list:
                        res = listFiles();
                        break;
                    case create:
                        res = createFiles(request);
                        break;
                    case read:
                        res = readFiles(request);
                        break;
                    case exit:
                        ControllerApp.ctControll.removeClientExclusiveBlock(request);
                    default:
                        res.setStatus(ResponseEnum.error);
                        res.setMessage("Erro, tipo de operação não reconhecida pelo Controlador");
                        break;
                }
            } else {
                res.setStatus(ResponseEnum.error);
                res.setMessage("O arquivo requisitado se encontra em uso por "
                        + "outro usuário favor aguardar o término do uso da "
                        + "edição");
            }
        } else {
            res.setStatus(ResponseEnum.error);
            res.setMessage("Erro, Número de servidores de arquivos insuficiente,"
                    + " o que pode causar inconsisência ao salvar seus dados. "
                    + "Favor entrar em contato com o administrador do serviço "
                    + "SAD.");
        }

        res.setOperation(request.getOperation());
        return res;
    }

    @Override
    public Response listFiles() throws RemoteException {
        List<String> lista = new LinkedList<>();
        //tratamento e chamar metodos slave
        for (ServerSlaveInfo info : ControllerApp.ctControll.getSlaveServersList()) {
            try {
                op = (Operations) Naming.lookup("rmi://localhost:" + info.getPorta() + "/ope/" + info.getIdServidor());
            } catch (NotBoundException ex) {
                ex.printStackTrace();
                ControllerApp.ctControll.rmvFileServer(info);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            Response respSlave = op.listFiles();
            List listarArquivos = respSlave.getListeResponse();
            listarArquivos.forEach((Object str) -> {
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
            });
        }
        resp.setStatus(ResponseEnum.success);
        resp.setListeResponse(lista);
        return resp;
    }

    @Override
    public Response removeFiles(Request request) throws RemoteException {
        for (ServerSlaveInfo info : ControllerApp.ctControll.getSlaveServersList()) {
            try {
                op = (Operations) Naming.lookup("rmi://localhost:" + info.getPorta() + "/ope/" + info.getIdServidor());
            } catch (NotBoundException ex) {
                ex.printStackTrace();
                ControllerApp.ctControll.rmvFileServer(info);
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
        int idServer = -1;
        ServerSlaveInfo slaveServerInfo = null;
        for (int i =0; i<ControllerApp.ctControll.getSlavesOrder().size();i++) {
            slaveServerInfo = ControllerApp.ctControll.getSlaveServerInfo(
                    ControllerApp.ctControll.getSlavesOrder().get(i));

            for (String str : slaveServerInfo.getListaDeArquivos()) {
                if (str.equals(request.getFileName())) {
                    idServer = ControllerApp.ctControll.getSlavesOrder().get(i);
                    ControllerApp.ctControll.getSlavesOrder().remove(i);
                    break;
                }
            }
            if(idServer>-1){
                break;
            }
        }
        if (idServer > -1 && slaveServerInfo != null) {
            try {
                op = (Operations) Naming.lookup("rmi://localhost:" + slaveServerInfo.getPorta() + "/ope/" + slaveServerInfo.getIdServidor());
            } catch (NotBoundException ex) {
                ex.printStackTrace();
                ControllerApp.ctControll.rmvFileServer(slaveServerInfo);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            
            ControllerApp.ctControll.getSlavesOrder().add(idServer);
            ControllerApp.ctControll.addClientExclusiveBlock(request);
            System.out.println("Lendo do servidor: "  + idServer);
            return op.readFiles(request);
        }
        resp.setStatus(ResponseEnum.error);
        return resp;
    }

    @Override
    public Response createFiles(Request request) throws RemoteException {
        removeFiles(request);
        List<ServerSlaveInfo> mu = ControllerApp.ctControll.getSlaveServersLeastFiles();
        for (ServerSlaveInfo info : mu) {
            try {
                op = (Operations) Naming.lookup("rmi://localhost:" + info.getPorta() + "/ope/" + info.getIdServidor());
            } catch (NotBoundException ex) {
                ex.printStackTrace();
                ControllerApp.ctControll.rmvFileServer(info);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            resp = op.createFiles(request);
            info.getListaDeArquivos().add(request.getFileName());
        }
        resp.setStatus(ResponseEnum.success);
        return resp;
    }

    public boolean haveThreeSlaves() {
        return ControllerApp.ctControll.getSlaveServersList().size() >= 3;
    }
}
