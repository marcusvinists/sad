/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.controller_client_slave;

import java.io.Serializable;

/**
 *
 * @author vini
 */
public class Request implements Serializable{
    private String filePath;
    private String fileName;
    private String fileTxt;
     private OperationsEnum operation;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileTxt() {
        return fileTxt;
    }

    public void setFileTxt(String fileTxt) {
        this.fileTxt = fileTxt;
    }

    public OperationsEnum getOperation() {
        return operation;
    }

    public void setOperation(OperationsEnum operation) {
        this.operation = operation;
    }
}
