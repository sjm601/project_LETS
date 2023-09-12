package com.vj.lets.domain.member.util;


public enum MemberType {
    GUEST("guest"), HOST("host"), ADMIN("admin");

    private final String type;

    private MemberType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
