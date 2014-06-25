package com.pxjg.module.user.dao;

import java.util.List;
import java.util.Map;

import com.pxjg.module.user.entity.User;
import com.pxjg.util.PageBean;


public interface IUserDAO {

	public Map login(User user);
	public int getUsersCount(String param);
	public List<User> queryUsersByPage(String param,PageBean pageBean);
	public User queryUsersById(String id);
	public int updateUsers(User user);
	public int updatePassword(User user);
	public User queryUsersByUsername(String username);
	public int insertUsers(User user);
	public int deleteUsers(long id);
	public List<User> queryUserByDeptId(String deptid);
	public int updateUserState(String user_id,int status);
	public int queryCountByParam(String param);
	public List<User> queryUserByParam(PageBean pageBean);
	public Map frontLogin(User user);
	public int updateFrontUser(User user);
}
