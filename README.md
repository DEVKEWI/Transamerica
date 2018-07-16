# Projetos ao Hotel Transamérica ☆☆☆☆☆

## Iniciado

Em meio ao ambiente corporativo, senti falta de ferramentas que deve satisfazer a eficiência de pesquisa e informações do cliente que querem um determinado produto, tendo base disso, desenvolvi um aplicativo com finalidade dinâmica em janelas(JFrame).

### Requisição de pudin(s)

Para testes, utilizei o Microsoft Excel como fonte de dados para armazenar as requisições, sendo sua composição:
 * OS (Ordem de Serviço) - Desvendar os dados do cliente após uma requisição de produto.
 * Nome (Nome abreviado) - Referencia de quem irá obter o produto.
 * Telefone (Número para contato) - Informar caso há uma alteração no serviço.
 * Quantidade - Denominar quanto do produto o cliente deseja solicitar.
 * Data (Data abreviada) - Data de retirada do produto.
 * Estado do produto (PENDENTE, EMITIDO, ENTREGUE) - Atualizar conforme o pedido.
 Um exemplo pode ser visto no [resultado de busca de dados](https://github.com/DevKewi/Transamerica#resultado)

Para solicitar um determinado produto, interagir com a ferramenta de **Requisição**

![alt text](https://raw.githubusercontent.com/DevKewi/Transamerica/master/Adm.%20PUDIM/IMG/Screenshot_2.png)

*Para mais imagens, acesse* [Adm. PUDIM](https://github.com/DevKewi/Transamerica/tree/master/Adm.%20PUDIM/IMG)

### APACHE POI

Utilizando o [APACHE POI](https://github.com/apache/poi) para o gerenciamos de conexão entre arquivos .XLS, exemplo abaixo demonstra uma busca de dados a partir do estado PENDENTE:

```
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
      Excel.getManager().setCliente(os, cliente);
    }
  }
  workbook.close();
  fis.close();
} catch (IOException e) {
  Excel.errorMessage(e);
} catch (ParseException ex) {
  Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
}
```
### Resultado:

![alt text](https://raw.githubusercontent.com/DevKewi/Transamerica/master/Adm.%20PUDIM/IMG/Screenshot_3.png)
