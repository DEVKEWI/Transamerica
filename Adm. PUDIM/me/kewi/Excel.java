/*
 * Projeto destribuido por DEVKEWI COMPANY - Todos os direitos reservados.
 * Para alteração do código fonte estará disposto a concordar com termos.
 */
package me.kewi;

import java.io.IOException;
import javax.swing.JOptionPane;
import me.kewi.clientes.ClienteManager;
import me.kewi.interfaces.Carregando;
import me.kewi.interfaces.Representante;

/**
 *
 * @author DEVKEWI COMPANY - Kewilleen
 */
public class Excel {

    private static Dados d;
    private static ClienteManager cm;

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        cm = new ClienteManager();
        d = new Dados();
        Carregando c = new Carregando();
        c.setVisible(true);
        d.startFile();
        c.setVisible(false);
        Representante r = new Representante();
        r.setVisible(true);
    }

    public static Dados getDados() {
        return d;
    }

    public static ClienteManager getManager(){
        return cm;
    }
    
    public static void errorMessage(IOException e) {
        JOptionPane.showMessageDialog(null, "Um erro foi encontrado, por favor informar ao programador:\n" + e.getMessage(), "Erro", 2);
    }

}
