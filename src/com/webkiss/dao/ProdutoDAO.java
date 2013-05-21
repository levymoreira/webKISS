package com.webkiss.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

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

}
