package com.webkiss.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.webkiss.dao.ProdutoDAO;
import com.webkiss.pojo.Produto;

@ManagedBean
@RequestScoped
public class ProdutoBean {

	// Propriedades
	
	private Produto produtoSelecionado = new Produto();
	
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	
	private List<Produto> lista = null;
	
	private String campoFiltro = "DESCRICAO";
	
	private String valorFiltro = "";

	// Constantes

	private final String PAGINA_LISTAGEM = "listagem?faces-redirect=true";
	private final String PAGINA_EDICAO = "edicao"; 											

	// Contrutores

	public ProdutoBean() {
	}

	// Métodos
	
	public String salvar() {	
		if(produtoSelecionado.getId() == 0){
			produtoSelecionado.setId(null);
		}
		produtoDAO.saveOrUpdate(produtoSelecionado);
		return PAGINA_LISTAGEM;
	}

	public String inserir() {	
		produtoSelecionado = new Produto();
		return PAGINA_EDICAO;
	}

	public String editar() {		
		return PAGINA_EDICAO;
	}
	
	public String cancelar() {
		return PAGINA_LISTAGEM;
	}	
	
	public String deletar(){
		produtoDAO.delete(produtoSelecionado);
		return PAGINA_LISTAGEM;
	}
	
	public String filtrar(){
		if(valorFiltro.trim().equalsIgnoreCase("")){ //pesquisa em branco, retorna todos registros
			lista = produtoDAO.getAll();
		}else{ 
			lista = produtoDAO.pesquisar(campoFiltro, valorFiltro);			
		}
		return PAGINA_LISTAGEM;
	}

	// Getters e Setters

	public List<Produto> getLista() {
		if (lista == null) {
			lista = produtoDAO.getAll();
		}
		return lista;
	}
	
	public void setLista(List<Produto> lista) {
		this.lista = lista;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public String getCampoFiltro() {
		return campoFiltro;
	}

	public void setCampoFiltro(String campoFiltro) {
		this.campoFiltro = campoFiltro;
	}

	public String getValorFiltro() {
		return valorFiltro;
	}

	public void setValorFiltro(String valorFiltro) {
		this.valorFiltro = valorFiltro;
	}
	
}
