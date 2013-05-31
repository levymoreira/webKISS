package com.webkiss.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.webkiss.pojo.Produto;
import static com.webkiss.util.HibernateUtil.getSession;

public class ProdutoDAO {

	public ProdutoDAO(){}
	
	public void saveOrUpdate(Produto produto) {
		if(produto.getDataDoCadastro() == null){
			produto.setDataDoCadastro(new Date());
		} 
		getSession().saveOrUpdate(produto);
	}
		
	public void delete(Produto produto){
		getSession().delete(produto);	
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> getAll(){
		Criteria criteria = getSession().createCriteria(Produto.class);
	 	return  (List<Produto>) criteria.addOrder(Order.asc("id")).list();
	}

	//Mais sobre criteria em: http://docs.jboss.org/hibernate/orm/3.3/reference/en/html/querycriteria.html
	@SuppressWarnings("unchecked")
	public List<Produto> pesquisar(String campo, Object valor){
		Criteria criteria = getSession().createCriteria(Produto.class);
		if(campo.equals("id")){ //pesquisa por id
			int i = Integer.valueOf(valor.toString().trim()); //remove os espaços antes de converter
			criteria.add(Restrictions.eq(campo, i));
		}else{ //pesquisa por nome
			criteria.add(Restrictions.like(campo, "%" + valor + "%"));
		}			
	 	return  (List<Produto>) criteria.addOrder(Order.asc("id")).list();
	}
}
