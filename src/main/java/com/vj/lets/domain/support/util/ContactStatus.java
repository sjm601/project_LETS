package com.vj.lets.domain.support.util;


public enum ContactStatus {
    HOLD("hold"), APPROVE("approve"), REFUSE("refuse");

    private final String status;

    private ContactStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
