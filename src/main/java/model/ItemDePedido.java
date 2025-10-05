package model;

import model.Produto;

public  record ItemDePedido(Produto produto, int quantidade) {
        @Override
        public String toString () {
            return "- " + produto().getNome() + " (Quantidade: " + quantidade() + ")";
        }
    }
