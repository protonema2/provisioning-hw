package com.voxloud.provisioning.service;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voxloud.provisioning.component.ProvisioningConfig;
import com.voxloud.provisioning.dto.ApiResponse;
import com.voxloud.provisioning.dto.DeviceModel;
import com.voxloud.provisioning.dto.GlobalConstant;
import com.voxloud.provisioning.dto.ProvisionResponse;
import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.repository.DeviceRepository;

@Service
public class ProvisioningServiceImpl implements ProvisioningService {

	@Autowired
	DeviceRepository deviceRepo;
	
	 @Autowired
	 private ProvisioningConfig provisioningConfig;
	 
	 @Autowired 
	 private ObjectMapper objectMapper; // For JSON conversion
	 
    public <T> ResponseEntity<ApiResponse<ProvisionResponse>> getProvisioningFile(String macAddress) {
    	try {
    		Device device = deviceRepo.findByMacAddress(macAddress).orElseThrow();
    		System.out.println("Device Found: "+device.getMacAddress());
    		System.out.println("==============================================");
    		System.out.println("Overide Fragment:\n"+device.getOverrideFragment());
    		System.out.println("==============================================");
    		System.out.println("Fragment Device: "+device.getFragmentAsMap());
    		System.out.println("==============================================");
    		Map<String, Object> fragment = device.getFragmentAsMap();
    		if(fragment == null || fragment.isEmpty()) {
    			fragment.put("domain", provisioningConfig.getDomain());
                fragment.put("port", provisioningConfig.getPort());
    		}
    		
    		fragment.put("username", device.getUsername());
    		fragment.put("password", device.getPassword());
    		fragment.put("codecs", provisioningConfig.getCodecs());
    		
    		String formattedFragment = formatFragment(device.getModel(), fragment);
    		System.out.println("==============================================");
    		System.out.println("formatted fragment: \n"+formattedFragment);
    		System.out.println("==============================================");
    		
    		return new ResponseEntity<>(new ApiResponse<ProvisionResponse>(GlobalConstant.OK, null, null, new ProvisionResponse(device.getMacAddress(), "Provisioned", formattedFragment)), HttpStatus.OK);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("Device not found");
			return new ResponseEntity<>(new ApiResponse<ProvisionResponse>(GlobalConstant.ERROR_NOT_FOUND, GlobalConstant.ERR_CODE_404, "Mac Device not found",null), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unidentify Error: "+e);
			return new ResponseEntity<>(new ApiResponse<ProvisionResponse>(GlobalConstant.ERROR_INTERNAL_SERVER, GlobalConstant.ERR_CODE_500, "Internal system exception", null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    private String formatFragment(DeviceModel model, Map<String, Object> fragmentData) {
        try {
            if (model == DeviceModel.CONFERENCE) {
                return objectMapper.writeValueAsString(fragmentData);
            } else {
                // Convert to Properties format
                return fragmentData.entrySet().stream()
                        .map(entry -> entry.getKey() + "=" + entry.getValue())
                        .collect(Collectors.joining("\n"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error formatting fragment", e);
        }
    }
}

