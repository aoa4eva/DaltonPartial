package com.example.demo.model;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String role;

    @ManyToMany(mappedBy="roles")
    Set<Daltonuser> users;

    public AppRole() {
        users = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Daltonuser> getUsers() {
        return users;
    }

    public void setUsers(Set<Daltonuser> users) {
        this.users = users;
    }
}
