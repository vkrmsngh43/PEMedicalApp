package com.medicalvault.controller.v1;

import com.medicalvault.constants.ErrorConstants;
import com.medicalvault.helper.SecurityContextHelper;
import com.medicalvault.model.PrescriptionRecordsResponse;
import com.medicalvault.service.AccessRequestService;
import com.medicalvault.service.PrescriptionRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prescriptionrecords")
public class PrescriptionRecordsController {

  @Autowired PrescriptionRecordsService prescriptionRecordsService;

  @Autowired AccessRequestService accessRequestService;

  @Autowired SecurityContextHelper securityContextHelper;

  @GetMapping(value = "/find/user/{userId}/{accesscode}")
  public ResponseEntity<PrescriptionRecordsResponse> getPrescriptionRecords(
      @PathVariable("userId") String userId, @PathVariable("accesscode") String accessCode) {
    PrescriptionRecordsResponse prescriptionRecordsResponse = new PrescriptionRecordsResponse();

    boolean isVerified =
        accessRequestService.verifyAccessCode(
            userId, securityContextHelper.getCurrentUserId(), accessCode);
    if (isVerified) {
      prescriptionRecordsResponse = prescriptionRecordsService.getPrescriptionRecordsOfUser(userId);
    } else {
      prescriptionRecordsResponse.setStatusCode(ErrorConstants.FAILED_STATUS_CODE);
      prescriptionRecordsResponse.setMessage(ErrorConstants.ACCESS_CODE_INVALID);
    }

    return ResponseEntity.ok(prescriptionRecordsResponse);
  }
}
