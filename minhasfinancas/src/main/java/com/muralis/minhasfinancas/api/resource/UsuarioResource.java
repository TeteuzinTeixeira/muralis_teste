package com.muralis.minhasfinancas.api.resource;

import com.muralis.minhasfinancas.api.dto.TokenDTO;
import com.muralis.minhasfinancas.api.dto.UsuarioDTO;
import com.muralis.minhasfinancas.exception.ErroAutenticacao;
import com.muralis.minhasfinancas.exception.RegraNegocioException;
import com.muralis.minhasfinancas.model.entity.Usuario;
import com.muralis.minhasfinancas.service.JwtService;
import com.muralis.minhasfinancas.service.LancamentoService;
import com.muralis.minhasfinancas.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UsuarioResource {

    private final UsuarioService service;
    private final LancamentoService lancamentoService;
    private final JwtService jwtService;


    @PostMapping("/autenticar")
    public ResponseEntity<?> autenticar (@RequestBody UsuarioDTO dto) {
        try {
            Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
            String token = jwtService.gerarToken(usuarioAutenticado);
            TokenDTO tokenDTO = new TokenDTO(usuarioAutenticado.getNome(), token);
            return ResponseEntity.ok(tokenDTO);
        }catch (ErroAutenticacao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{id}/saldo")
    public ResponseEntity obterSaldo( @PathVariable("id") Long id ) {
        Optional<Usuario> usuario = service.obterPorId(id);

        if(!usuario.isPresent()) {
            return new ResponseEntity( HttpStatus.NOT_FOUND );
        }

        BigDecimal saldo = lancamentoService.obterSaldoPorUsuario(id);
        return ResponseEntity.ok(saldo);
    }

    @PostMapping
    public ResponseEntity salvar( @RequestBody UsuarioDTO dto ) {

        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha()).build();

        try {
            Usuario usuarioSalvo = service.salvarUsuario(usuario);
            return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
        }catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
