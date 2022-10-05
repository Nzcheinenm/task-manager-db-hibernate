package ru.t1.dkononov.tm.command.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.DataYamlSaveFasterXmlRequest;
import ru.t1.dkononov.tm.enumerated.Role;

public final class DataYamlSaveFasterXmlCommand extends AbstractDataCommand {

    @NotNull
    public static final String DESCRIPTION = "Сохранить данные в yaml файл.";

    @NotNull
    public static final String NAME = "data-save-yaml";

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
        System.out.println("[DATA SAVE YAML]");
        @NotNull final DataYamlSaveFasterXmlRequest request = new DataYamlSaveFasterXmlRequest();
        getDomainEndpoint().saveDataYaml(request);
    }

    @Override
    public @Nullable Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
