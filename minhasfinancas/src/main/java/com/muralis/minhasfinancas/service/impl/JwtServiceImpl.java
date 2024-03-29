package com.muralis.minhasfinancas.service.impl;

import com.muralis.minhasfinancas.model.entity.Usuario;
import com.muralis.minhasfinancas.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class JwtServiceImpl implements JwtService {

  @Value("${jwt.expiracao}")
  private String expiracao;

  @Value("${jwt.chave-assinatura}")
  private String chaveAssinatura;

  @Override
  public String gerarToken(Usuario usuario) {
    long exp = Long.parseLong(expiracao);
    LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(exp);
    Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
    java.util.Date data = Date.from(instant);

    String dataExpiracaoToken = dataHoraExpiracao.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
    return Jwts
            .builder()
            .setExpiration(data)
            .setSubject(usuario.getEmail())
            .claim("nome", usuario.getNome())
            .claim("horaExpiracao", dataExpiracaoToken)
            .claim("userid", usuario.getId())
            .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
            .compact();
  }

  @Override
  public Claims obterClaims(String token) throws ExpiredJwtException {
    return Jwts
        .parser()
        .setSigningKey(chaveAssinatura)
        .parseClaimsJws(token)
        .getBody();
  }

  @Override
  public boolean isTokenValido(String token) {
    try {
      Claims claims = obterClaims(token);
      java.util.Date dataEx = claims.getExpiration();
      LocalDateTime dataExpiracao = dataEx.toInstant()
          .atZone(ZoneId.systemDefault()).toLocalDateTime();

      return !LocalDateTime.now().isAfter(dataExpiracao);
    } catch (ExpiredJwtException e) {
      return false;
    }
  }

  @Override
  public String obterLoginUsuario(String token) {
    Claims claims = obterClaims(token);
    return claims.getSubject();
  }
}
