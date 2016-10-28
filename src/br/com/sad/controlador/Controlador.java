/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.controlador;

import br.com.sad.util.Operacoes;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Fernando
 */
public class Controlador {

    public static LoadBalancer balance;

    public static void main(String args[]) {
        balance = new LoadBalancer();
        try {

            int portSlave = 2010;
            int portClient = 2011;
            

            //Intancia obj com metodos dip para cliente
            SlaveServices ss = new SlaveServicesImpl();
            
            //criar disp conexão para slave
            LocateRegistry.createRegistry(portSlave);
            Naming.bind("rmi://localhost:"+portSlave+"/ss", ss);

            
            //criar disp conexao para client
            LocateRegistry.createRegistry(portClient);
            Operacoes cs = new ClientServicesImpl();
            Naming.bind("rmi://localhost:"+portClient+"/cs", cs);
            

            System.out.println("Controlador de servidores está em funcionamento...");
            ;
        } catch (Exception e) {
            System.err.println("Problemas ao registrar objeto");
            System.err.println(e.getMessage());
        }
    }
}
