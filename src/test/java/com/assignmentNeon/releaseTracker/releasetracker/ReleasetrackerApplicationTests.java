package com.assignmentNeon.releaseTracker.releasetracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.assignmentNeon.releaseTracker.releasetracker.entity.Release;
import com.assignmentNeon.releaseTracker.releasetracker.restcontroller.ReleaseTrackerRestController;
import com.assignmentNeon.releaseTracker.releasetracker.service.ReleaseService;

@SpringBootTest(classes = ReleasetrackerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({ "/02-release-tracker.sql", "/03-insertdata.sql" })
class ReleasetrackerApplicationTests {

	@Autowired
	ReleaseTrackerRestController releaseTrackerController;
	@Autowired
	ReleaseService releaseService;

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testLoadDataForTestClass() {
		assertEquals(2, releaseService.findAll().size());
	}

	@Test
	public void testGetAllReleases() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/releases", HttpMethod.GET, entity,
				String.class);
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetEmployeeById() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/releases/2", HttpMethod.GET,
				entity, String.class);
		assertNotNull(response.getBody());
		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	public void testCreateRelease() {

		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		Release release = new Release("neon_test3", "Opis za test 3", 1, time, time, time);
		ResponseEntity<Release> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/releases", release,
				Release.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
		// System.out.println(postResponse.getBody());
		assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testUpdateRelease() {
		int id = 2;
		Release release = restTemplate.getForObject(getRootUrl() + "/api/releases/" + id, Release.class);
		String descriptionBeforeUpdate = release.getDescription();
		release.setName("update_neon_test3");
		release.setDescription("update_Opis za test 3");
		restTemplate.put(getRootUrl() + "/api/releases", release);
		Release updateRelease = restTemplate.getForObject(getRootUrl() + "/api/releases/" + id, Release.class);

		assertNotEquals(descriptionBeforeUpdate, updateRelease.getDescription());
	}

	@Test
	public void testDeleteRelease() {
		int id = 1;

		Release release = restTemplate.getForObject(getRootUrl() + "/api/releases/" + id, Release.class);
		assertNotNull(release);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/releases/" + id, HttpMethod.DELETE,
				HttpEntity.EMPTY, String.class);
		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}
}
