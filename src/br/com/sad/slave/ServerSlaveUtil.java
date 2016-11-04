/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.slave;

import java.io.File;
import java.rmi.RemoteException;

/**
 *
 * @author vini
 */
public class ServerSlaveUtil {

    public void createDir(int id) throws RemoteException {
        String path = "/opt/sad/" + id + "/";
        File f = new File(path);
        if (!f.exists()) {
            if (!f.mkdir()) {
            System.out.println("Não foi possível criar local de armazenamento:");
            System.out.println(path);
        }
    }
    }
}
