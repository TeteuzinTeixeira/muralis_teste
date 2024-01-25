package com.muralis.minhasfinancas.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.sql.SQLOutput;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name = "usuario", schema = "financas")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

	public static void main(String[] args) {
		usuario usuario = new usuario();
		usuario.setEmail("mateus.gomes@muralis.com.br");
		usuario.setNome("usuario");
		usuario.setSenha("senha");
	}
}