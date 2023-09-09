package com.vj.lets.domain.cafe.dto;

import lombok.*;

/**
 * Cafe Option dto
 *
 * @author 강소영
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CafeOption {
    private int id;
    private String name;
    private String imagePath;
    private String description;
}
