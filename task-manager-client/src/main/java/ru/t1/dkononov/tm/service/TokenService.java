package ru.t1.dkononov.tm.service;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.services.ITokenService;

@Getter
@Setter
public final class TokenService implements ITokenService {

    @NotNull
    private String token;

}
