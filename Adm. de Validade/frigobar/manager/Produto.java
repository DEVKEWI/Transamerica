/*
 * Projeto destribuido por DEVKEWI COMPANY - Todos os direitos reservados.
 * Para alteração do código fonte estará disposto a concordar com termos.
 */
package frigobar.manager;

/**
 *
 * @author DEVKEWI COMPANY - Kewilleen
 */
class Produto {

    private String produto, almox;

    public Produto(String produto, String almox) {
        this.produto = produto;
        this.almox = almox;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getAlmox() {
        return almox;
    }

    public void setAlmox(String almox) {
        this.almox = almox;
    }

}
