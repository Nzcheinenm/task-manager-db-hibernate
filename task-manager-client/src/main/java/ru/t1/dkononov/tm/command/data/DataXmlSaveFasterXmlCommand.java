package ru.t1.dkononov.tm.command.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.request.DataXmlSaveFasterXmlRequest;
import ru.t1.dkononov.tm.enumerated.Role;

public final class DataXmlSaveFasterXmlCommand extends AbstractDataCommand {

    @NotNull
    public static final String DESCRIPTION = "Сохранить данные в xml faster файл.";

    @NotNull
    public static final String NAME = "data-save-xml-faster";

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
        System.out.println("[DATA SAVE XML]");
        @NotNull final DataXmlSaveFasterXmlRequest request = new DataXmlSaveFasterXmlRequest(getToken());
        getDomainEndpoint().saveDataXmlFasterXml(request);
    }

    @Override
    public @Nullable Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }
}
