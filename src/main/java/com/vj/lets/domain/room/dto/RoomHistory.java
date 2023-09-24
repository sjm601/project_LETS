package com.vj.lets.domain.room.dto;

import lombok.*;

/**
 * 카페 룸 히스토리 DTO
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class RoomHistory {

    private int id;
    private String modifyComment;
    private String modifyDate;
    private int roomId;

}
