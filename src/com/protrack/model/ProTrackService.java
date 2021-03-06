package com.protrack.model;

import java.sql.Date;
import java.util.List;

import com.pro.model.ProVO;

public class ProTrackService {
	private ProTrackDAO_interface dao;
	public ProTrackService(){
		dao = new ProTrackJDBCDAO();
	}
	public ProTrackVO addProTrack(String mem_No,String pro_No){
		ProTrackVO proTrackVO = new ProTrackVO();
		proTrackVO.setMem_No(mem_No);
		proTrackVO.setPro_No(pro_No);
		dao.insert(proTrackVO);
		return proTrackVO;
	}
	
	
	public List<ProTrackVO> getAll() {
		return dao.getAll();

	}
	public List<ProTrackVO> getOnePro(String mem_No){
		return dao.findByPrimaryKey(mem_No);
	}
	
	public void deletePro(ProTrackVO proTrackVO){
		dao.delete(proTrackVO);
	}
	
	
	
}
