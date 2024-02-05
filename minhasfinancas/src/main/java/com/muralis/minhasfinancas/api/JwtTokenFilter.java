package com.muralis.minhasfinancas.api;

import com.muralis.minhasfinancas.service.JwtService;
import com.muralis.minhasfinancas.service.SecurityUserDetailService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final SecurityUserDetailService userDetailService;

  public JwtTokenFilter(JwtService jwtService, SecurityUserDetailService userDetailService) {
    this.jwtService = jwtService;
    this.userDetailService = userDetailService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse,
                                  FilterChain filterChain) throws ServletException, IOException {
    String authorization = httpServletRequest.getHeader("Authorization");
    if (authorization != null && authorization.startsWith("Bearer")) {
      String token = authorization.split(" ")[1];
      if (jwtService.isTokenValido(token)) {
        String login = jwtService.obterLoginUsuario(token);
        UserDetails usuarioAutenticado = userDetailService.loadUserByUsername(login);

        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(
            usuarioAutenticado,
            null,
            usuarioAutenticado.getAuthorities());

        user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        SecurityContextHolder.getContext().setAuthentication(user);
      }
    }
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
