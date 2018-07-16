/*
 * Projeto destribuido por DEVKEWI COMPANY - Todos os direitos reservados.
 * Para alteração do código fonte estará disposto a concordar com termos.
 */
package me.kewi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import me.kewi.clientes.Cliente;
import me.kewi.clientes.Estado;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author DEVKEWI COMPANY - Kewilleen
 */
public class Dados {

    private final String user = System.getProperty("user.name");
    private final String fileName = "PUDIM.xls";
    private final String path = "C:\\Users\\" + user + "\\Desktop\\" + fileName;
    private FileInputStream fis;
    private FileOutputStream fos;

    public File getFile() {
        return new File(path);
    }

    public void startFile() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        if (!getFile().exists()) {
            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet();
            Row row = sheet.createRow(0); // para cima ou baixo
            /* Cell cell = row.createCell(0); para esquerda ou direita */
            Cell id = row.createCell(0);
            id.setCellValue(1);
            Cell nome = row.createCell(1);
            nome.setCellValue("DEVKEWI");
            Cell telefone = row.createCell(2);
            telefone.setCellValue("(11) 9xxxx-xxxx");
            Cell qnt = row.createCell(3);
            qnt.setCellValue(1);
            Cell data = row.createCell(4);
            Calendar c = Calendar.getInstance();
            Date d = c.getTime();
            data.setCellValue(sdf.format(d));
            Cell pedido = row.createCell(5);
            pedido.setCellValue("PENDENTE");
            try {
                fos = new FileOutputStream(getFile());
                workbook.write(fos);
                fos.close();
                workbook.close();
            } catch (IOException e) {
                Excel.errorMessage(e);
            }
            JOptionPane.showMessageDialog(null, "Fechando o sistema para auto-configuração (OBS: Isso exibirá apenas uma vez)");
            System.exit(0);
            return;
        }
        try {
            fis = new FileInputStream(getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            int i = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    Row r = cell.getRow();
                    i++;
                    Estado s = Estado.PENDENTE;
                    for (Estado e : Estado.values()) {
                        if (r.getCell(5).getStringCellValue().equalsIgnoreCase(e.name())) {
                            s = e;
                        }
                    }
                    Cliente c = new Cliente(r.getCell(1).getStringCellValue(), r.getCell(2).getStringCellValue(), r.getCell(3).getNumericCellValue(), sdf.parse(r.getCell(4).getStringCellValue()), s);
                    Excel.getManager().setCliente(r.getCell(0).getNumericCellValue(), c);
                }
            }
            workbook.close();
            fis.close();
        } catch (IOException e) {
            Excel.errorMessage(e);
        } catch (ParseException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean inWeek(Date dia) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date firstDayWeek = c.getTime();
        for (int i = 0; i < 7; i++) {
            c.add(Calendar.DATE, 1);
        }
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        Date lastDayWeek = c.getTime();
        return (dia.getTime() >= firstDayWeek.getTime()) && dia.getTime() <= lastDayWeek.getTime();
    }

    public void inserir(double os, String nome, String telefone, String qnt, String data) {
        try {
            fis = new FileInputStream(getFile());
            Workbook w = new HSSFWorkbook(fis);
            Sheet sheet = w.getSheetAt(0);
            int rows = 0;
            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                rows++;
            }
            Row row = sheet.getRow((rows - 1));
            os = os + row.getCell(0).getNumericCellValue();
            Row r = sheet.createRow(rows);
            Cell id = r.createCell(0);
            id.setCellValue(os);
            Cell name = r.createCell(1);
            name.setCellValue(nome);
            Cell cellphone = r.createCell(2);
            cellphone.setCellValue(telefone);
            Cell quantidade = r.createCell(3);
            quantidade.setCellValue(Integer.parseInt(qnt));
            Cell date = r.createCell(4);
            date.setCellValue(data);
            Cell pedido = r.createCell(5);
            pedido.setCellValue("PENDENTE");
            fos = new FileOutputStream(getFile());
            w.write(fos);
            w.close();
            fos.close();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Cliente c = new Cliente(r.getCell(1).getStringCellValue(), r.getCell(2).getStringCellValue(), r.getCell(3).getNumericCellValue(), sdf.parse(r.getCell(4).getStringCellValue()), Estado.PENDENTE);
            Excel.getManager().setCliente(r.getCell(0).getNumericCellValue(), c);
        } catch (IOException e) {
            Excel.errorMessage(e);

        } catch (ParseException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editar(String os, String nome, String telefone, String qnt, String dia, String es) {
        try {
            fis = new FileInputStream(getFile());
            Workbook w = new HSSFWorkbook(fis);
            Sheet sheet = w.getSheetAt(0);
            String[] s = os.split("\\.");
            int i = Integer.parseInt(s[0]);
            Row r = sheet.getRow(i - 1);
            Cliente c = Excel.getManager().getMap().get(Double.parseDouble(os));
            if (!nome.isEmpty()) {
                r.getCell(1).setCellValue(nome);
                c.setNome(nome);
            }
            if (!telefone.isEmpty()) {
                r.getCell(2).setCellValue(telefone);
                c.setTelefone(telefone);
            }
            if (!qnt.isEmpty()) {
                r.getCell(3).setCellValue(Integer.parseInt(qnt));
                c.setQnt(Double.parseDouble(qnt));
            }
            if (!dia.isEmpty()) {
                r.getCell(4).setCellValue(dia);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                c.setDate(sdf.parse(dia));
            }
            if (!es.isEmpty()) {
                r.getCell(5).setCellValue(es);
                Estado estado = null;
                for (Estado e : Estado.values()) {
                    if (es.equalsIgnoreCase(e.name())) {
                        estado = e;
                    }
                }
                c.setEstado(estado);
            }
            fos = new FileOutputStream(getFile());
            w.write(fos);
            w.close();
            fos.close();
            Excel.getManager().setCliente(Double.parseDouble(os), c);
        } catch (IOException e) {
            Excel.errorMessage(e);
        } catch (ParseException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
