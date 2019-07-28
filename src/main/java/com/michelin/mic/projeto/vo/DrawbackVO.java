package com.michelin.mic.projeto.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "ATO_NCM")
@Table(name="ATO_NCM")
public class DrawbackVO{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cd_ato_ncm_seq") 
	@SequenceGenerator(name="cd_ato_ncm_seq", sequenceName="cd_ato_ncm_seq", allocationSize = 1,initialValue = 1) 
	@Column(name="cd_ato_ncm")
	private Integer cd_ato_ncm;
	
	@NotNull 
	@Column(name="cd_ato")
	private String ato;

	@NotNull
	@Column(name="cd_ncm")
	private Integer ncm;

	@Temporal(TemporalType.DATE)
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="dt_inicio_vig")
	private Date dataInicial;

	@Temporal(value = TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="dt_fim_vig")
	private Date dataFinal;
	
	@Temporal(value = TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	@Column(name="dt_modific")
	private Date dataModificacao;
	
	@NotNull
	@Column(name="user_modific")
	private String usuario;
	
	public DrawbackVO() {
	}
	
	public DrawbackVO(String ato, Integer ncm, Date dataInicial, Date  dataFinal, Date dataModificacao) {
		this.ato = ato;
		this.ncm = ncm;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.dataModificacao = dataModificacao;
	}

	public String getAto() {
		return ato;
	}

	public void setAto(String ato) {
		this.ato = ato;
	}

	public Integer getNcm() {
		return ncm;
	}

	public void setNcm(Integer ncm) {
		this.ncm = ncm;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	
	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Date getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getCd_ato_ncm() {
		return cd_ato_ncm;
	}

	public void setCd_ato_ncm(Integer cd_ato_ncm) {
		this.cd_ato_ncm = cd_ato_ncm;
	}
	
}
