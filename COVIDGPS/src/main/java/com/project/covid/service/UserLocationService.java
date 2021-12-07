package com.project.covid.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.covid.mapper.UserLocationMapper;
import com.project.covid.model.InfectionLoc;
import com.project.covid.model.LocationVO;

@Service
public class UserLocationService {

	@Autowired
	private UserLocationMapper mapper;
	
	public List<String> trackingId(List<InfectionLoc> mcode_list,String id) {
		List<String> uid_List = new ArrayList<>();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date it;
		Date ot;
		for (InfectionLoc loc : mcode_list) {
			String[] m_list = loc.getMcode().split("/");
			try {
				it = fm.parse(loc.getInTime());
				ot = fm.parse(loc.getOutTime());
				if (loc.getMcode().length() == 0) {
					uid_List.addAll(mapper.getUidContactorLoc(loc.getLoc(), it, ot));
				} else {
					for (String str : m_list) {
						uid_List.addAll(mapper.getUidContactor(str, it, ot));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		HashSet<String> dupData = new HashSet<String>(uid_List);
		uid_List = new ArrayList<String>(dupData);
		uid_List.remove(id);
		return uid_List;
	}
	public List<LocationVO> trackingIdLocationVO(List<InfectionLoc> mcode_list,String id) {
		List<LocationVO> uid_List = new ArrayList<>();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date it;
		Date ot;
		for (InfectionLoc loc : mcode_list) {
			String[] m_list = loc.getMcode().split("/");
			try {
				it = fm.parse(loc.getInTime());
				ot = fm.parse(loc.getOutTime());
				if (loc.getMcode().length() == 0) {
					//uid_List.addAll(mapper.getUidContactorLoc(loc.getLoc(), it, ot));
				} else {
					for (String str : m_list) {
						uid_List.addAll(mapper.getUidContactorLocationVO(str, it, ot));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		HashSet<LocationVO> dupData = new HashSet<>(uid_List);
		uid_List = new ArrayList<LocationVO>(dupData);
		for(LocationVO vo: uid_List) {
			if(vo.getId()==id) {
				uid_List.remove(vo);
			}
		}		
		return uid_List;
	}
}
