package com.volunteer.web.manager;

import java.util.List;

import com.volunteer.model.Honer;

public interface HonerManager {

	List<Honer> getMyHoner(Long id);
	
	List<Honer> getAllHoner();

	Honer getHonerById(Long id);

	Long save(Honer honer);

}
