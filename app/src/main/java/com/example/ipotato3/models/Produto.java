package com.example.ipotato3.models;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class Produto implements Serializable {

    //Atributos
    private long id;
    private int imagem;
    private String nome, descricao;
    private double preco, desconto;
    public int quantidadeProduto = 0;

    //Construtor
    public Produto(long id, String nome, String descricao, double preco, double desconto, int quantidadeProduto) {
        this.id = id;
//        this.imagem = imagem;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.desconto = desconto;
        this.quantidadeProduto = quantidadeProduto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    //Método personalizado
    public static ArrayList<Produto> getProdutos(){
        ArrayList<Produto> batatas = new ArrayList<Produto>();

//        batatas.add(new Produto(1, "Batata 1", R.drawable.iconbatatafrita, "Batata 1", 9.90, 1.0,1));
//        batatas.add(new Produto(2, "Batata 2", R.drawable.iconbatatafrita, "Batata 2",9.90, 1.0,1));
//        batatas.add(new Produto(3, "Batata 3", R.drawable.iconbatatafrita, "Batata 3",9.90, 1.0,1));
//        batatas.add(new Produto(4, "Batata 4", R.drawable.iconbatatafrita, "Batata 4",9.90, 1.0,1));

        return batatas;
    }
}

