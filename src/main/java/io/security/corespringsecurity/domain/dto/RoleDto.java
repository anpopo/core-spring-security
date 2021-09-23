package io.security.corespringsecurity.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDto {
    private String id;
    private String roleName;
    private String roleDesc;

    @Override
    public String toString() {
        return "role: " + roleName + "/ roleDesc: " + roleDesc;
    }
}
