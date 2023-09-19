package com.vj.lets.domain.group.util;


import lombok.Getter;

/**
 *  스터디 그룹 직위 타입 이넘 클래스
 */
@Getter
public enum PositionType {
    LEADER("팀장"), MEMBER("팀원");

    private final String type;

    private PositionType(String type) {
        this.type = type;
    }

}
