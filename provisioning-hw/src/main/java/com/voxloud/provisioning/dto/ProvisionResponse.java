package com.voxloud.provisioning.dto;

public class ProvisionResponse {

	private String macAddress;
    private String status;
    private String fragment;
    
	public ProvisionResponse(String macAddress, String status, String fragment) {
		super();
		this.macAddress = macAddress;
		this.status = status;
		this.fragment = fragment;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFragment() {
		return fragment;
	}

	public void setFragment(String fragment) {
		this.fragment = fragment;
	}
    
	

}
