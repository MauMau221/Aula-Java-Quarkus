package org.senac;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Cliente extends PanacheEntity {
    public String nome;
    public String email;
    public int idade;

    public Cliente() {
    }

    public  Cliente(String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

}

