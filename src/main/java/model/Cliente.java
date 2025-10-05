package model;

import java.time.LocalDate;
import java.util.Date;

public class Cliente {

    private String id;
    private String nome;
    private String email;
    private String documento;
    private Date dataNascimento;
    private LocalDate dataCadastro;
    private boolean ativo;

    public Cliente(String id, String nome, String email, String documento, Date dataNascimento, LocalDate dataCadastro, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
    }

    public String getId() {
        return id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Date getDataNascimento() {
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    //Desativar cliente
   public void desativarCliente(){}
    this.ativo = false;

    // Atualizar dados (versão simples)

    public void atualizarDados(String novoNome, String novoEmail){
        this.nome = novoNome;
        this.email = novoEmail;
    }

}

