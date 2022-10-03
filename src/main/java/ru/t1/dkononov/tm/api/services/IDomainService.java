package ru.t1.dkononov.tm.api.services;

public interface IDomainService {
    void loadDataBackup() throws Exception;

    void saveDataBackup();

    void loadDataBase64() throws Exception;

    void saveDataBase64() throws Exception;

    void saveDataBinary() throws Exception;

    void loadDataBinary() throws Exception;

    void saveDataJsonFasterXml() throws Exception;

    void loadDataJsonFasterXml() throws Exception;

    void saveDataJsonJaxB() throws Exception;

    void loadDataJsonJaxB() throws Exception;

    void saveDataXmlFasterXml() throws Exception;

    void loadDataXmlFasterXml() throws Exception;

    void saveDataXmlJaxB() throws Exception;

    void loadDataXmlJaxB() throws Exception;
}
