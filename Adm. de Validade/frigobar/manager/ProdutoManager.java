/*
 * Projeto destribuido por DEVKEWI COMPANY - Todos os direitos reservados.
 * Para alteração do código fonte estará disposto a concordar com termos.
 */
package frigobar.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DEVKEWI COMPANY - Kewilleen
 */
public class ProdutoManager {

    private final Map<Double, Produto> map = new HashMap<>();

    /**
     *
     * @param id
     * @return Se há o ID na Map
     */
    public boolean hasId(double id) {
        return map.containsKey(id);
    }

    /**
     *
     * @param id
     * @return Nome do produto
     */
    public String getProdutoPorId(double id) {
        return map.get(id).getProduto();
    }

    /**
     *
     * @param id
     * @return Código do Almox.
     */
    public String getAlmoxPorId(double id) {
        return map.get(id).getAlmox();
    }

    /**
     *
     * @param id
     * @param nome
     * @param almox
     */
    public void setProduto(double id, String nome, String almox) {
        this.map.put(id, new Produto(nome, almox));
    }

    /**
     *
     * @param almox
     * @return Se ha existencia de almoxerifado
     */
    public boolean hasAlmox(String almox) {
        if (map.isEmpty()) {
            return false;
        }
        for (Produto produto : getProdutos()) {
            if (produto.getAlmox().equalsIgnoreCase(almox)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return Total de ID(s) no map
     */
    public double getIDs() {
        return map.keySet().size();
    }

    /**
     *
     * @return Lista de produtos no sistema
     */
    public List<Produto> getProdutos() {
        List<Produto> l = new ArrayList<>();
        for (double id = 0; id < getIDs(); id++) {
            l.add(map.get(id));
        }
        return l;
    }

    /**
     *
     * @param almox
     * @return Nome do produto
     */
    public String getProdutoNomePorAlmox(String almox) {
        for (Produto p : getProdutos()) {
            if (p.getAlmox().equalsIgnoreCase(almox)) {
                return p.getProduto();
            }
        }
        return null;
    }

    /**
     *
     * @return Todos os nomes dos produtos
     */
    public List<String> getProdutosNome() {
        List<String> l = new ArrayList<>();
        for (Produto produto : getProdutos()) {
            l.add(produto.getProduto());
        }
        return l;
    }

    /**
     *
     * @param nome
     * @return Se há um produto com esse nome
     */
    public boolean hasProduto(String nome) {
        for (String produto : getProdutosNome()) {
            if (produto.equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getAlmoxs() {
        List<String> l = new ArrayList<>();
        for (Produto produto : getProdutos()) {
            l.add(produto.getAlmox());
        }
        return l;
    }

    public String getAlmoxPorNome(String nome) {
        for (Produto produto : getProdutos()) {
            if (produto.getProduto().equalsIgnoreCase(nome)) {
                return produto.getAlmox();
            }
        }
        return null;
    }

    public double getIdPorNome(String nome) {
        double id = 0;
        for (double d : map.keySet()) {
            Produto produto = map.get(d);
            if (produto.getProduto().equalsIgnoreCase(nome)) {
                id = d;
                break;
            }
        }
        return id;
    }

    public double getIdPorAlmox(String almox) {
        double id = 0;
        for (double d : map.keySet()) {
            Produto produto = map.get(d);
            if (produto.getAlmox().equalsIgnoreCase(almox)) {
                id = d;
                break;
            }
        }
        return id;
    }

    public void removeId(double id) {
        this.map.remove(id);
    }

}
