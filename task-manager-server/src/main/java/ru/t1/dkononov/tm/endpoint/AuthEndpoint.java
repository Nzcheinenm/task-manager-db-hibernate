package ru.t1.dkononov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.services.IAuthService;
import ru.t1.dkononov.tm.api.services.IServiceLocator;
import ru.t1.dkononov.tm.dto.request.UserLoginRequest;
import ru.t1.dkononov.tm.dto.response.UserLoginResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import static ru.t1.dkononov.tm.api.endpoint.IEndpoint.REQUEST;

@WebService(endpointInterface = "ru.t1.dkononov.tm.api.endpoint.IAuthEndpoint")
public final class AuthEndpoint extends AbstractEndpoint {

    public AuthEndpoint(@NotNull IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

//    @NotNull
//    @WebMethod
//    public UserLoginResponse login(
//            @WebParam(name = REQUEST, partName = REQUEST)
//            @NotNull final UserLoginRequest request
//    ) {
//        @NotNull final IAuthService authService = getServiceLocator().getAuthService();
//
//    }

}
