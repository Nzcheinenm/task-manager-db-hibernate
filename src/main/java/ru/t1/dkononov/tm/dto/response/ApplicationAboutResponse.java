package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
public class ApplicationAboutResponse extends AbstractResponse {

    @Nullable
    private String email;

    @Nullable
    private String name;

}
