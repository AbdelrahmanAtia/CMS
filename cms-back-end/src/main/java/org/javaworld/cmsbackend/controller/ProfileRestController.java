package org.javaworld.cmsbackend.controller;

import org.javaworld.cmsbackend.model.Profile;
import org.javaworld.cmsbackend.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProfileRestController {

	@Autowired
	private ProfileService profileService;

	@GetMapping("/profile")
	public Profile getCurrentUserProfile() {
		return profileService.getCurrentUserProfile();
	}

	@PutMapping("/profile")
	public Profile updateCurrentUserProfile(@Validated @RequestBody Profile profile) {
		return profileService.updateCurrentUserProfile(profile);
	}
	
	@GetMapping("/profile/isUniqueEmail/{email}")
	public boolean isUniqueEmail(@PathVariable String email) {
		return profileService.isUniqueEmail(email);
	}

}
