package com.example;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomerSearchDao {

	@Autowired
	private SessionFactory _sessionFactory;
	
	/*@PersistenceContext
	public EntityManager em;*/
	
	private Session getSession(){
		return this._sessionFactory.getCurrentSession();
	}

	public List<Users> search(String key) {

		//FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		FullTextSession fullTextSession = Search.getFullTextSession(getSession());
		QueryBuilder queryBuilder = fullTextSession.getSearchFactory()
											.buildQueryBuilder()
											.forEntity(Users.class)
											.get();
		
		
		Query query = queryBuilder.keyword()
							.onFields("id","name")
							.matching(key)
							.createQuery();
		
		org.hibernate.search.FullTextQuery jpaQuery = fullTextSession.createFullTextQuery(query, Users.class);
		
		@SuppressWarnings("unchecked")
		List<Users> resultList = jpaQuery.list();//.getResultList();

		return resultList;
	}
	
	public void saveUser(Users user){
		getSession().save(user);
	}

}
