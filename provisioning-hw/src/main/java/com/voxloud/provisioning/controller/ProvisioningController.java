package com.voxloud.provisioning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voxloud.provisioning.dto.ApiResponse;
import com.voxloud.provisioning.dto.ProvisionResponse;
import com.voxloud.provisioning.service.ProvisioningService;

@RestController
@RequestMapping("/api/v1")
public class ProvisioningController {
	
	@Autowired
	ProvisioningService provisionService;
	
	@GetMapping("/provisioning/{macAddress}")
    public <T> ResponseEntity<ApiResponse<ProvisionResponse>> getMacAddress(@PathVariable String macAddress) {
		return provisionService.getProvisioningFile(macAddress);
	}
}