package com.ActivationIntelligence.structures.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Set;

/**
 * Created by Steve on 2/15/17.
 */
@Data
@Entity
@Builder
@AllArgsConstructor
public class ModelClientRole implements Role {
    @Id
    @GeneratedValue
    private String roleId;

    @ManyToOne
    private String roleName;

    private Set<Permission> permissionSet;
}
