package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
public class ApplicationVersionResponse extends AbstractResponse {

    @Nullable
    private String version;

}
