/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.controller_client_slave;

/**
 *
 * @author vini
 */
public enum OperationsEnum {
    create(3),
    list(2),
    remove(1),
    read(4),
    exit(5),
    not_impl(6);

    private final int operationVal;

    OperationsEnum(int operationVal) {
        this.operationVal = operationVal;
    }

    public int getOperationVal() {
        return operationVal;
    }

     public static OperationsEnum retornarEnum(int servico){
        switch (servico) {
            case 1:
                return OperationsEnum.remove;
            case 2:
                return OperationsEnum.list;
            case 3:
                return OperationsEnum.create;
            case 4:
                return OperationsEnum.read;
            case 5:
                return OperationsEnum.exit;
            default:
                return OperationsEnum.not_impl;
        }
    }
}
