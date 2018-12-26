package org.jiira.chapter16.service;

import java.util.List;

import org.jiira.chapter16.pojo.Role;

public interface RoleService {
	
	
	public Role getRole(Long id);
	
	public int updateRole(Role role);
	
	public int updateRoleArr(List<Role> roleList);
	
}
