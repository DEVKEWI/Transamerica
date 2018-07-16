/*
 * Projeto destribuido por DEVKEWI COMPANY - Todos os direitos reservados.
 * Para alteração do código fonte estará disposto a concordar com termos.
 */
package me.kewi.clientes;

import java.util.Date;

/**
 *
 * @author DEVKEWI COMPANY - Kewilleen
 */
public class Cliente {

    private String nome, telefone;
    private double qnt;
    private Date date;
    private Estado estado;

    public Cliente(String nome, String telefone, double qnt, Date date, Estado estado) {
        this.nome = nome;
        this.telefone = telefone;
        this.qnt = qnt;
        this.date = date;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getQnt() {
        return qnt;
    }

    public void setQnt(double qnt) {
        this.qnt = qnt;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
