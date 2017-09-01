package com.icoffee.utils;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.core.Response;

/**
 *  получение актуального курса валют
 */

@ManagedBean(name = "currencyCourser")
@ApplicationScoped
public class CurrencyCourserUtil {

    private String path = "http://www.nbrb.by/API/ExRates/Rates/145";

    public String getServerJson() {

        String serverAnswer = "";
        try {
            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(path);
            Response response = target.request().get();
            String value = response.readEntity(String.class);
            for(String s: value.split(",")){
                if(s.contains("Cur_OfficialRate")){
                    serverAnswer = s.split(":")[1].replaceAll("}", "");
                }
            }
            response.close();
        } catch (Exception e) {
            serverAnswer = "error";
            e.printStackTrace();
        }
        return serverAnswer;
    }
}
