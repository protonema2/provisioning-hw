package com.voxloud.provisioning.service;

import org.springframework.http.ResponseEntity;

import com.voxloud.provisioning.dto.ApiResponse;
import com.voxloud.provisioning.dto.ProvisionResponse;

public interface ProvisioningService {

	<T> ResponseEntity<ApiResponse<ProvisionResponse>> getProvisioningFile(String macAddress);
}
