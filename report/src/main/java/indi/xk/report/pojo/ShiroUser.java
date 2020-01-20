package indi.xk.report.pojo;

/**
 * Created by zxy on 2020/1/3.
 */

import java.util.List;

public class ShiroUser {
    private String userName;
    private String password;
    private Boolean available;
    private String role;
    private List<String> permissions;

    public ShiroUser(String userName, String password, Boolean available, String role, List<String> permissions) {
        this.userName = userName;
        this.password = password;
        this.available = available;
        this.role = role;
        this.permissions = permissions;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}