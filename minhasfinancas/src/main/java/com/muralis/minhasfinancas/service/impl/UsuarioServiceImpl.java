package com.muralis.minhasfinancas.service.impl;

import com.muralis.minhasfinancas.exception.ErroAutenticacao;
import com.muralis.minhasfinancas.exception.RegraNegocioException;
import com.muralis.minhasfinancas.model.entity.Usuario;
import com.muralis.minhasfinancas.repository.UsuarioRepository;
import com.muralis.minhasfinancas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository repository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Usuario autenticar(String email, String senha) {

        Optional<Usuario> usuario = repository.findByEmail(email);

        if (!usuario.isPresent()) {
            throw new ErroAutenticacao("usuarionao Encontrado para o email informado.");
        }

        if (!usuario.get().getSenha().equals(senha)) {
            throw new ErroAutenticacao("Senha Invalida.");
        }

        return null;
    }

    @Override
    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {

        validarEmail(usuario.getEmail());

        return repository.save(usuario);
    }

    @Override
    public void validarEmail(String email) {
        boolean existe = repository.existsByEmail(email);
        if (existe) {
            throw new RegraNegocioException("Ja existe um usuario cadastro com este email.");
        }
    }

}
