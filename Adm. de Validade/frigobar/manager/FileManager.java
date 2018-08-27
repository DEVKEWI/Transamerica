/*
 * Projeto destribuido por DEVKEWI COMPANY - Todos os direitos reservados.
 * Para alteração do código fonte estará disposto a concordar com termos.
 */
package frigobar.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import me.kewi.Frigobar;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author DEVKEWI COMPANY - Kewilleen
 */
public class FileManager {

    private Path p = Paths.get("");
    private File dir = new File(p.toAbsolutePath().toString() + "\\lib\\Dados");
    private File data = new File(dir.getAbsolutePath() + "\\Controle.xls");
    private FileOutputStream fileOut;
    private boolean exists;

    public File getData() {
        if (!dir.exists()) {
            dir.mkdir();
        }
        return data;
    }

    public boolean existsData() {
        boolean b = getData().exists();
        if (!b) {
            try {
                data.createNewFile();
            } catch (IOException ex) {
                Frigobar.sendInfo(0, ex.getMessage(), "Erro");
            }
        }
        return b;
    }

    private void setExists(boolean b) {
        this.exists = b;
    }

    public void createDataFile() {
        Workbook w = new HSSFWorkbook();
        if (!existsData()) {
            try {
                data.createNewFile();
                w.createSheet("Controle");
                w.createSheet("Produtos-ID");
                fileOut = new FileOutputStream(data);
                w.write(fileOut);
                fileOut.close();
                w.close();
                setExists(true);
            } catch (IOException ex) {
                Frigobar.sendInfo(0, ex.getMessage(), "Erro");
                setExists(false);
            }
        }
    }

    public boolean checkExistsFiles() {
        createDataFile();
        return exists;
    }

}
