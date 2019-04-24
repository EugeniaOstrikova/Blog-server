package com.ostrikova.blogserver.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User extends AbstractEntity{
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password_value")
    private String password;

    @Column(name = "email")
    private String email;

    public static class Builder {
        private String firstName;
        private String lastName;
        private String password;
        private String email;

        public Builder firstName(String value) {
            firstName = value;
            return this;
        }

        public Builder lastName(String value) {
            lastName = value;
            return this;
        }

        public Builder password(String value) {
            password = value;
            return this;
        }

        public Builder email(String value) {
            email = value;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public User() {}

    private User(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        password = builder.password;
        email = builder.email;
        this.setCreatedDate(new Date());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
