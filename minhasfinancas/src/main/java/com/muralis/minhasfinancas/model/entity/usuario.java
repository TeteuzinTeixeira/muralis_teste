package com.muralis.minhasfinancas.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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
public class Usuario {


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
		Usuario usuario = new Usuario();
		usuario.setEmail("mateus.gomes@muralis.com.br");
		usuario.setNome("usuario");
		usuario.setSenha("senha");
	}
}