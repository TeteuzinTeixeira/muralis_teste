package com.muralis.minhasfinancas.repository;

import com.muralis.minhasfinancas.model.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository  extends JpaRepository<Lancamento, Long> {
}
