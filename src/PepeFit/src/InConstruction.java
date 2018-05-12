package org.primefaces.showcase.view.misc;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class InConstruction {

    public void onIdle() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                "THIS PAGE IS UNDER CONSTRUCTION.", "PROCEED AT YOUR OWN RISK"));
    }

    public void onActive() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                "THIS PAGE IS UNDER CONSTRUCTION.", "PROCEED AT YOUR OWN RISK"));
    }
}