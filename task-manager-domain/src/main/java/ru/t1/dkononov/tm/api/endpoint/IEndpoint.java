package ru.t1.dkononov.tm.api.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import javax.jws.WebMethod;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public interface IEndpoint {

    @NotNull
    String HOST = "localhost";

    @NotNull
    String PORT = "8080";

    @NotNull
    String REQUEST = "request";

    @NotNull
    String SPACE = "http://endpoint.tm.dkononov.t1.ru";

    @SneakyThrows
    @WebMethod(exclude = true)
    static <T> T newInstance(
            @NotNull final String name,
            @NotNull final String space,
            @NotNull final String part,
            @NotNull final Class<T> clazz
    ) {
        @NotNull final String host = HOST;
        @NotNull final String port = PORT;
        return newInstance(host, port, name, space, part, clazz);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static <T> T newInstance(
            @NotNull final IConnectionProvider connectionProvider,
            @NotNull final String name,
            @NotNull final String space,
            @NotNull final String part,
            @NotNull final Class<T> clazz
    ) {
        @NotNull final String host = connectionProvider.getHost();
        @NotNull final String port = connectionProvider.getPort();
        return newInstance(host, port, name, space, part, clazz);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static <T> T newInstance(
            @NotNull final String host,
            @NotNull final String port,
            @NotNull final String name,
            @NotNull final String space,
            @NotNull final String part,
            @NotNull final Class<T> clazz
    ) {
        @NotNull final String wsdl = "http://" + host + ":" + port + "/" + name + "?wsdl";
        @NotNull final URL url = new URL(wsdl);
        @NotNull final QName qName = new QName(space, part);
        return Service.create(url, qName).getPort(clazz);
    }



}
