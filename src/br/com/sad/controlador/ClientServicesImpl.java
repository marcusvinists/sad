/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.controlador;

import br.com.sad.util.Operacoes;
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
public class ClientServicesImpl extends UnicastRemoteObject implements Operacoes {

    private Operacoes op;

    public ClientServicesImpl() throws RemoteException {
        super();
    }

    @Override
    public List listarArquivos() throws RemoteException {
        List<String> lista = new LinkedList<>();
        //tratamento e chamar metodos slave
        for (ServerSlaveInfo info : Controlador.balance.getListaSlaveServers()) {
            try {
                op = (Operacoes) Naming.lookup("rmi://localhost:" + info.getPorta() + "/ope/" + info.getIdServidor());
            } catch (NotBoundException ex) {
                ex.printStackTrace();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }

            List listarArquivos = op.listarArquivos();
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
        return lista;
    }

    @Override
    public void removerArquivos(String nomeDoArquivo) throws RemoteException {
        for (ServerSlaveInfo info : Controlador.balance.getListaSlaveServers()) {
            try {
                op = (Operacoes) Naming.lookup("rmi://localhost:" + info.getPorta() + "/ope/" + info.getIdServidor());
            } catch (NotBoundException ex) {
                ex.printStackTrace();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            op.removerArquivos(nomeDoArquivo);
            for (int i = 0; i < info.getListaDeArquivos().size(); i++) {
                if (info.getListaDeArquivos().get(i).equals(nomeDoArquivo)) {
                    info.getListaDeArquivos().remove(i);
                }
            }
        }
    }

    @Override
    public String lerArquivo(String nomeDoArquivo) throws RemoteException {
        for (ServerSlaveInfo info : Controlador.balance.getListaSlaveServers()) {
            try {
                op = (Operacoes) Naming.lookup("rmi://localhost:" + info.getPorta() + "/ope/" + info.getIdServidor());
            } catch (NotBoundException ex) {
                ex.printStackTrace();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            return op.lerArquivo(nomeDoArquivo);
        }
        return null;
    }

    @Override
    public void salvarArquivo(String nomeDoArquivo, String txt) throws RemoteException {
        List<ServerSlaveInfo> mu = Controlador.balance.retornarServidoresMenosArquivos();
        for (ServerSlaveInfo info : mu) {
            try {
                op = (Operacoes) Naming.lookup("rmi://localhost:" + info.getPorta() + "/ope/" + info.getIdServidor());
            } catch (NotBoundException ex) {
                ex.printStackTrace();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            op.salvarArquivo(nomeDoArquivo, txt);
            info.getListaDeArquivos().add(nomeDoArquivo);
        }
    }

}
