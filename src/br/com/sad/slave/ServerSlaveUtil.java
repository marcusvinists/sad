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
        if (f.exists()) {
            System.err.print("local para armazenamento ja existe favor informar outro id");
        } else{ 
            if (!f.mkdir()) {
            System.out.println("Could not create directory");
        }
    }
    }
}
