package ru.t1.dkononov.tm.model;

public abstract class AbstractUserOwnedModel extends AbstractModel {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
