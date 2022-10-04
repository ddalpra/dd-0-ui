package org.dalpra.acme.dd34bse.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
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
}
