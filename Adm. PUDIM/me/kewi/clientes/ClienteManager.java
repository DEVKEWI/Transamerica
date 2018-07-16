/*
 * Projeto destribuido por DEVKEWI COMPANY - Todos os direitos reservados.
 * Para alteração do código fonte estará disposto a concordar com termos.
 */
package me.kewi.clientes;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DEVKEWI COMPANY - Kewilleen
 */
public class ClienteManager {

    private Map<Double, Cliente> cliente = new HashMap<>();

    /**
     * 
     * @param os
     * @return Se a O.S está na Map
     */
    public boolean isCliente(Double os) {
        return cliente.containsKey(os);
    }

    /**
     *
     * @param os
     * @param cliente
     */
    public void setCliente(Double os, Cliente cliente) {
        this.cliente.put(os, cliente);
    }

    public Map<Double, Cliente> getMap() {
        return this.cliente;
    }

}
