package model;

public class Produto{

    private String codigo;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidadeEstoque;
    private Departamento departamento;

    public Produto(String codigo, String nome, String descricao, double preco, int quantidadeEstoque, Departamento departamento) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.departamento = departamento;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public Departamento getDepartamento() {
        return departamento;
    }


    public void baixarEstoque(int quantidade) {
        if (quantidade > quantidadeEstoque) {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque");
        }
        this.quantidadeEstoque -= quantidade;
    }

    @Override
    public String toString() {
        return String.format(
                "\nProduto:\n" +
                        "  Código: %s\n" +
                        "  Nome: %s\n" +
                        "  Descrição: %s\n" +
                        "  Preço: R$ %.2f\n" +
                        "  Quantidade em Estoque: %d\n",
                codigo, nome, descricao, preco, quantidadeEstoque
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return codigo.equals(produto.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

}
