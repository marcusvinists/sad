/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.util;

/**
 *
 * @author vini
 */
public enum OperationsEnum {
    create(3),
    list(2),
    remove(1),
    read(4),
    exit(5);

    private final int operationVal;

    OperationsEnum(int operationVal) {
        this.operationVal = operationVal;
    }

    public int getOperationVal() {
        return operationVal;
    }

}
