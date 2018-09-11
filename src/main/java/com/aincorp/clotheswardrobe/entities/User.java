package com.aincorp.clotheswardrobe.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Введите ваш логин")
    @Size(min=2, max = 32 ,message = "Введен не корректный логин, введите больше двух символов")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Введите ваш пароль")
    @Size(min=8, max = 32 ,message = "Введен не корректный логин, введите больше восьми символов")
    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_person",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="person_id")
    )
    private Person person;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
    }

    public User(@NotBlank(message = "Введите ваш логин") @Size(min = 2, max = 32, message = "Введен не корректный логин, введите больше двух символов") String username, @NotBlank(message = "Введите ваш пароль") @Size(min = 8, max = 32, message = "Введен не корректный логин, введите больше восьми символов") String password, String confirmPassword, Person person, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.person = person;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}