package com.icoffee.utils;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *  выводит сообщения на экран
 */

public class AppMessageUtil {

    private static AppMessageUtil appMessageUtil;

    public static AppMessageUtil getInstance(){
        if(appMessageUtil == null){
            appMessageUtil = new AppMessageUtil();
        }
        return appMessageUtil;
    }

    private AppMessageUtil() {
    }

    @PostConstruct
    private void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
    }

    public void showMessage(FacesMessage.Severity severity, String message, String description){
        FacesMessage facesMessage = new FacesMessage(message, description);
        facesMessage.setSeverity(severity);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public void showStartPageMessage(String typeMessage){
        ScreenMessageEnum screenMessageEnum = null;
        switch (typeMessage) {
            case "Save":
                screenMessageEnum = ScreenMessageEnum.SAVE;
                break;
            case "Back":
                screenMessageEnum = ScreenMessageEnum.BACK;
                break;
            case "Edit":
                screenMessageEnum = ScreenMessageEnum.EDIT;
                break;
        }

        if(screenMessageEnum != null){
            showMessage(screenMessageEnum.getSeverity(), screenMessageEnum.getMessage(), screenMessageEnum.getDescription());
        }
    }
}
