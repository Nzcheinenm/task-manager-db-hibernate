package ru.t1.dkononov.tm.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class AbstractUserOwnedModelDTO extends AbstractModelDTO {

    @Nullable
    @Column(name = "user_id")
    private String userId;

}
