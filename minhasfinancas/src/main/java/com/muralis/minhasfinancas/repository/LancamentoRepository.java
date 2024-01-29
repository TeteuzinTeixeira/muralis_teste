package com.muralis.minhasfinancas.repository;

import com.muralis.minhasfinancas.model.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface LancamentoRepository  extends JpaRepository<Lancamento, Long> {
    @Query(value =
            "select sum(1.valor) from Lancamento 1 join l.usuario u "
                    + " where u.id = idUsuario and 1.tipo : tipo group by u")
            BigDecimal obterSaldoPorTipoLancamentoEUsuario(@Param("idUsuario") Long idUsuario, @Param("tipo") String tipo );
}
