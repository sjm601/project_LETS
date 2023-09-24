package com.vj.lets.domain.group.util;


import lombok.Getter;

/**
 * 스터디 그룹 직위 enum 클래스
 *
 * @author VJ특공대 이희영
 * @version 1.0
 * @since 2023-09-22 (금)
 */
@Getter
public enum PositionType {
    LEADER("팀장"), MEMBER("팀원");

    private final String type;

    private PositionType(String type) {
        this.type = type;
    }

}
