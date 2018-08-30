/*
 * Projeto destribuido por DEVKEWI COMPANY - Todos os direitos reservados.
 * Para alteração do código fonte estará disposto a concordar com termos.
 */
package frigobar.manager;

import frigobar.display.Carregando;
import frigobar.display.Menu;
import frigobar.display.cadastro.Cadastros;
import frigobar.display.validade.Demanda;
import java.awt.AWTException;
import java.awt.Frame;
import static java.awt.Frame.NORMAL;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import me.kewi.Frigobar;
import static me.kewi.Frigobar.sendInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author DEVKEWI COMPANY - Kewilleen
 */
public class Validation {

    private Menu m;
    private double id;
    private frigobar.display.validade.Validation display;
    private FileManager fm;
    private ProdutoManager pm;
    private ValidadeManager vm;
    private Cadastros ca;
    private Demanda d;

    /**
     *
     * @return ID atual do ultimo produto
     */
    public double getID() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setID(double id) {
        this.id = id;
    }

    /**
     * Metodo inicial
     */
    public void checkValidation() {
        Carregando c = new Carregando();
        c.setVisible(true);
        fm = new FileManager();
        pm = new ProdutoManager();
        vm = new ValidadeManager();
        fm.checkExistsFiles();
        m = new Menu();
        display = new frigobar.display.validade.Validation();
        ca = new Cadastros();
        d = new Demanda();
        try {
            FileInputStream fis = new FileInputStream(fm.getData());
            Workbook w = new HSSFWorkbook(fis);
            Sheet ids = w.getSheetAt(1);
            int rows = ids.getPhysicalNumberOfRows();
            for (int i = 0; i < rows; i++) {
                Row row = ids.getRow(i);
                Cell indice = row.getCell(0);
                Cell produto = row.getCell(1);
                Cell almox = row.getCell(2);
                double id = indice.getNumericCellValue();
                String nome = produto.getStringCellValue();
                String code = almox.getStringCellValue();
                insertTable(id, nome, code);
                pm.setProduto(id, nome, code);
                setProdutoDemanda(nome);
            }
            if (rows != 0) {
                String s1 = rows > 1 ? rows + " iten(s)" : "um item";
                m.getInfo().setText("Tendo " + s1 + " cadastrado(s)");
            }
            Sheet s = w.getSheetAt(0);
            Row r = s.getRow(0);
            barra();
            if ((r != null) && r.getCell(0) != null) {
                c.setVisible(false);
                display.setVisible(true);
                int rowsValidation = s.getPhysicalNumberOfRows();
                for (int i = 0; i < rowsValidation; i++) {
                    Row row = s.getRow(i);
                    double id = row.getCell(0).getNumericCellValue();
                    String nome = row.getCell(1).getStringCellValue();
                    double lote = row.getCell(2).getNumericCellValue();
                    double qnt = row.getCell(3).getNumericCellValue();
                    String diaAlmox = row.getCell(4).getStringCellValue();
                    String validade = row.getCell(5).getStringCellValue();
                    String estado = row.getCell(6).getStringCellValue();
                    vm.setId(vm.getId() + 1);
                    if (checkDate(validade) && estado.equalsIgnoreCase("PENDENTE")) {
                        vm.setValidade(id, nome, pm.getAlmoxPorNome(nome), (int) lote, (int) qnt, diaAlmox, validade, Estado.PENDENTE);
                        JTable t = display.getTable();
                        TableModel tm = t.getModel();
                        DefaultTableModel dtm = (DefaultTableModel) tm;
                        dtm.addRow(new Object[]{(int) id, nome, (int) lote, (int) qnt, diaAlmox, validade});
                    }
                }
                /*
                 Verificar data dos produtos e se sim, executar a linha setVisibled
                 */
                display.setVisibled(true);
                w.close();
                fis.close();
                return;
            }
            c.setVisible(false);
            w.close();
            fis.close();
        } catch (IOException ex) {
            Frigobar.sendInfo(0, ex.getMessage(), "Erro");
        }
        m.setVisible(true);
    }

