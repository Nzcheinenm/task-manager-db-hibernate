package ru.t1.dkononov.tm.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractModelDTO implements Serializable {

    @NotNull
    private String id = UUID.randomUUID().toString();

}
