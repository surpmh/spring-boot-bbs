package com.springboot.bbs.domain.entity;

import com.springboot.bbs.domain.dto.HospitalResponse;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "nation_wide_hospitals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hospital {
    @Id
    private Long Id;

    @Column(name = "road_name_address")
    private String roadNameAddress;

    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "patient_room_count")
    private Integer patientRoomCount;

    @Column(name = "total_number_of_beds")
    private Integer totalNumberOfBeds;

    @Column(name = "business_type_name")
    private String businessTypeName;

    @Column(name = "business_status_code")
    private Integer businessStatusCode;

    @Column(name = "business_status_name")
    private String businessStatusName;

    @Column(name = "total_area_size")
    private Float totalAreaSize;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    private List<Review> reviews;

    // HospitalEntity를 HospitalResponse DTO로 만들기
    public static HospitalResponse of(Hospital hospital) {
        return new HospitalResponse(hospital.getId(), hospital.getRoadNameAddress(), hospital.getHospitalName(), hospital.getPatientRoomCount(), hospital.getTotalNumberOfBeds(), hospital.getBusinessTypeName(), hospital.getBusinessStatusCode(), hospital.getBusinessStatusName(), hospital.getTotalAreaSize());
    }
}
