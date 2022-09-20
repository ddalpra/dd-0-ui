package org.dalpra.acme.person.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dalpra.acme.person.entity.Person;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import java.io.Serializable;
import java.util.List;

@Named("personController")
@SessionScoped
public class PersonController implements Serializable {
    private List<Person> personList;
    private Person person;
    private transient Client client;
    static String BASE_URL = "http://localhost:9000/";

    public PersonController() {
        FacesContext fc = FacesContext.getCurrentInstance();

    }

    public List<Person> getPersonList() {
        ResteasyClient client = (ResteasyClient)ClientBuilder.newClient();
        ResteasyWebTarget target = client.target(BASE_URL+"person/");
        Invocation.Builder request = target.request();
        Response response = null;
        try
        {
            response = request.get();
            personList = response.readEntity(new GenericType<List<Person>>() {});

        }
        finally
        {
            response.close();
            client.close();
        }

        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public Person getPerson() {
        ResteasyClient client = (ResteasyClient)ClientBuilder.newClient();
        ResteasyWebTarget target = client.target(BASE_URL+"person/");
        Invocation.Builder request = target.request();
        Response response = null;
        try
        {
            response = request.get();
            personList = response.readEntity(new GenericType<List<Person>>() {});

        }
        finally
        {
            response.close();
            client.close();
        }
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void salvaPerson(){
        FacesMessage msg;
        ResteasyClient client = (ResteasyClient)ClientBuilder.newClient();
        ResteasyWebTarget target = client.target(BASE_URL+"person/");
        Response response = target.request()
                .post(Entity.entity(person, "application/json"));

        int statoInsert = response.getStatus();

        msg = new FacesMessage("Stato inserimento " + statoInsert);
        //System.out.println(response.getStatus());
        response.close();
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
