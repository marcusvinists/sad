/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.slave;

import br.com.sad.controlador.ServerSlaveInfo;
import br.com.sad.controlador.SlaveServices;
import br.com.sad.util.Operacoes;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author vini
 */
public class FileServerApp {
    public static ServerSlaveInfo info;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            ServerSlaveUtil util = new ServerSlaveUtil();
            List<String> listaArquivos = new LinkedList<>();
            //porta do controlador
            int portSlaveToServer = 2010;
            int portServerToSlave = 3000;

            Scanner scan = new Scanner(System.in);
            System.out.println("Favor passar o id para este servidor: ");
            int idServidor = scan.nextInt();
            util.createDir(idServidor);
            
            int portThisSlave = portServerToSlave+idServidor;
            //registrar no servidor
            info = new ServerSlaveInfo(idServidor, listaArquivos,  "/opt/sad/" + idServidor + "/",portThisSlave);
            SlaveServices ss = (SlaveServices)Naming.lookup("rmi://localhost:"+portSlaveToServer+"/ss");
            ss.registrarNovoServidor(info);
            
            //fica disponivel para realizar operacoes
            //criar conexão para slave
            Operacoes op = new OperacoesImplSlave();
            LocateRegistry.createRegistry(portThisSlave);
            Naming.bind("rmi://localhost:"+portThisSlave+"/ope/"+idServidor, op);
            
            
            System.out.println("Servidor slave está no ar...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
