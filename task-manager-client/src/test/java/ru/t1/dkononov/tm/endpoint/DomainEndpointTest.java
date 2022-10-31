package ru.t1.dkononov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.api.endpoint.IAuthEndpoint;
import ru.t1.dkononov.tm.api.endpoint.IDomainEndpoint;
import ru.t1.dkononov.tm.api.services.IPropertyService;
import ru.t1.dkononov.tm.dto.request.*;
import ru.t1.dkononov.tm.marker.IntegrationCategory;
import ru.t1.dkononov.tm.service.PropertyService;


@Category(IntegrationCategory.class)
public class DomainEndpointTest {

    @NotNull
    private static final IPropertyService propertyService = new PropertyService();

    @NotNull
    private static final IAuthEndpoint authEndpoint = IAuthEndpoint.newInstance(propertyService);

    @NotNull
    private static final IDomainEndpoint domainEndpoint = IDomainEndpoint.newInstance(propertyService);

    @Nullable
    private static String token;

    @NotNull
    private final static String login = "admin";

    @NotNull
    private final static String pass = "admin";

    @BeforeClass
    public static void setUp() throws Exception {
        @NotNull final UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setLogin(login);
        loginRequest.setPassword(pass);
        token = authEndpoint.login(loginRequest).getToken();
    }

    @Test
    public void loadDataBackup() throws Exception {
        @NotNull final DataBackupLoadRequest request = new DataBackupLoadRequest(token);
        Assert.assertNotNull(domainEndpoint.loadDataBackup(request));
    }

    @Test
    public void saveDataBackup() throws Exception {
        @NotNull final DataBackupSaveRequest request = new DataBackupSaveRequest(token);
        Assert.assertNotNull(domainEndpoint.saveDataBackup(request));
    }

    @Test
    public void loadDataBase64() throws Exception {
        @NotNull final DataBase64LoadRequest request = new DataBase64LoadRequest(token);
        Assert.assertNotNull(domainEndpoint.loadDataBase64(request));
    }

    @Test
    public void saveDataBase64() throws Exception {
        @NotNull final DataBase64SaveRequest request = new DataBase64SaveRequest(token);
        Assert.assertNotNull(domainEndpoint.saveDataBase64(request));
    }

    @Test
    public void saveDataBinary() throws Exception {
        @NotNull final DataBinarySaveRequest request = new DataBinarySaveRequest(token);
        Assert.assertNotNull(domainEndpoint.saveDataBinary(request));
    }

    @Test
    public void loadDataBinary() throws Exception {
        @NotNull final DataBinaryLoadRequest request = new DataBinaryLoadRequest(token);
        Assert.assertNotNull(domainEndpoint.loadDataBinary(request));
    }

    @Test
    public void saveDataJsonFasterXml() throws Exception {
        @NotNull final DataJsonSaveFasterXmlRequest request = new DataJsonSaveFasterXmlRequest(token);
        Assert.assertNotNull(domainEndpoint.saveDataJsonFasterXml(request));
    }

    @Test
    public void loadDataJsonFasterXml() throws Exception {
        @NotNull final DataJsonLoadFasterXmlRequest request = new DataJsonLoadFasterXmlRequest(token);
        Assert.assertNotNull(domainEndpoint.loadDataJsonFasterXml(request));
    }

    @Test
    public void saveDataJsonJaxB() throws Exception {
        @NotNull final DataJsonSaveJaxBRequest request = new DataJsonSaveJaxBRequest(token);
        Assert.assertNotNull(domainEndpoint.saveDataJsonJaxB(request));
    }

    @Test
    public void loadDataJsonJaxB() throws Exception {
        @NotNull final DataJsonLoadJaxBRequest request = new DataJsonLoadJaxBRequest(token);
        Assert.assertNotNull(domainEndpoint.loadDataJsonJaxB(request));
    }

    @Test
    public void saveDataXmlFasterXml() throws Exception {
        @NotNull final DataXmlSaveFasterXmlRequest request = new DataXmlSaveFasterXmlRequest(token);
        Assert.assertNotNull(domainEndpoint.saveDataXmlFasterXml(request));
    }


    @Test
    public void saveDataXmlJaxB() throws Exception {
        @NotNull final DataXmlSaveJaxBRequest request = new DataXmlSaveJaxBRequest(token);
        Assert.assertNotNull(domainEndpoint.saveDataXmlJaxB(request));
    }

    @Test
    public void loadDataYaml() throws Exception {
        @NotNull final DataYamlLoadFasterXmlRequest request = new DataYamlLoadFasterXmlRequest(token);
        Assert.assertNotNull(domainEndpoint.loadDataYaml(request));
    }

    @Test
    public void saveDataYaml() throws Exception {
        @NotNull final DataYamlSaveFasterXmlRequest request = new DataYamlSaveFasterXmlRequest(token);
        Assert.assertNotNull(domainEndpoint.saveDataYaml(request));
    }

}
