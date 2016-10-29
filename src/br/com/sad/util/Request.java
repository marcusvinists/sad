/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.util;

import java.io.Serializable;

/**
 *
 * @author vini
 */
public class Request implements Serializable{
    private String filePath;
    private String fileName;
    private String fileTxt;

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
    
    
    
}
