package com.vj.lets.domain.cafe.dto;

import lombok.*;

import java.util.List;

/**
 *Cafe dto
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023. 9. 7. (목)
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CafeEditForm {
    private int id;
    private String email;
    private String name;
    private String phoneNumber;
    private String roadAddress;
    private String detailAddress;
    private double latitude;
    private double longitude;
    private int startTime;
    private int endTime;
    private String description;
    private String imagePath;
    private String regdate;
    private int businessNumber;
    private String status;
    private int siGunGuId;
    private int memberId;
    private List<Integer> options;
}
