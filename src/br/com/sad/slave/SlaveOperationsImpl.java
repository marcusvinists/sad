/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.slave;

import br.com.sad.slave.app.FileServerApp;
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
import br.com.sad.controller_slave.Operations;
import br.com.sad.controller_client_slave.Request;
import br.com.sad.controller_client_slave.Response;
import br.com.sad.controller_client_slave.ResponseEnum;

/**
 *
 * @author vini
 */
public class SlaveOperationsImpl extends UnicastRemoteObject implements Operations {
    private Response resp;
    
    public SlaveOperationsImpl() throws RemoteException {
        super();
        resp = new Response();
    }

    @Override
    public Response listFiles() throws RemoteException {
        List<String> arNames = new LinkedList<>();
        File folder = new File(FileServerApp.info.getPathArquivos());
        File[] listOfFiles = folder.listFiles();

        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                System.out.println("File " + listOfFile.getName());
                arNames.add(listOfFile.getName());
            } else if (listOfFile.isDirectory()) {
            }
        }
        resp.setListeResponse(arNames);
        resp.setStatus(ResponseEnum.success);
        return resp;
    }

    @Override
    public Response removeFiles(Request request) throws RemoteException {
        try {
            File file = new File(FileServerApp.info.getPathArquivos() + "/" + request.getFileName());
            file.delete();
            resp.setStatus(ResponseEnum.success);
        } catch (Exception ex) {
            resp.setStatus(ResponseEnum.error);
            resp.setMessage(ex.getMessage());
            ex.printStackTrace();
        }
        return resp;
    }

    @Override
    public Response readFiles(Request request) throws RemoteException {
        String text = null;
        try {
            File file = new File(FileServerApp.info.getPathArquivos() + "/" + request.getFileName());
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
            resp.setStatus(ResponseEnum.error);
            ex.printStackTrace();
        }
        resp.setStatus(ResponseEnum.success);
        resp.setTxtResponse(text);
        return resp;
    }

    @Override
    public Response createFiles(Request request) throws RemoteException {
        try {
//            String nomeDoArquivo, String txt
            File file = new File(FileServerApp.info.getPathArquivos() + "/" + request.getFileName());
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(request.getFileTxt());
            }
        } catch (IOException e) {
            resp.setStatus(ResponseEnum.error);
            resp.setMessage(e.getMessage());
            
            e.printStackTrace();
            System.out.println("Não foi possível criar o arquivo: " + e.getMessage());
            return resp;
        }
        resp.setStatus(ResponseEnum.success);
        return resp;
    }

}
