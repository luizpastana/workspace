package br.com.j.hemopa.digital.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import br.com.j.hemopa.digital.model.Agenda;
import br.com.j.hemopa.digital.model.Pessoa;
import br.com.j.hemopa.digital.util.JPAUtil;

public class PessoaDAO {
	
	public void adiciona(Pessoa pessoa) {

		// consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();

		// abre transacao
		em.getTransaction().begin();

		// persiste o objeto
		em.persist(pessoa);

		// commita a transacao
		em.getTransaction().commit();

		// fecha a entity manager
		em.close();
	}

	public boolean existe(Pessoa pessoa) {

		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Pessoa> query = em
				.createQuery(" select u from Pessoa u " + " where u.cpf = :pCpf ", Pessoa.class);
		query.setParameter("pCpf", pessoa.getCpf());
		
		try {
			
			Pessoa resultado = query.getSingleResult();
			
		}catch (NoResultException ex) {
			
			return false;
			
		}
		

		em.close();

		return true;
	}
	
	public Pessoa buscarPessoaPorCpf(String cpf) {

		EntityManager em = new JPAUtil().getEntityManager();

		try {

			final Map<String, Object> parametros = new HashMap<>();

			final StringBuilder JPQL = new StringBuilder("SELECT p FROM Pessoa p ");
			JPQL.append("WHERE 1 = 1 ");
			JPQL.append(" AND p.cpf = :pCpf ");
			parametros.put("pCpf", cpf);

			TypedQuery<Pessoa> query = em.createQuery(JPQL.toString(), Pessoa.class);

			for (final String parametro : parametros.keySet()) {
				query.setParameter(parametro, parametros.get(parametro));
			}

			return (Pessoa) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Pessoa> listaTodos() {
		
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<Pessoa> query = em.getCriteriaBuilder().createQuery(Pessoa.class);
		query.select(query.from(Pessoa.class));

		List<Pessoa> lista = em.createQuery(query).getResultList();

		em.close();
		return lista;
		
	}
	
	public void atualiza(Pessoa pessoa) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.merge(pessoa);

		em.getTransaction().commit();
		em.close();
	}

	

}
