package ru.t1.dkononov.tm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.dto.model.ProjectDTO;
import ru.t1.dkononov.tm.dto.model.TaskDTO;
import ru.t1.dkononov.tm.dto.model.UserDTO;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@XmlRootElement
@NoArgsConstructor
@XmlType(name = "domain")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "domain")
public final class Domain implements Serializable {

    private final static long serialVersionUID = 1;

    @NotNull
    private String id = UUID.randomUUID().toString();

    @NotNull
    private Date created = new Date();

    @NotNull
    @JsonProperty("user")
    @XmlElement(name = "user")
    @XmlElementWrapper(name = "users")
    @JacksonXmlElementWrapper(localName = "users")
    private List<UserDTO> users = new ArrayList<>();

    @NotNull
    @JsonProperty("project")
    @XmlElement(name = "project")
    @XmlElementWrapper(name = "projects")
    @JacksonXmlElementWrapper(localName = "projects")
    private List<ProjectDTO> projects = new ArrayList<>();

    @NotNull
    @JsonProperty("ru/t1/dkononov/tm/task")
    @XmlElement(name = "ru/t1/dkononov/tm/task")
    @XmlElementWrapper(name = "tasks")
    @JacksonXmlElementWrapper(localName = "tasks")
    private List<TaskDTO> tasks = new ArrayList<>();

}
