package com.admin.service.dao.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.admin.dto.CampaniaDTO;
import com.admin.dto.ContadorDTO;
import com.admin.service.dao.ConsultaCampania;

@Service
public class ConsultaCampaniaImpl implements ConsultaCampania {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ContadorDTO ejecutaSolicitud(String fecha) {
		ContadorDTO contadorDTO = new ContadorDTO();
		String sql = "CALL contar_registros_por_fecha(?)";
		Integer resultado = jdbcTemplate.queryForObject(sql, Integer.class, fecha);
		if (Objects.nonNull(resultado) && resultado>0) {
			contadorDTO.setContadorRegistros(resultado);
		}else {
			contadorDTO.setContadorRegistros(0);
		}
        return contadorDTO;
	}

	@Override
	public List<CampaniaDTO> solicitaReportesPorFecha(String fecha) {
		String sql = "CALL recuperar_idcampanias_byfecha(?)";
		
		return jdbcTemplate.query(sql, new Object[] { fecha }, (rs, rowNum) -> {
			CampaniaDTO campaniaDTO = new CampaniaDTO();
			campaniaDTO.setIdCampania(rs.getInt("idCampania"));
			campaniaDTO.setEstatus(rs.getInt("estatus"));
			return campaniaDTO;
		});
	}

	@Override
	public List<CampaniaDTO> ejecutaSolicitudPaginado(String fecha, Integer pagina, Integer limite) {
		String sql = "CALL recuperar_paginado_campana(?,?,?)";
		
		return jdbcTemplate.query(sql, new Object[] { fecha,pagina,limite }, (rs, rowNum) -> {
			CampaniaDTO campaniaDTO = new CampaniaDTO();
			campaniaDTO.setIdCampania(rs.getInt("idCampania"));
			campaniaDTO.setEstatus(rs.getInt("estatus"));
			return campaniaDTO;
		});
	}

}
