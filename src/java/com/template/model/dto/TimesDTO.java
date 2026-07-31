package com.template.model.dto;

public class TimesDTO {
    private int id;
    private String nome;
    private int anoFundacao;
    private String estado;
    private int titulosBrasileiros;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getAnoFundacao() { return anoFundacao; }
    public void setAnoFundacao(int anoFundacao) { this.anoFundacao = anoFundacao; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getTitulosBrasileiros() { return titulosBrasileiros; }
    public void setTitulosBrasileiros(int titulosBrasileiros) { this.titulosBrasileiros = titulosBrasileiros; }
}