    private boolean checkDate(String validade) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dia = sdf.parse(validade);
            Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            Date firstDayWeek = c.getTime();
            for (int i = 0; i < 30; i++) {
                c.add(Calendar.DATE, i);
            }
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);
            Date lastDayWeek = c.getTime();
            return (dia.getTime() >= firstDayWeek.getTime()) && dia.getTime() <= lastDayWeek.getTime();
        } catch (ParseException ex) {
            Frigobar.sendInfo(0, ex.getMessage(), "Erro");
            return false;
        }
    }

    /**
     *
     * @return Gerenciador de protudos
     */
    public ProdutoManager getProduto() {
        return pm;
    }

    /**
     *
     * @param nome
     * @param almox
     */
    public void setProduto(String nome, String almox) {
        System.out.println("A");
        setID(pm.getIDs());
        System.out.println(this.id);
        String s1 = (getID() + 1) > 1 ? ((int) getID() + 1) + " iten(s)" : "um item";
        m.getInfo().setText("Tendo " + s1 + " cadastrado(s)");
        pm.setProduto(getID(), nome, almox);
        insertExcel(getID(), nome, almox);
        insertTable(getID(), nome, almox);
        setProdutoDemanda(nome);
    }

    /**
     *
     * @return Tela principal
     */
    public Menu getMenu() {
        return m;
    }

    private void insertExcel(double id, String nome, String almox) {
        try {
            FileInputStream fis = new FileInputStream(fm.getData());
            Workbook w = new HSSFWorkbook(fis);
            Sheet s = w.getSheetAt(1);
            Row r = s.createRow((int) id);
            Cell indice = r.createCell(0);
            indice.setCellValue(id);
            Cell produt = r.createCell(1);
            produt.setCellValue(nome);
            Cell almoxe = r.createCell(2);
            almoxe.setCellValue(almox);
            FileOutputStream fos = new FileOutputStream(fm.getData());
            w.write(fos);
            fos.close();
            w.close();
        } catch (IOException ex) {
            Frigobar.sendInfo(0, ex.getMessage(), "Erro");
        }
    }

    /**
     *
     * @return Tela de validade
     */
    public frigobar.display.validade.Validation getValidation() {
        return display;
    }

    /**
     *
     * @return Tela de cadastros
     */
    public Cadastros getCadastros() {
        return ca;
    }

    private void insertTable(double id, String nome, String almox) {
        JTable t = ca.getTable();
        TableModel tm = t.getModel();
        DefaultTableModel dtm = (DefaultTableModel) tm;
        dtm.addRow(new Object[]{(int) id, nome, almox});
        ca.getTotal().setText("Total de " + (int) (id + 1) + " produto(s) cadastrado(s)");
    }

    /**
     *
     * @param id
     * @param novoAlmox
     */
    public void editarAlmox(double id, String novoAlmox) {
        System.out.println(novoAlmox);
        try {
            FileInputStream fis = new FileInputStream(fm.getData());
            Workbook w = new HSSFWorkbook(fis);
            Sheet s = w.getSheetAt(1);
            Row r = s.getRow((int) id);
            Cell almox = r.createCell(2);
            almox.setCellValue(novoAlmox);
            FileOutputStream fos = new FileOutputStream(fm.getData());
            w.write(fos);
            fos.close();
            w.close();
            pm.setProduto(id, r.getCell(1).getStringCellValue(), novoAlmox);
            JTable t = ca.getTable();
            TableModel tm = t.getModel();
            tm.setValueAt(novoAlmox, (int) id, 2);
        } catch (IOException ex) {
            Frigobar.sendInfo(0, ex.getMessage(), "Erro");
        }
    }

    /**
     *
     * @param id
     * @param novoProduto
     */
    public void editarProduto(double id, String novoProduto) {
        System.out.println(novoProduto);
        try {
            FileInputStream fis = new FileInputStream(fm.getData());
            Workbook w = new HSSFWorkbook(fis);
            Sheet s = w.getSheetAt(1);
            Row r = s.getRow((int) id);
            removeProdutoDemanda(r.getCell(1).getStringCellValue());
            Cell almox = r.createCell(1);
            almox.setCellValue(novoProduto);
            FileOutputStream fos = new FileOutputStream(fm.getData());
            w.write(fos);
            fos.close();
            w.close();
            pm.setProduto(id, novoProduto, r.getCell(2).getStringCellValue());
            JTable t = ca.getTable();
            TableModel tm = t.getModel();
            tm.setValueAt(novoProduto, (int) id, 1);
            setProdutoDemanda(novoProduto);
        } catch (IOException ex) {
            Frigobar.sendInfo(0, ex.getMessage(), "Erro");
        }
    }

    /**
     *
     * @return Tela de demanda (Validade)
     */
    public Demanda getDemanda() {
        return d;
    }

    private void setProdutoDemanda(String produto) {
        JComboBox itens = d.getProdutos();
        DefaultComboBoxModel dcbm = (DefaultComboBoxModel) itens.getModel();
        dcbm.addElement(produto);
        itens.setModel(dcbm);
    }

    private void removeProdutoDemanda(String produto) {
        JComboBox itens = d.getProdutos();
        DefaultComboBoxModel dcbm = (DefaultComboBoxModel) itens.getModel();
        dcbm.removeElement(produto);
        itens.setModel(dcbm);
    }

    /**
     *
     * @return Gerenciador de validade
     */
    public ValidadeManager getValidade() {
        return vm;
    }

    /**
     *
     * @return Gerenciador de arquivo
     */
    public FileManager getFileManager() {
        return fm;
    }

    private void barra() {
        if (!java.awt.SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();
        ActionListener ac = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!m.isVisible()) {
                    m.setVisible(true);
                }
            }
        };
        PopupMenu pm = new PopupMenu();
        MenuItem mi = new MenuItem("Menu");
        mi.addActionListener(ac);
        pm.add(mi);
        pm.addSeparator();
        ActionListener acSair = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        MenuItem sair = new MenuItem("Sair");
        sair.addActionListener(acSair);
        pm.add(sair);
        final TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/frigobar/imagens/success.png")), "Frigobar - Validade", pm);
        trayIcon.setImageAutoSize(true);
        MouseListener ml = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!m.isVisible()) {
                    m.setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        trayIcon.addActionListener(ac);
        trayIcon.addMouseListener(ml);
        try {
            tray.add(trayIcon);
            trayIcon.displayMessage("Verificação Frigobar", "Restaure o MENU, caso tenha fechado clicando aqui", TrayIcon.MessageType.INFO);
        } catch (AWTException e) {
            Frigobar.sendInfo(2, e.getMessage(), "Error");
        }
    }

    /**
     *
     * @param frame
     */
    public void minimizar(final Frame frame) {
        frame.addWindowStateListener(new WindowAdapter() {
            @Override
            public void windowStateChanged(WindowEvent evt) {
                int oldState = evt.getOldState();
                int newState = evt.getNewState();
                if ((oldState & Frame.ICONIFIED) == 0 && (newState & Frame.ICONIFIED) != 0) {
                    frame.setVisible(false);
                } else if ((oldState & Frame.ICONIFIED) != 0 && (newState & Frame.ICONIFIED) == 0) {
                    frame.setVisible(true);
                    frame.setState(NORMAL);
                }
            }
        });
    }

}
