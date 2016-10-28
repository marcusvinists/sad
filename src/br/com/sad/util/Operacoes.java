/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.util;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author vini
 */
public interface Operacoes extends Remote{
    
    
    public List listarArquivos() throws RemoteException;
    
    public void removerArquivos(String nomeDoArquivo)throws RemoteException;
    
    public String lerArquivo(String nomeDoArquivo)throws RemoteException;
    
    public void salvarArquivo(String nomeDoArquivo, String txt)throws RemoteException;
}
