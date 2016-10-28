/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.slave;

import br.com.sad.util.Operacoes;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author vini
 */
public class OperacoesImplSlave extends UnicastRemoteObject implements Operacoes {

    public OperacoesImplSlave() throws RemoteException {
        super();
    }

    @Override
    public List listarArquivos() throws RemoteException {
        List<String> arNames = new LinkedList<>();
        File folder = new File(ServerSlave.info.getPathArquivos());
        File[] listOfFiles = folder.listFiles();

        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                System.out.println("File " + listOfFile.getName());
                arNames.add(listOfFile.getName());
            } else if (listOfFile.isDirectory()) {
                //System.out.println("Directory " + listOfFile.getName());
            }
        }
        return arNames;
    }

    @Override
    public void removerArquivos(String nomeDoArquivo) throws RemoteException {
        try {
            File file = new File(ServerSlave.info.getPathArquivos() + "/" + nomeDoArquivo);
            file.delete();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String lerArquivo(String nomeDoArquivo) throws RemoteException {
        String text = null;
        try {
            File file = new File(ServerSlave.info.getPathArquivos() + "/" + nomeDoArquivo);
            if (file.exists()) {
                text = "";
                List<String> lines = Files.readAllLines((file.toPath()),
                        Charset.defaultCharset());

                for (String line : lines) {
                    text = text.concat(line);
                    System.out.println(line);
                }
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return text;
    }

    @Override
    public void salvarArquivo(String nomeDoArquivo, String txt) throws RemoteException {
        try {
            File file = new File(ServerSlave.info.getPathArquivos() + "/" + nomeDoArquivo);
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(txt);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Não foi possível criar o arquivo: " + e.getMessage());
        }
    }

}
