package app;

import model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class Main {
    public static void main(String[] args) {

        List<Cliente> clientes = new ArrayList<>();

        // Cadastrando endereço

        Endereco endereco1 = new Endereco("Rua A", "123", "Centro", "São Paulo", "SP", "01000-000");
        Endereco endereco2 = new Endereco("Rua B", "556", "Centro", "São Paulo", "SP", "01111-000");
        Endereco endereco3 = new Endereco("Rua B", "556", "Centro", "São Paulo", "SP", "01111-000");

        //cadastro de clinete

        try {
            Cliente c1 = new Cliente("1", "João", "joao@email.com", "12345678900",
                    LocalDate.of(2008, 10, 10), LocalDate.now(), endereco1, true);
            clientes.add(c1);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        try {
            Cliente c2 = new Cliente("2", "Maria", "maria@email.com", "98765432100",
                    LocalDate.of(1990, 5, 20), LocalDate.now(), endereco2, true);
            clientes.add(c2);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        try {
            Cliente c3 = new Cliente("3", "Helena", "maria@email.com", "98765432100",
                    LocalDate.of(1991, 5, 20), LocalDate.now(), endereco3, true);
            clientes.add(c3);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }


        listarClientes(clientes);





        //criar um novo item de pedido

                Produto produto1 = new Produto("P001", "Notebook", "Notebook Gamer", 5000.00, 10, Departamento.ELETRONICO);
                Produto produto2 = new Produto("P002", "Mouse", "Mouse Gamer", 150.00, 50, Departamento.ELETRONICO);
                Produto produto3 = new Produto("P003", "Camiseta", "Camiseta de algodão", 50.00, 200, Departamento.VESTUARIO);
                Produto produto4 = new Produto("P004", "Java Efetivo 3a edição", "Livro sobre Java", 120.00, 50, Departamento.LIVROS);

                Pedido pedido = new Pedido("PED123", null, new ArrayList<>());

                pedido.adicionarItem(produto1, 2);
                pedido.adicionarItem(produto2, 1);
                pedido.adicionarItem(produto3, 2);
                pedido.adicionarItem(produto4, 1);


                Map<Departamento, List<ItemDePedido>> itensPorDepartamento = pedido.listarItensPorDepartamento();

                System.out.println("Itens por departamento:");

                itensPorDepartamento.forEach((departamento, itens) -> {
                    System.out.println("Departamento: " + departamento);
                    System.out.println("Itens:");
                    System.out.println("Valor total do departamento: " + pedido.calcularValorTotalPorDepartamento(departamento));
                    itens.forEach(item -> System.out.println(item));
                    System.out.println("-------------------------------");
                });

                // Exemplo de cupom com 10% de desconto válido até 31/12/2025

                CupomDeDesconto cupom = new CupomDeDesconto("DESCONTO10", 10.0, LocalDateTime.of(2025, 12, 31, 23, 59));
                pedido.aplicarCupomDesconto(cupom);

                // Exibir data e hora da compra
                LocalDateTime dataHoraCompra = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

                System.out.println("\n===== RESUMO DA COMPRA =====");
                System.out.println("Data e hora da compra: " + dataHoraCompra.format(formatter));
                System.out.println();

                double totalSemDesconto = pedido.getItens().stream()
                        .mapToDouble(item -> item.produto().getPreco() * item.quantidade())
                        .sum();

                double totalComDesconto = pedido.calcularValorTotal();
                double valorEconomizado = totalSemDesconto - totalComDesconto;

                System.out.printf("Valor total do pedido (sem desconto): R$ %.2f%n", totalSemDesconto);

                if (pedido.getCupomDeDesconto().isPresent()) {
                    CupomDeDesconto cupomUsado = pedido.getCupomDeDesconto().get();
                    System.out.println("Cupom de desconto aplicado: " + cupomUsado.getCodigo());
                    System.out.printf("Desconto aplicado: %.2f%%%n", cupomUsado.getPercentualDesconto());
                    System.out.printf("Valor economizado: R$ %.2f%n", valorEconomizado);
                    System.out.printf("Valor final com desconto: R$ %.2f%n", totalComDesconto);
                } else {
                    System.out.println("Nenhum cupom de desconto aplicado.");
                    System.out.printf("Valor final: R$ %.2f%n", totalSemDesconto);
                }

            }

            public static void listarClientes(List<Cliente>clientes){
                System.out.println("===Clientes Cadastrados===");
                for (Cliente cliente : clientes){
                    cliente.detalhesCliente();
                }
            }
        }
