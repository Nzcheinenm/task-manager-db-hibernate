package ru.t1.dkononov.tm.service;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.services.ITokenService;

@Getter
@Setter
public final class TokenService implements ITokenService {

    @Nullable
    private String token;

}
