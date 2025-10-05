package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


public class Cliente {

    private String id;
    private String nome;
    private String email;
    private String documento;
    private LocalDate dataNascimento;
    private LocalDate dataCadastro;
    private boolean ativo;
    private Endereco endereco;

    public Cliente(String id, String nome, String email, String documento, LocalDate dataNascimento, LocalDate dataCadastro,Endereco endereco, boolean ativo) {
        if (!isMaiorDeIdade(dataNascimento)) {
            throw new IllegalArgumentException("Cadastro não permitido. Cliente deve ter 18 anos ou mais.");
        }

        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException("Documento de identificação é obrigatório.");
        }

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
        this.endereco = endereco;
        this.ativo = ativo;

        int idade = calcularIdade(dataNascimento);
        System.out.println("Cadastro permitido. Cliente tem " + idade + " anos.");
    }

    private boolean isMaiorDeIdade(LocalDate dataNascimento) {
        return calcularIdade(dataNascimento) >= 18;
    }

    private int calcularIdade(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now();
        if (dataNascimento == null || dataNascimento.isAfter(hoje)) {
            throw new IllegalArgumentException("Data de nascimento inválida.");
        }
        return Period.between(dataNascimento, hoje).getYears();
    }

    public void detalhesCliente() {
        System.out.println("----- Detalhes do Cliente -----");
        System.out.println("Documento: " + documento);
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Data de Nascimento: " + dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Ativo: " + ativo);
        System.out.println("-------------------------------");
    }

    // Getters

    public String getId() {
        return id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    // Setters

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void desativarCliente() {
        this.ativo = false;
    }

    public void atualizarDados(String novoNome, String novoEmail) {
        this.nome = novoNome;
        this.email = novoEmail;
    }
}

