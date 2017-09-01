package com.icoffee.utils;

import javax.faces.application.FacesMessage;

public enum ScreenMessageEnum {

    SAVE(FacesMessage.SEVERITY_INFO, "Your order is saved!", "Your order is created and delivered for processing"),
    BACK(FacesMessage.SEVERITY_ERROR, "You returned back!", "Changes have not been saved"),
    EDIT(FacesMessage.SEVERITY_INFO, "Successful!", "Changes saved");

    private final FacesMessage.Severity severity;
    private final String message;
    private final String description;

    ScreenMessageEnum(FacesMessage.Severity severity, String message, String description) {
        this.severity = severity;
        this.message = message;
        this.description = description;
    }

    public FacesMessage.Severity getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
