package com.michelin.mic.projeto.services;

import java.util.ArrayList;

import com.michelin.mic.projeto.vo.DrawbackVO;

public interface IDrawbackService {

	public ArrayList<DrawbackVO> findAll();

	public int insert(DrawbackVO vo);
	
	public int update(DrawbackVO vo);
	
	public void delete(DrawbackVO vo);
	
	public DrawbackVO findByAto(Integer ato);
}
