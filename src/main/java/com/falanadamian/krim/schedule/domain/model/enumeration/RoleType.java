package com.falanadamian.krim.schedule.domain.model.enumeration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum RoleType {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_GUEST("ROLE_GUEST");

    private String roleType;

    RoleType(String roleType){
        this.roleType = roleType;
    }

    public String getRoleType() {
        return roleType;
    }

    private static final Map<String, RoleType> ROLE_TYPE_MAP = new HashMap<>();

    static {
        Arrays.stream(RoleType.values()).forEach(s -> ROLE_TYPE_MAP.put(s.getRoleType(), s));
    }

    public static RoleType of(String value) {
        return ROLE_TYPE_MAP.getOrDefault(value, ROLE_GUEST);
    }
}
