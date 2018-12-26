package org.jiira.chapter15.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import org.jiira.chapter15.pojo.Role;
import org.jiira.chapter15.pojo.RoleParams;

@Repository
public interface RoleDao {
	
	public int insertRole(Role role);
	
	public Role getRole(Long id);
	
	public List<Role> findRoles(RoleParams roleParams);
	
	public int deleteRole(Long id);
}
