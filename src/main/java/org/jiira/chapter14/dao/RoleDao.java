package org.jiira.chapter14.dao;

import org.springframework.stereotype.Repository;

import org.jiira.chapter14.pojo.Role;

@Repository
public interface RoleDao {
	
	public Role getRole(Long id);
}
