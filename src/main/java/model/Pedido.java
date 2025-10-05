package model;

import model.Cliente;
import model.CupomDeDesconto;
import model.Produto;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import model.Departamento;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.BinaryOperator;



public class Pedido {

    private String numero;
    private Cliente cliente;
    private List<ItemDePedido> itens;
    private CupomDeDesconto cupomDeDesconto;

    public Pedido(String numero, Cliente cliente, List<ItemDePedido> itens) {
        this.numero = numero;
        this.cliente = cliente;
        this.itens = itens;
    }

    public String getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemDePedido> getItens() {
        return itens;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        this.itens.add(new ItemDePedido(produto, quantidade));
    }

    public double calcularValorTotal() {
        double valorTotal = itens.stream()
                .mapToDouble(item -> item.produto().getPreco() * item.quantidade())
                .sum();

        if (cupomDeDesconto != null && cupomDeDesconto.isValido()) {
            valorTotal -= valorTotal * (cupomDeDesconto.getPercentualDesconto() / 100);
        }

        return valorTotal;
    }

    public void removerItem(Produto produto) {
        this.itens.removeIf(item -> item.produto().equals(produto));
    }

    public void removerCupomDesconto() {
        this.cupomDeDesconto = null;
    }


    public Map<Departamento, List<ItemDePedido>> listarItensPorDepartamento() {
        return itens.stream()
                .collect(Collectors.groupingBy(item ->  item.produto().getDepartamento()));
    }

    public double calcularValorTotalPorDepartamento(Departamento departamento) {
        final double valorInicial = 0.0;
        Double resultado = itens.stream()
                .filter(
                        new Predicate<ItemDePedido>() {
                            @Override
                            public boolean test(ItemDePedido item) {
                                return item.produto().getDepartamento().equals(departamento);
                            }
                        }
                        //item -> item.produto().getDepartamento().equals(departamento)
                )
                .map(
                        new Function<ItemDePedido, Double>() {
                            @Override
                            public Double apply(ItemDePedido item) {
                                return item.produto().getPreco() * item.quantidade();
                            }
                        }
                        //item -> item.produto().getPreco() * item.quantidade()
                )
                .reduce(
                        valorInicial,
                        new BinaryOperator<Double>() {
                            @Override
                            public Double apply(Double contador, Double preco) {
                                return contador + preco;
                            }
                        }
                        //(contador, preco) -> contador + preco
                );

        return resultado;
    }


    public void aplicarCupomDesconto(CupomDeDesconto cupom) {
        this.cupomDeDesconto = cupom;
    }

    public Optional<CupomDeDesconto> getCupomDeDesconto() {
        return Optional.ofNullable(cupomDeDesconto);
    }

}

