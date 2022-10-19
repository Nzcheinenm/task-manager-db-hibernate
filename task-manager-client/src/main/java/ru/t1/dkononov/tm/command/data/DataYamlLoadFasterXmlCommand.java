package ru.t1.dkononov.tm.command.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.DataYamlLoadFasterXmlRequest;
import ru.t1.dkononov.tm.enumerated.Role;

public final class DataYamlLoadFasterXmlCommand extends AbstractDataCommand {

    @NotNull
    public static final String DESCRIPTION = "Загрузить данные из yaml файла.";

    @NotNull
    public static final String NAME = "data-load-yaml";

    @Override
    public @Nullable String getARGUMENT() {
        return null;
    }

    @Override
    public @NotNull String getDESCRIPTION() {
        return DESCRIPTION;
    }

    @Override
    public @NotNull String getNAME() {
        return NAME;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("[DATA LOAD YAML]");
        @NotNull final DataYamlLoadFasterXmlRequest request = new DataYamlLoadFasterXmlRequest(getToken());
        getDomainEndpoint().loadDataYaml(request);
    }

    @Override
    public @Nullable Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
