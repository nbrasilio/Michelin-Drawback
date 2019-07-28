package com.michelin.mic.projeto.services;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.michelin.mic.projeto.repositories.DrawbackRepository;
import com.michelin.mic.projeto.vo.DrawbackVO;

@Service
public class DrawbackService implements IDrawbackService {
	
	@Autowired
	private DrawbackRepository repository;


	@Override
	public ArrayList<DrawbackVO> findAll() {
		return new ArrayList<DrawbackVO>(repository.findAllByOrder());
	}

	@Override
	public int insert(@ModelAttribute @Valid DrawbackVO vo) {
		if (repository.findByNcm(vo.getNcm()) == null) {
			repository.save(vo);
			return 0;
		}
		return -1;
	}

	@Override
	public int update(DrawbackVO vo) {
		if(vo.getDataFinal()==null || vo.getDataFinal().after(vo.getDataInicial())) {
			if (repository.findByNcmExceptThis(vo.getNcm(), vo.getCd_ato_ncm()) == null) {
				repository.save(vo);
				return 0;
			}
			return -2;
		}
		return -1;
	}

	@Override
	public void delete(@ModelAttribute DrawbackVO vo) {
		repository.delete(vo);
	}

	@Override
	public DrawbackVO findByAto(Integer ato) {
		return repository.findByAto(ato);
	}
	
}
