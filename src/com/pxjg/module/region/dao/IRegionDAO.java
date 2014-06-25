package com.pxjg.module.region.dao;

import java.util.List;

import com.pxjg.module.region.entity.Region;


public interface IRegionDAO {
	public Region queryRegionList(Region region);
	public List<Region> queryRegionListTree();
	public Region queryRegionById(String id);
	public boolean updateRegion(Region region);
	public boolean addRegion(Region region);
	public Integer getParentCount(String id);
	public List<Region> getChildRegion(String id);
	public boolean deleteRegion(Region region);
	public Region getRegionByName(String string);
	public List<Region> queryChildRegionByType(String id,String type) ;
}
