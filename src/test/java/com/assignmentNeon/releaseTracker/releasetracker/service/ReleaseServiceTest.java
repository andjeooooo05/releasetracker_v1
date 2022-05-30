package com.assignmentNeon.releaseTracker.releasetracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.assignmentNeon.releaseTracker.releasetracker.dao.ReleaseDAO;
import com.assignmentNeon.releaseTracker.releasetracker.entity.Release;

@ExtendWith(SpringExtension.class)
public class ReleaseServiceTest {

	@Mock
	ReleaseDAO releaseDAO;
	@InjectMocks
	ReleaseService releaseService = new ReleaseServiceImpl(releaseDAO);

	@Test

	public void testFindAllReleases() {
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		List<Release> list = new ArrayList<Release>();
		Release releaseOne = new Release(1, "neon1", "neon1", 1, time, time, time);
		Release releaseTwo = new Release(2, "neon2", "neon2", 1, time, time, time);
		Release releaseThree = new Release(3, "neon3", "neon3", 1, time, time, time);

		list.add(releaseOne);
		list.add(releaseTwo);
		list.add(releaseThree);

		when(releaseDAO.findAll()).thenReturn(list);

		// test
		List<Release> releaseList = releaseService.findAll();

		assertEquals(3, releaseList.size());
		verify(releaseDAO, times(1)).findAll();
	}

	@Test
	public void testReleaseById() {
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		Release release = new Release(1, "neon1", "neon1", 1, time, time, time);
		when(releaseDAO.findById(1)).thenReturn(release);

		Release release_1 = releaseService.findById(1);

		assertEquals(1, release_1.getId());
		assertEquals("neon1", release_1.getName());
		assertEquals("neon1", release_1.getDescription());
	}

	@Test
	public void createReleaseTest() {
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		Release release = new Release(1, "neon1", "neon1", 1, time, time, time);

		releaseService.save(release);

		verify(releaseDAO, times(1)).save(release);
	}

	@Test
	public void updateReleaseTest() {
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		Release release = new Release(1, "neon1", "neon1", 1, time, time, time);
		when(releaseDAO.findById(1)).thenReturn(release);
		
		Release release_1 = releaseService.findById(1);
		release_1.setDescription("neon2");
		releaseService.save(release_1);

		verify(releaseDAO, times(1)).save(release_1);
		Release release_2 = releaseService.findById(1);
		assertEquals(1, release_2.getId());
		assertEquals("neon2", release_1.getDescription());
	}
	@Test
	public void deleteReleaseTest() {
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		List<Release> list = new ArrayList<Release>();
		Release releaseOne = new Release(1, "neon1", "neon1", 1, time, time, time);
		Release releaseTwo = new Release(2, "neon2", "neon2", 1, time, time, time);

		list.add(releaseOne);
		list.add(releaseTwo);
		when(releaseDAO.findAll()).thenReturn(list);
		
		releaseService.deleteById(1);
		
		verify(releaseDAO, times(1)).deleteById(1);
		
		
	}

}
