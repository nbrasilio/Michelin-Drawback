package com.michelin.mic.projeto.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.michelin.mic.projeto.vo.DrawbackVO;

@Repository
public interface DrawbackRepository extends JpaRepository<DrawbackVO, Integer>{
	
	@Query(value="SELECT *  FROM ATO_NCM WHERE cd_ncm = :ncm and"
		+ " (dt_fim_vig is null or dt_fim_vig >= CURRENT_DATE)",
        nativeQuery = true)
	DrawbackVO findByNcm(@Param("ncm")Integer ncm);
	
	@Query(value="SELECT *  FROM ATO_NCM WHERE cd_ncm = :ncm and"
			+ " (dt_fim_vig is null or dt_fim_vig >= CURRENT_DATE) and"
			+ " cd_ato_ncm != :cd_ato_ncm",
	        nativeQuery = true)
	DrawbackVO findByNcmExceptThis(@Param("ncm")Integer ncm, @Param("cd_ato_ncm")Integer cd_ato_ncm);
	
//	@Override
//	@Query(value="SELECT *  FROM ATO_NCM ORDER BY CD_ATO")
//	List<DrawbackVO> findAll();
	
	@Query(value="SELECT *  FROM ATO_NCM ORDER BY CD_ATO, CD_NCM", nativeQuery = true)
	List<DrawbackVO> findAllByOrder();
	
	@Query(value="SELECT * FROM ATO_NCM WHERE cd_ato = :ato",
            nativeQuery = true)
	DrawbackVO findByAto(@Param("ato") Integer ato);
	
}
