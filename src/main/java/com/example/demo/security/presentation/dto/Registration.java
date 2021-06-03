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
}
