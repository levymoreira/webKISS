package com.webkiss.teste;

import org.hibernate.SessionFactory;

import com.webkiss.dao.ProdutoDAO;
import com.webkiss.pojo.Produto;
import com.webkiss.util.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		//Teste para ver se esta tudo ok com a conexão ao banco de dados
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		sf.getCurrentSession().beginTransaction();
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
		//insere
		Produto p = new Produto("PRODUTO QUALQUER");
		produtoDAO.saveOrUpdate(p);
		
		//lista
		for (Produto produto : produtoDAO.getAll()) {
			System.out.println(produto);
		}
		
		//deleta
		//produtoDAO.delete(p);
		
		sf.getCurrentSession().getTransaction().commit();
	}

}
