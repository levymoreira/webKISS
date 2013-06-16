package com.webkiss.pojo;

import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "produtos")
public class Produto implements Serializable{

	//Propriedades
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 50)
	private String nome;
	
	@Column(length = 300)
	private String foto;
	
	@Column(precision=25, scale=10)
	private BigDecimal preco;
	
	@Column(length=1) //novo, bom, mais ou menos, quebrado
	private Byte situacao;
	
	private Boolean inativo;
	
	@Lob
	private String observacoes;			
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_do_cadastro", nullable = false, updatable = false)
	private Date dataDoCadastro;
	
	//Construtores
	
	public Produto(){}
	
	public Produto(String nome, String foto){
		this.nome = nome;
		this.foto = foto;
	}

	//Getters e Setters	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Byte getSituacao() {
		return situacao;
	}

	public void setSituacao(Byte situacao) {
		this.situacao = situacao;
	}

	public Boolean getInativo() {
		return inativo;
	}

	public void setInativo(Boolean inativo) {
		this.inativo = inativo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Date getDataDoCadastro() {
		return dataDoCadastro;
	}

	public void setDataDoCadastro(Date dataDoCadastro) {
		this.dataDoCadastro = dataDoCadastro;
	}
	
	
	
	//Métodos sobrescritos
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {	
		return "Id: " + this.getId() + " Nome: " + this.getNome();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}
