package com.example.knowledge.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePrivilegeId implements Serializable {
	private static final long serialVersionUID = 4614452268210347472L;

	private long roleId;

	private long privilegeId;
}
