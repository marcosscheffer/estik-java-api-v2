package com.marcos.estik.shared.domain.user;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

public enum PermissionEnum {
    TICKET_OPEN, TICKET_WORK, TICKET_SUPERVISOR, TICKET_MANAGE,

    STOCK_READ, STOCK_MANAGE,

    COMPUTER_READ, COMPUTER_MANAGE,

    FACILITY_MANAGE,

    USER_MANAGE,

    SYSTEM_MANAGE;

    public Set<PermissionEnum> implied() {
        return switch(this) {
            case TICKET_WORK -> EnumSet.of(TICKET_OPEN);
            case TICKET_SUPERVISOR -> EnumSet.of(TICKET_WORK, TICKET_OPEN);
            case TICKET_MANAGE -> EnumSet.of(TICKET_OPEN, TICKET_WORK, TICKET_SUPERVISOR);
            
            case STOCK_MANAGE -> EnumSet.of(STOCK_READ);

            case COMPUTER_MANAGE -> EnumSet.of(COMPUTER_READ);

            case SYSTEM_MANAGE -> EnumSet.of(
                TICKET_OPEN, 
                TICKET_WORK, 
                TICKET_SUPERVISOR, 
                TICKET_MANAGE,
                STOCK_READ, 
                STOCK_MANAGE,
                COMPUTER_READ, 
                COMPUTER_MANAGE,
                FACILITY_MANAGE,
                USER_MANAGE
            );

            default -> EnumSet.noneOf(PermissionEnum.class);

        };
    }

    public static Set<PermissionEnum> expand(Collection<PermissionEnum> permissions) {
        Set<PermissionEnum> result = EnumSet.noneOf(PermissionEnum.class);

        for (PermissionEnum permission : permissions) {
            result.add(permission);
            result.addAll(permission.implied());
        }
        return result;
    } 

    public static Set<PermissionEnum> grantedBy(PermissionEnum target) {
        Set<PermissionEnum> result = EnumSet.noneOf(PermissionEnum.class);

        for(PermissionEnum permission : values()) {
            if (permission == target || permission.implied().contains(target)) {
                result.add(permission);
            }
        }

        return result;
    }
}
