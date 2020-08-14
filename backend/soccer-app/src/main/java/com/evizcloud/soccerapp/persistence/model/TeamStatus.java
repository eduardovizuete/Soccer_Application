package com.evizcloud.soccerapp.persistence.model;

public enum TeamStatus {

    //@formatter:off
    ACTIVE("Active"),
    INACTIVE("Inactive");
    //@formatter:on

    private final String label;

    TeamStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
