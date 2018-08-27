# Projetos ao Hotel Transamérica ☆☆☆☆☆

Este é um projeto que engloba todos os projetos que fiz ao Hotel, a finalidade de outros programadores auxiliar ou propor sugestões ao desenvolvimento.

## Iniciado

Em meio ao ambiente corporativo, senti falta de ferramentas que deve satisfazer a eficiência de pesquisa e informações do cliente que querem um determinado produto, tendo base disso, desenvolvi um aplicativo com finalidade dinâmica em janelas(JFrame).

<h2 align="center">PROJETOS FEITOS:</h2>
<p align="center"><b><a href="https://github.com/DEVKEWI/Transamerica#requisi%C3%A7%C3%A3o-de-pudins">Admin. de Pudim</a> | <a href="https://github.com/DEVKEWI/Transamerica#administra%C3%A7%C3%A3o-de-validades">Admin. de Vencimentos</a> | <a href="#">Futuro</a></b></p>

### Administração de validade(s)

Na demanda de produtos ao Frigobar, o controle de validade de produtos é necessário. Pensando nisso, informar toda vez quando há um produto próximo a sua validade em período de trinta dias. Organizado em PRODUTO e VENCIMENTO, como funciona?

#### PRODUTO

 Ele tem o objetivo de organizar os produtos, sendo sua diretriz:

 * ID (Índice Dinâmico) - Tornar a pesquisa eficênte.
 * Produto - Denominar o nome do ID.
 * Código Almoxerifado - Para requisitar e/ou solicitar o produto.

<img src="https://raw.githubusercontent.com/DEVKEWI/Transamerica/master/Adm.%20de%20Validade/IMG/Screenshot_6.png"><br>
*Imagem destinada a exibir os produtos cadastrados*

#### VENCIMENTO

 - Destinado a administrar o processo de vencimento em período de trinta dias.

Exemplo: Cadastrei uma nova demanda no dia 27/08 e o produto irá vencer no dia 28/10, o programa irá emitir um alerta quando chegar no dia 28/09. 

Composto por:

 * ID
 * PRODUTO
 * Lote - Determinado o vencimento pela quantidade recebida nas docas.
 * Quantidade - A quantidade que será transferida ao departamento do Frigobar.
 * T.Almox - Data de Transfência do Estoque para o do Frigobar.
 * VENCIMENTO
 
 <img src="https://raw.githubusercontent.com/DEVKEWI/Transamerica/master/Adm.%20de%20Validade/IMG/Screenshot_13.png"><br>
 *Imagem com propósito de apresentar os vencimentos breves*
 
*Para mais imagens, acesse* [Adm. de Validade](https://github.com/DEVKEWI/Transamerica/tree/master/Adm.%20de%20Validade/IMG)

### Interagir com Microsoft Office Excel

Todos os dados são salvos em apenas uma planilha sendo duas abas (Produtos e Vencimentos)

```
try {
 FileInputStream fis = new FileInputStream(fm.getData());
 Workbook w = new HSSFWorkbook(fis);
 Sheet ids = w.getSheetAt(1);
 int rows = ids.getPhysicalNumberOfRows();
 for (int i = 0; i < rows; i++) {
  Row row = ids.getRow(i);
  double id = row.getCell(0).getNumericCellValue();
  String nome = row.getCell(1).getStringCellValue();
  String code = row.getCell(2).getStringCellValue();
  insertTable(id, nome, code);
  pm.setProduto(id, nome, code);
  setProdutoDemanda(nome);
 }
} catch (IOException ex) {
  Frigobar.sendInfo(0, ex.getMessage(), "Erro");
}
```
*Exemplo retirado do programa Frigobar.jar*

### Deseja ver como funciona no seu computador?
<a href="https://github.com/DEVKEWI/Transamerica/raw/master/Adm.%20de%20Validade/Frigobar.jar">Fazer download</a> <br><br>

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
