package ru.t1.dkononov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.t1.dkononov.tm.marker.UnitCategory;


@Category(UnitCategory.class)
public class PropertyServiceTest {

    @NotNull
    public final PropertyService propertyService = new PropertyService();


    @Test
    public void getApplicationVersion() {
        Assert.assertNotNull(propertyService.getApplicationVersion());
    }

    @Test
    public void getAuthorEmail() {
        Assert.assertNotNull(propertyService.getAuthorEmail());
    }

    @Test
    public void getAuthorName() {
        Assert.assertNotNull(propertyService.getAuthorName());
    }

    @Test
    public void getApplicationConfig() {
        Assert.assertNotNull(propertyService.getApplicationConfig());
    }

    @Test
    public void getServerPort() {
        Assert.assertNotNull(propertyService.getServerPort());
    }

    @Test
    public void getServerHost() {
        Assert.assertNotNull(propertyService.getServerHost());
    }

    @Test
    public void getSessionKey() {
        Assert.assertNotNull(propertyService.getSessionKey());
    }

    @Test
    public void getSessionTimeout() {
        Assert.assertNotNull(propertyService.getSessionTimeout());
    }

}
