package com.muralis.minhasfinancas.model.entity;

import com.muralis.minhasfinancas.model.enums.StatusLancamento;
import com.muralis.minhasfinancas.model.enums.TipoLancamento;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "lancamento", schema = "financas")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Override
    public String toString() {
        return "Lancamento{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", mes=" + mes +
                ", ano=" + ano +
                ", usuario=" + usuario +
                ", valor=" + valor +
                ", dataCadastro=" + dataCadastro +
                ", tipo=" + tipo +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lancamento that = (Lancamento) o;
        return Objects.equals(id, that.id) && Objects.equals(descricao, that.descricao) && Objects.equals(mes, that.mes) && Objects.equals(ano, that.ano) && Objects.equals(usuario, that.usuario) && Objects.equals(valor, that.valor) && Objects.equals(dataCadastro, that.dataCadastro) && tipo == that.tipo && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, mes, ano, usuario, valor, dataCadastro, tipo, status);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public void setUsuario(com.muralis.minhasfinancas.model.entity.usuario usuario) {
        this.usuario = usuario;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
    }

    public void setStatus(StatusLancamento status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getMes() {
        return mes;
    }

    public Integer getAno() {
        return ano;
    }

    public com.muralis.minhasfinancas.model.entity.usuario getUsuario() {
        return usuario;
    }

    public Integer getValor() {
        return valor;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public TipoLancamento getTipo() {
        return tipo;
    }

    public StatusLancamento getStatus() {
        return status;
    }

    @Column(name = "mes")
    private Integer mes;

    @Column(name = "ano")
    private Integer ano;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private usuario usuario;

    @Column(name = "valor")
    private Integer valor;

    @Column(name = "data_cadastro")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dataCadastro;

    @Column(name = "tipo")
    @Enumerated(value = EnumType.STRING )
    private TipoLancamento tipo;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private StatusLancamento status;
}