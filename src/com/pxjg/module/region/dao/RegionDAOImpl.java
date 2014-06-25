package com.pxjg.module.region.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pxjg.base.dao.BaseDAO;
import com.pxjg.module.region.entity.Region;

public class RegionDAOImpl extends BaseDAO implements IRegionDAO {

	@Override
	public Region queryRegionList(Region region) {
		region.setTotal(getRegionCount(region));
		region.setDatas(getSqlSession().selectList("my_region.queryRegion",region));
		return region;
	}

	public int getRegionCount(Region region){
		return getSqlSession().selectOne("my_region.getRegionCount",region);
	}
	public List<Region> queryRegionListTree(){
		return getSqlSession().selectList("my_region.queryRegionTree");
	}

	@Override
	public Region queryRegionById(String id) {
		return getSqlSession().selectOne("my_region.queryRegionById",id);
	}

	@Override
	public boolean updateRegion(Region region) {
		boolean flag=false;
		try {
			getSqlSession().update("my_region.updateRegion",region);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean addRegion(Region region) {
		boolean flag=false;
		try {
			getSqlSession().insert("my_region.addRegion",region);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deleteRegion(Region region) {
		boolean flag=false;
		try {
			
			getSqlSession().update("my_region.deleteRegion",region);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	public Integer getParentCount(String id) {
		return getSqlSession().selectOne("my_region.getParentCount",id);
	}

	@Override
	public List<Region> getChildRegion(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("my_region.queryChildRegion",id);
	}

	@Override
	public Region getRegionByName(String string) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("my_region.queryRegionByName",string);
	}
	
	@Override
	public List<Region> queryChildRegionByType(String id,String type) {
		// TODO Auto-generated method stub
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("type", type);
		
		return getSqlSession().selectList("my_region.queryChildRegionByType",map);
	}
	
	

}


