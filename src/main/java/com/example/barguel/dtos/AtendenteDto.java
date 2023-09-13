package com.example.barguel.dtos;

import javax.validation.constraints.NotNull;

public class AtendenteDto {
    @NotNull
    private String username;
    @NotNull
    private String password;

    public AtendenteDto(String username, String password) {
        this.username = username;
        this.password = password;
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
}
