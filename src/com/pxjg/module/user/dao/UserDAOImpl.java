package com.pxjg.module.user.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pxjg.base.dao.BaseDAO;
import com.pxjg.module.user.entity.User;
import com.pxjg.util.PageBean;


public class UserDAOImpl extends BaseDAO implements IUserDAO {

	@Override
	public Map login(User user) {
			User bean = null;
			Map result = new HashMap();
			try {
				Object object = getSqlSession().selectOne("my_users.login", user);
				if (object != null)
					bean = (User) object;
				if(bean == null){
					//用户名或密码错误
					result.put("reFlag", "2");
				}else if(bean.getRole()==0){
					result.put("reFlag", "3");
				}else{
					result.put("reFlag", "1");
					result.put("bean", bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}
	
	public Map frontLogin(User user) {
		User bean = null;
		Map result = new HashMap();
		try {
			Object object = getSqlSession().selectOne("my_users.frontLogin", user);
			if (object != null)
				bean = (User) object;
			if(bean == null){
				//用户名或密码错误
				result.put("reFlag", "2");
			}else{
				result.put("reFlag", "1");
				result.put("bean", bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	

	@Override
	public int getUsersCount(String param) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("param", param);
		int count = getSqlSession().selectOne("my_users.count",map);
		return count;
	}

	@Override
	public List<User> queryUsersByPage(String param,PageBean pageBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startCount", pageBean.getStartCount());
		map.put("endCount", pageBean.getEndCount());
		map.put("param", param);
		List<User> list=new ArrayList<User>();
		list = getSqlSession().selectList("my_users.queryUsersInfoPage", map);
		return list;
	}

	
	@Override
	public User queryUsersById(String id) {
		return getSqlSession().selectOne("my_users.queryUsersByid",id);
	}

	@Override
	public int updateUsers(User user) {
		return getSqlSession().update("my_users.updateUsers",user);
	}

	@Override
	public User queryUsersByUsername(String username) {
		return getSqlSession().selectOne("my_users.queryByUsername",username);
	}

	@Override
	public int insertUsers(User user) {
		return getSqlSession().insert("my_users.insertUsers",user);
	}

	@Override
	public int deleteUsers(long id) {
		return getSqlSession().delete("my_users.deleteUsers",id);
	}


	@Override
	public List<User> queryUserByDeptId(String deptid) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("my_users.queryUsersByDeptId",deptid);
	}


	@Override
	public int updateUserState(String user_id, int status) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("state", status);
		return getSqlSession().update("my_users.updateUserState",map);
	}


	@Override
	public int updatePassword(User user) {
		return getSqlSession().update("my_users.updatePassword",user);
	}


	@Override
	public int queryCountByParam(String param) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("param",param);
		return getSqlSession().selectOne("my_users.queryCountByParam",map);
	}


	@Override
	public List<User> queryUserByParam(PageBean pageBean) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startCount", pageBean.getStartCount());
		map.put("endCount", pageBean.getEndCount());
		map.put("param",pageBean.getParam());
		return getSqlSession().selectList("my_users.queryUserByParam",pageBean);
	}

	@Override
	public int updateFrontUser(User user) {
		// TODO Auto-generated method stub
		return getSqlSession().update("my_users.updateFrontUser",user);
	}



}
