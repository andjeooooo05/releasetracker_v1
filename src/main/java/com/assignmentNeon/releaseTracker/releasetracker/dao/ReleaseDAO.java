package com.assignmentNeon.releaseTracker.releasetracker.dao;

import java.util.List;

import com.assignmentNeon.releaseTracker.releasetracker.entity.Release;

public interface ReleaseDAO {

	public List<Release> findAll();

	public Release findById(int theId);

	public void save(Release theRelease);

	public void deleteById(int theId);
}
