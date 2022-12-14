package com.springboot.bbs.service;

import com.springboot.bbs.domain.entity.Hospital;
import com.springboot.bbs.domain.dto.HospitalResponse;
import com.springboot.bbs.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public HospitalResponse getHospital(Long id) {
        Optional<Hospital> optHospital = hospitalRepository.findById(id);   // Entity
        Hospital hospital = optHospital.get();
        HospitalResponse hospitalResponse = Hospital.of(hospital);      // DTO

        if (hospital.getBusinessStatusCode() == 13) {
            hospitalResponse.setBusinessStatusName("영업중");
        } else if (hospital.getBusinessStatusCode() == 3) {
            hospitalResponse.setBusinessStatusName("폐업");
        } else {
            hospitalResponse.setBusinessStatusName(String.valueOf(hospital.getBusinessStatusCode()));
        }
        return hospitalResponse;
    }

    public Hospital findById(Long id) {
        Hospital hospital = hospitalRepository.findById(id).orElseThrow(()->new IllegalArgumentException("id가 없습니다."));
        return hospital;
    }
}
