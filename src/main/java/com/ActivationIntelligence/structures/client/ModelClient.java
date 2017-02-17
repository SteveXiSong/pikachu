package com.ActivationIntelligence.structures.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

/**
 * Created by Steve on 2/15/17.
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ModelClient implements Client {
    @Id
    @GeneratedValue
    private String clientId;
    private String clientName;

    private String username;
    @JsonIgnore
    private String password;

    private Date registrationDate;

    @OneToMany(mappedBy = "roleName")
    private Set<ModelClientRole> modelClientRole;
}
