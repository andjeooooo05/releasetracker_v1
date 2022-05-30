package com.assignmentNeon.releaseTracker.releasetracker.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignmentNeon.releaseTracker.releasetracker.entity.Release;
import com.assignmentNeon.releaseTracker.releasetracker.exception.ResourceNotFoundException;
import com.assignmentNeon.releaseTracker.releasetracker.service.ReleaseService;

@RestController
@RequestMapping("/api")
public class ReleaseTrackerRestController {

	private ReleaseService releaseService;

	@Autowired
	public ReleaseTrackerRestController(ReleaseService theReleaseService) {
		releaseService = theReleaseService;

	}
	// expose "/releases" and return list of releases -GET

	@GetMapping("/releases")
	public List<Release> findAll() {

		return releaseService.findAll();

	}

	// expose "/releases/{releaseId}" and return release -GET

	@GetMapping("/releases/{releaseId}")
	public Release getRelease(@PathVariable int releaseId) throws  ResourceNotFoundException {

		Release theRelease = releaseService.findById(releaseId);

		if (theRelease == null) {
			
			throw new ResourceNotFoundException ("Release not found for this id :: " + releaseId);

		}
		return theRelease;
	}

	// expose "/releases" and return release -POST Add new release
	@PostMapping("/releases")
	public Release addRelease(@RequestBody Release theRelease) {

		// in case value is pass as JSON set id to 0
		theRelease.setId(0);
		releaseService.save(theRelease);
		return theRelease;

	}

	// add mapping for PUT/ release - update existing release
	@PutMapping("/releases")
	public Release updateRelease(@RequestBody Release theRelease) {

		releaseService.save(theRelease);
		return theRelease;

	}

	// add mapping for DELETE/ release - delete release
	@DeleteMapping("/releases/{releaseId}")
	public ResponseEntity<HttpStatus> deleteRelease(@PathVariable int releaseId) throws ResourceNotFoundException {

		Release tempRelease = releaseService.findById(releaseId);

		// throw exception if null

		if (tempRelease == null) {

			throw new ResourceNotFoundException ("Release not found for this id :: " + releaseId);
			

		}
		releaseService.deleteById(releaseId);
		return new ResponseEntity<>(HttpStatus.OK);

	}

}
