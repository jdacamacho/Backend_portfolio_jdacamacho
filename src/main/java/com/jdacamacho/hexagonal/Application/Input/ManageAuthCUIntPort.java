package com.jdacamacho.hexagonal.Application.Input;

import com.jdacamacho.hexagonal.Domain.Objects.Security.Credential;
import com.jdacamacho.hexagonal.Domain.Objects.Security.UserAuth;

public interface ManageAuthCUIntPort {
    public UserAuth login(Credential credential);
}
