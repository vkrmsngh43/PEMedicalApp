package com.pe.medical.model;

import java.util.List;

public class MedicalRecordsResponse {
	private List<MedicalRecords> medicalRecords;
	
	public List<MedicalRecords> getMedicalRecords() {
		return medicalRecords;
	}
	public void setMedicalRecords(List<MedicalRecords> medicalRecords) {
		this.medicalRecords = medicalRecords;
	}
	
}
