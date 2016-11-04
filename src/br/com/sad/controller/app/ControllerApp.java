/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.controller.app;

import br.com.sad.controller.ContextControll;
import br.com.sad.controller.ControllerImpl;
import br.com.sad.controller_slave.SlaveServices;
import br.com.sad.controller.SlaveServicesImpl;
import br.com.sad.controller_client.Controller;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Fernando
 */
public class ControllerApp {

 
    public static ContextControll ctControll;

    public static void main(String args[]) {
        ctControll = new ContextControll();
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
            Controller ct = new ControllerImpl();
            Naming.bind("rmi://localhost:"+portClient+"/ct", ct);
            

            System.out.println("Controlador de servidores está em funcionamento...");
            
        } catch (Exception e) {
            System.err.println("Erro ao executar Controlador de servidores de arquivos");
            System.err.println(e.getMessage());
        }
    }
}
