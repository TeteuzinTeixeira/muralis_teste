package repository;

import java.util.Optional;

import com.muralis.minhasfinancas.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.annotation.Rollback;

import com.muralis.minhasfinancas.MinhasfinancasApplication;
import com.muralis.minhasfinancas.model.entity.Usuario;

@SpringBootTest(classes = MinhasfinancasApplication.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureTestEntityManager
@Transactional
@Rollback(false)
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void deveVerificarAExistenciaDeUmEmail() {
        //cenário
        Usuario usuario = criarUsuario();
        entityManager.persist(usuario);

        //ação/ execução
        boolean result = repository.existsByEmail("usuario@email.com");

        //verificacao
        Assertions.assertThat(result).isTrue();

    }

    @Test
    public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
        //cenário

        //acao
        boolean result = repository.existsByEmail("usuario@email.com");

        //verificacao
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void devePersistirUmUsuarioNaBaseDeDados() {
        //cenário
        Usuario usuario =criarUsuario();

        //acao
        Usuario usuarioSalvo = repository.save(usuario);

        // verificacao
        Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
    }

    @Test
    public void deveBuscarUmUsuarioPorEmail() {
        //cenario
        Usuario usuario = criarUsuario();
        entityManager.persist(usuario);

        //verificacao
        Optional<Usuario> result = repository.findByEmail("email@email.com");

        Assertions.assertThat( result.isPresent() ).isFalse();

    }

    @Test
    public void deveRetornarVazioAoBuscarUsuarioPorEmailQuandoNaoExisteNaBase() {

        //verificacao
        Optional<Usuario> result = repository.findByEmail("usuario@email.com");

        Assertions.assertThat( result.isPresent() ).isFalse();

    }

    public static Usuario criarUsuario() {
        return Usuario
                .builder()
                .nome("usuario")
                .email("usuario@email.com")
                .senha("senha")
                .build();
    }

}
