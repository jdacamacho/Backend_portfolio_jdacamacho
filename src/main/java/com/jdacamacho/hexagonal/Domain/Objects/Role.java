package com.jdacamacho.hexagonal.Domain.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Role {
    private long id;
    private String name;

    public Role(){

    }

}
