package ru.t1.dkononov.tm.command.user;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.UserProfileRequest;
import ru.t1.dkononov.tm.enumerated.Role;
import ru.t1.dkononov.tm.exception.AbstractException;
import ru.t1.dkononov.tm.model.User;

public final class UserViewProfileCommand extends AbstractUserCommand {

    @Getter
    @NotNull
    private final String NAME = "view-user-profile";

    @Getter
    @NotNull
    private final String DESCRIPTION = "view profile of current user";

    @Override
    public void execute() throws AbstractException {
        @NotNull final UserProfileRequest request = new UserProfileRequest();
//        @NotNull final User user = getAuthEndpointClient().profile(request).getUser();
//        System.out.println("[USER VIEW PROFILE]");
//        System.out.println("ID: " + user.getId());
//        System.out.println("LOGIN: " + user.getId());
//        System.out.println("FIRST NAME: " + user.getFirstName());
//        System.out.println("MIDDLE NAME: " + user.getMiddleName());
//        System.out.println("LAST NAME: " + user.getLastName());
//        System.out.println("E-MAIL: " + user.getEmail());
//        System.out.println("ROLE: " + user.getRole().getDisplayName());
    }

    @Nullable
    @Override
    public Role[] getRoles() {
        return Role.values();
    }

}
