package com.muralis.minhasfinancas.repository;

import com.muralis.minhasfinancas.model.entity.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.muralis.minhasfinancas.MinhasfinancasApplication.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {
    @Autowired
    UsuarioRepository repository;

    @Test
    public void deveVerificarAExistenciaDeUmEmail() {

        Usuario usuario = Usuario.builder().nome("usuario").email("mateus.gomes@muralis.com.br").build();
        repository.save(usuario);

        boolean result = repository.existsByEmail("mateus.gomes@muralis.com.br");

        Assertions.assertThat(result).isTrue();

    }

}
