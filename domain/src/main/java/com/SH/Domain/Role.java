package com.SH.Domain;

import java.util.List;

public class Role {

    private String id;
    private String roleName;
    private String roleDesc;
    private List<UserInfo> userInfos;
    private List<Permission> permissions;

    public Role() {
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", userInfos=" + userInfos +
                ", permissions=" + permissions +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Role(String id, String roleName, String roleDesc, List<UserInfo> userInfos, List<Permission> permissions) {
        this.id = id;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.userInfos = userInfos;
        this.permissions = permissions;
    }
}
