package com.bookreccs.dao;

import com.bookreccs.entity.Role;

public interface RoleDao {
	public Role findRoleByName(String theRoleName);
}
