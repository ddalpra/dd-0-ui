package org.dalpra.acme.dd34bse.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import org.dalpra.acme.dd34bse.entity.User;
import org.dalpra.acme.person.entity.Person;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;


import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserController implements Serializable {
    private List<User> userList;
    private User user;
    private transient Client client;
    static String BASE_URL = "http://localhost:9000/";

    public UserController(){
        FacesContext fc = FacesContext.getCurrentInstance();
        user = new User();
    }

    public List<User> getUserList() {
        ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
        ResteasyWebTarget target = client.target(BASE_URL+"api/users");
        Invocation.Builder request = target.request();
        Response response = null;
        try
        {
            response = request.get();
            userList = response.readEntity(new GenericType<List<User>>() {});

        }
        finally
        {
            response.close();
            client.close();
        }

        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void salvaUser(){
        FacesMessage msg;
        ResteasyClient client = (ResteasyClient)ClientBuilder.newClient();
        ResteasyWebTarget target = client.target(BASE_URL+"api/users");

        Response response = target.request()
                .post(Entity.json(user));

        System.out.println(Entity.json(user));

        int statoInsert = response.getStatus();

        msg = new FacesMessage("Stato inserimento " + statoInsert);
        //System.out.println(response.getStatus());
        response.close();
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
