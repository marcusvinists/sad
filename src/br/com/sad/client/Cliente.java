/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sad.client;

import br.com.sad.util.Operacoes;
import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Fernando
 */
public class Cliente {

    private static String nomeDoArquivo;
    private static String txt;

    public static void main(String args[]) {
        try {
            System.out.println("Bem vindo ao SAD - Sistema de Arquivos Distrbuidos");
            Scanner scan = new Scanner(System.in);
            int portClient = 2011;
            Operacoes cs = (Operacoes) Naming.lookup("rmi://localhost:" + portClient + "/cs");

            while (true) {
                System.out.println("********************************");
                System.out.println("Selecione a operaçao desejada:");
                System.out.println("(1)deletar");
                System.out.println("(2)listar todos arquivos");
                System.out.println("(3)criar arquivo");
                System.out.println("(4)ler arquivo");
                System.out.println("(5)sair");
                System.out.println("********************************");
                System.out.println(" ");
                int servico = scan.nextInt();
                digitarNomeOuCont(servico);
                switch (servico) {
                    case 1:
                        cs.removerArquivos(nomeDoArquivo);
                        break;
                    case 2:
                        List lista = cs.listarArquivos();
                        System.out.println(" ");
                        System.out.println("********************************");
                        if (lista.size() > 0) {
                            System.out.println("Lista de arquivos: ");
                        } else {
                            System.out.println("Nenhum arquivo a ser exibido!");
                        }
                        int i = 1;
                        for (Object str : lista) {
                            System.out.println(i + "-" + str.toString());
                            i += 1;
                        }
                        System.out.println("********************************");
                        System.out.println("");
                        break;
                    case 3:
                        cs.salvarArquivo(nomeDoArquivo, txt);
                        System.out.println("********************************");
                        System.out.println("Arquivo " + nomeDoArquivo + " criado.");
                        System.out.println("********************************");
                        System.out.println("");
                        break;
                    case 4:
                        String conteudo = cs.lerArquivo(nomeDoArquivo);
                        System.out.println("********************************");
                        if (conteudo != null) {
                            System.out.println("Conteúdo do arquivo: ");
                            System.out.println(conteudo);
                        } else {
                            System.out.println("Arquivo não encontrado");
                        }
                        System.out.println("********************************");
                        System.out.println(" ");
                    case 5:
                        System.exit(0);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void digitarNomeOuCont(int servico) {
        Scanner scan = new Scanner(System.in);
        if (servico == 1 || servico == 4 || servico == 3) {
            System.out.println("Digite o nome do arquivo");
            nomeDoArquivo = scan.nextLine();
        }
        if (servico == 3) {
            System.out.println("Digite o conteúdo do arquivo");
            txt = scan.nextLine();
        }
    }

}
