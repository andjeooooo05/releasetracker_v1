package com.assignmentNeon.releaseTracker.releasetracker.restcontroller;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.assignmentNeon.releaseTracker.releasetracker.entity.Release;
import com.assignmentNeon.releaseTracker.releasetracker.service.ReleaseService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReleaseTrackerRestController.class)
public class ReleaseTrackerRestControllerTest {

	@MockBean
	ReleaseService releaseService;

	@Autowired
	MockMvc mockMvc;
	
	 @Autowired
	  private ObjectMapper objectMapper;

	@Test
	public void testfindAll() throws Exception {
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		Release release1 = new Release(1, "neon1", "neon1", 1, time, time, time);
		Release release2 = new Release(2, "neon2", "neon2", 1, time, time, time);
		List<Release> releases = Arrays.asList(release1,release2);

		Mockito.when(releaseService.findAll()).thenReturn(releases);

		mockMvc.perform(get("/api/releases")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(jsonPath("$[0].name", Matchers.is("neon1")))
				.andExpect(jsonPath("$[0].description", Matchers.is("neon1")))
				.andExpect(jsonPath("$[0].status", Matchers.is(1)))
				.andExpect(jsonPath("$[1].id", Matchers.is(2)))
				.andExpect(jsonPath("$[1].name", Matchers.is("neon2")))
				.andExpect(jsonPath("$[1].description", Matchers.is("neon2")))
				.andExpect(jsonPath("$[1].status", Matchers.is(1)));
	}

	@Test
	public void testGetReleaseById() throws Exception {
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		Release release = new Release(1, "neon2", "neon2", 1, time, time, time);

		Mockito.when(releaseService.findById(1)).thenReturn(release);

		mockMvc.perform(get("/api/releases/{releaseId}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("neon2"))
				.andExpect(jsonPath("$.description").value("neon2"))
				.andExpect(jsonPath("$.status").value("1"));
	}
	

	@Test
	public void testAddNewRelease() throws Exception {
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		Release release = new Release( "neon3", "neon3",1, time, time, time);

		mockMvc.perform(post("/api/releases").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(release)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(0))
				.andExpect(jsonPath("$.name").value("neon3"))
				.andExpect(jsonPath("$.description").value("neon3"))
				.andExpect(jsonPath("$.status").value("1"));
	}
	@Test
	public void testUpdateRelease() throws Exception {
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		int id_1 = 3;
		int id_2 = 4;
		Release release = new Release(id_1, "neon2", "neon2", 1, time, time, time);
		Release upTimestamprelease = new Release(id_2, "neon3", "neon3",1, time, time, time);
		when(releaseService.findById(id_1)).thenReturn(release);
		mockMvc.perform(put("/api/releases").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(upTimestamprelease)))
		.andExpect(status().isOk()).andExpect(jsonPath("$.id").value(4))
		.andExpect(jsonPath("$.name").value("neon3"))
		.andExpect(jsonPath("$.description").value("neon3"))
		.andExpect(jsonPath("$.status").value("1"));
	}
	@Test
	public void testDeleteReleaseById() throws Exception {
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		int id = 4;
		Release release = new Release(id, "neon4", "neon4", 1, time, time, time);
	    Mockito.when(releaseService.findById(4)).thenReturn(release);


		doNothing().when(releaseService).deleteById(id);
	    mockMvc.perform(delete("/api/releases/{releaseId}",id))
	    .andExpect(status().isOk());
	}

}
