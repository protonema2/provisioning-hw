package com.voxloud.provisioning.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voxloud.provisioning.dto.DeviceModel;

import lombok.Data;
import lombok.ToString;

/**
 * 
 */
@Entity
@Data
@ToString
public class Device {

    @Id
    @Column(name = "mac_address")
    private String macAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceModel model;

    @Column(name = "override_fragment")
    private String overrideFragment;

    private String username;

    private String password;

    
	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public DeviceModel getModel() {
		return model;
	}

	public void setModel(DeviceModel model) {
		this.model = model;
	}

	public String getOverrideFragment() {
		return overrideFragment;
	}

	public void setOverrideFragment(String overrideFragment) {
		this.overrideFragment = overrideFragment;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    public Map<String, Object> getFragmentAsMap() {
        if (overrideFragment == null || overrideFragment.isBlank()) {
            return new HashMap<>();
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(overrideFragment, Map.class);
        } catch (Exception e) {
            Map<String, Object> result = new HashMap<>();
            String[] lines = overrideFragment.split("\n");
            for (String line : lines) {
                String[] keyValue = line.split("=");
                if (keyValue.length == 2) {
                    result.put(keyValue[0].trim(), keyValue[1].trim());
                }
            }
            return result;
        }
    }
    
}