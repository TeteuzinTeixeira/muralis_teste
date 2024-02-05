package com.muralis.minhasfinancas.service.impl;

import com.muralis.minhasfinancas.exception.ErroAutenticacao;
import com.muralis.minhasfinancas.exception.RegraNegocioException;
import com.muralis.minhasfinancas.model.entity.Usuario;
import com.muralis.minhasfinancas.repository.UsuarioRepository;
import com.muralis.minhasfinancas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository repository;
    private PasswordEncoder encoder;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository repository, PasswordEncoder encoder) {
        super();
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public Usuario autenticar(String email, String senha) {

        Optional<Usuario> usuario = repository.findByEmail(email);

        if (!usuario.isPresent()) {
            throw new ErroAutenticacao("Usuario nao Encontrado para o email informado.");
        }

        boolean senhaCorreta = encoder.matches(senha, usuario.get().getSenha());

        if (!senhaCorreta) {
            throw new ErroAutenticacao("Senha Invalida.");
        }

        return usuario.get();
    }

    @Override
    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {

        validarEmail(usuario.getEmail());
        criptografarSenha(usuario);
        return repository.save(usuario);
    }

    @Override
    public void validarEmail(String email) {
        boolean existe = repository.existsByEmail(email);
        if (existe) {
            throw new RegraNegocioException("Ja existe um usuario cadastro com este email.");
        }
    }

    private void criptografarSenha(Usuario usuario) {
        String senha = usuario.getSenha();
        String senhaCriptografada = encoder.encode(senha);
        usuario.setSenha(senhaCriptografada);
    }

    @Override
    public Optional<Usuario> obterPorId(Long id) {
        return repository.findById(id);
    }

}
