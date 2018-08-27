/*
 * Projeto destribuido por DEVKEWI COMPANY - Todos os direitos reservados.
 * Para alteração do código fonte estará disposto a concordar com termos.
 */
package frigobar.manager;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DEVKEWI COMPANY - Kewilleen
 */
public class ValidadeManager {

    private Map<Double, Validade> validade = new HashMap<>();
    private double id;

    /**
     *
     * @param id
     * @param nome
     * @param code
     * @param lote
     * @param qnt
     * @param tAlmox
     * @param vencimento
     * @param estado
     */
    public void setValidade(double id, String nome, String code, int lote, int qnt, String tAlmox, String vencimento, Estado estado) {
        this.validade.put(id, new Validade(new Produto(nome, code), lote, qnt, tAlmox, vencimento, estado));
    }

    /**
     *
     * @return Obter ultimo ID salvo
     */
    public double getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(double id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @return Verifica se há o ID na map
     */
    public boolean hasProduto(double id) {
        return validade.containsKey(id);
    }

    /**
     *
     * @param id
     * @return Nome do produto por ID
     */
    public String getProduto(double id) {
        return validade.get(id).getProduto().getProduto();
    }

    /**
     *
     * @param id
     * @return Código do produto por ID
     */
    public String getAlmox(double id) {
        return validade.get(id).getProduto().getAlmox();
    }

    /**
     *
     * @param id
     * @return O número do lote por ID
     */
    public int getLote(double id) {
        return validade.get(id).getLote();
    }

    /**
     *
     * @param id
     * @return A quantidade por ID
     */
    public int getQnt(double id) {
        return validade.get(id).getQnt();
    }

    /**
     *
     * @param id
     * @return A data de transfêrenia do almoxerifado
     */
    public String getTAlmox(double id) {
        return validade.get(id).getAlmox();
    }

    /**
     *
     * @param id
     * @return A data de validade do produto
     */
    public String getValidade(double id) {
        return validade.get(id).getVenci();
    }

    /**
     *
     * @param id
     * @return Estado do vencimento
     */
    public Estado getEstado(double id) {
        return validade.get(id).getEstado();
    }

    /**
     *
     * @param id
     */
    public void remove(double id) {
        this.validade.remove(id);
    }
}
