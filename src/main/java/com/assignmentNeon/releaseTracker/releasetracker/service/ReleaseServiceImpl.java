package com.assignmentNeon.releaseTracker.releasetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignmentNeon.releaseTracker.releasetracker.dao.ReleaseDAO;
import com.assignmentNeon.releaseTracker.releasetracker.entity.Release;

@Service
public class ReleaseServiceImpl implements ReleaseService {
	
	private ReleaseDAO releaseDAO;
	
	@Autowired
	public ReleaseServiceImpl(ReleaseDAO theReleaseDAO) {
		
		releaseDAO = theReleaseDAO;
	}

	@Override
	@Transactional
	public List<Release> findAll() {
		
		return releaseDAO.findAll();
	}

	@Override
	@Transactional
	public Release findById(int theId) {
		
		return releaseDAO.findById(theId);
	}

	@Override
	@Transactional
	public void save(Release theRelease) {
		releaseDAO.save(theRelease);
		
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		releaseDAO.deleteById(theId);
		
	}

}
