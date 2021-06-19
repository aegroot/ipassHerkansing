package com.example.demo.security.presentation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;

public class Registration {
    @NotBlank
    public String username;

    @Size(min = 5)
    public String password;

    @NotBlank
    public String firstName;

    @NotBlank
    public String lastName;
    @NotBlank
    public  int gbyear;
    @NotBlank
    public   int gbmonth;
    @NotBlank
    public int gbday;


    public Registration(@NotBlank String username, @Size(min = 5) String password, @NotBlank String firstName,
                        @NotBlank String lastName, @NotBlank int gbyear, @NotBlank int gbmonth, @NotBlank int gbday) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gbyear = gbyear;
        this.gbmonth = gbmonth;
        this.gbday = gbday;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getGbyear() {
        return gbyear;
    }

    public int getGbmonth() {
        return gbmonth;
    }

    public int getGbday() {
        return gbday;
    }
}
