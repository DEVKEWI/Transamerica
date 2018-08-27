/*
 * Projeto destribuido por DEVKEWI COMPANY - Todos os direitos reservados.
 * Para alteração do código fonte estará disposto a concordar com termos.
 */
package frigobar.manager;

/**
 *
 * @author DEVKEWI COMPANY - Kewilleen
 */
class Validade {

    private Produto produto;
    private int lote, qnt;
    private String almox, venci;
    private Estado estado;

    public Validade(Produto produto, int lote, int qnt, String almox, String venci, Estado estado) {
        this.produto = produto;
        this.lote = lote;
        this.qnt = qnt;
        this.almox = almox;
        this.venci = venci;
        this.estado = estado;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getLote() {
        return lote;
    }

    public void setLote(int lote) {
        this.lote = lote;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public String getAlmox() {
        return almox;
    }

    public void setAlmox(String almox) {
        this.almox = almox;
    }

    public String getVenci() {
        return venci;
    }

    public void setVenci(String venci) {
        this.venci = venci;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
