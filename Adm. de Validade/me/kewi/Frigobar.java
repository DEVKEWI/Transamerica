/*
 * Projeto destribuido por DEVKEWI COMPANY - Todos os direitos reservados.
 * Para alteração do código fonte estará disposto a concordar com termos.
 */
package me.kewi;

import frigobar.manager.Validation;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author DEVKEWI COMPANY - Kewilleen
 */
public class Frigobar {

    private static Validation v;
    private static Frigobar f;
    private static frigobar.display.validade.Validation display_v;

    public static void main(String[] args) {
        f = new Frigobar();
        v = new Validation();
        v.checkValidation();
        display_v = v.getValidation();
    }

    /**
     *
     * @param i
     * @param o
     * @param t
     */
    public static void sendInfo(int i, Object o, String t) {
        boolean b = o instanceof String;
        String s = b ? (String) o : "";
        s = (i == 0) ? "<html><body><center><bold>Contacte ao programador sobre o erro:</bold><br>" + s + "</center></body></html>" : "<html>" + s.replace("\n", "<br>") + "</html>";

        JOptionPane optionPane = i == 5 ? new JOptionPane(b ? s : o, 1, JOptionPane.DEFAULT_OPTION, (Icon) f.getSuccess()) : new JOptionPane(b ? s : o, i);
        JDialog dialog = optionPane.createDialog(i == 0 ? "Erro" : t);

        dialog.setIconImage(f.getIcon());
        dialog.setModal(true);
        dialog.setVisible(true);
        if (i == 0) {
            System.exit(0);
        } else {
            dialog.setVisible(false);
        }
    }

    /**
     *
     * @return Gerenciador principal
     */
    public static Validation getValidation() {
        return v;
    }

    /**
     *
     * @return Tela de vencimento
     */
    public frigobar.display.validade.Validation getValidationDisplay() {
        return display_v;
    }

    public Image getIcon() {
        return Toolkit.getDefaultToolkit().getImage(getClass().getResource("/frigobar/imagens/Icon/info16x16.png"));
    }

    private Icon getSuccess() {
        return new ImageIcon(getClass().getResource("/frigobar/imagens/Icon/success32x32.png"));
    }

    /**
     *
     * @param str
     * @return Verifica se a string é numerica
     */
    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
            System.out.println(d);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
