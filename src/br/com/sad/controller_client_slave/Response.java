/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.controller_client_slave;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author vini
 */
public class Response implements Serializable{
    
    private ResponseEnum status;
    private OperationsEnum operation;
    private String message;
    private List listeResponse;
    private String txtResponse;

    public ResponseEnum getStatus() {
        return status;
    }

    public void setStatus(ResponseEnum status) {
        this.status = status;
    }

    public OperationsEnum getOperation() {
        return operation;
    }

    public void setOperation(OperationsEnum operation) {
        this.operation = operation;
    }
    
    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getListeResponse() {
        return listeResponse;
    }

    public void setListeResponse(List listeResponse) {
        this.listeResponse = listeResponse;
    }

    public String getTxtResponse() {
        return txtResponse;
    }

    public void setTxtResponse(String txtResponse) {
        this.txtResponse = txtResponse;
    }
}
