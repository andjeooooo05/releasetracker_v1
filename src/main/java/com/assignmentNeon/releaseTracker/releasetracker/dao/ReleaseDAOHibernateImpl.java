package com.assignmentNeon.releaseTracker.releasetracker.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assignmentNeon.releaseTracker.releasetracker.entity.Release;
import org.hibernate.Session;
import org.hibernate.query.Query;


@Repository
public class ReleaseDAOHibernateImpl implements ReleaseDAO {
	
	//define field for entitymanager
	
	private EntityManager entityManager;
	
	//set up constractor injection
	@Autowired
	public ReleaseDAOHibernateImpl(EntityManager theEntityManager) {
		
		entityManager = theEntityManager;
		
	}

	@Override
	public List<Release> findAll() {
		
		//get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//create a query
		Query<Release> theQuery = currentSession.createQuery("from Release", Release.class);
		
		
		//execute query and get result list
		
		List <Release> release = theQuery.getResultList();
		
		//return the result
		return release;
	}

	@Override
	public Release findById(int theId) {
		
		//get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		
		//get the release
		Release theRelease = currentSession.get(Release.class, theId);
		
		//return the release
		return theRelease;
	}

	@Override
	public void save(Release theRelease) {
		//get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//save the release
		currentSession.saveOrUpdate(theRelease);
	
	}

	@Override
	public void deleteById(int theId) {
		//get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//delete object with primary key
		Query<Release> theQuery = currentSession.createQuery("delete from Release where id=:releaseId");
		
		theQuery.setParameter("releaseId", theId);
		theQuery.executeUpdate();
		
	}

}
