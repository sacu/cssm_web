package org.jiira.chapter16.dao;


import org.springframework.stereotype.Repository;

import org.jiira.chapter16.pojo.Role;

@Repository
public interface RoleDao {
	
	
	public Role getRole(Long id);
	
	public int updateRole(Role role);
}
