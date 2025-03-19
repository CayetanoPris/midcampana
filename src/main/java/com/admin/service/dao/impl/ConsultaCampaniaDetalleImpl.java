package com.admin.service.dao.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.admin.dto.ContadorDTO;
import com.admin.dto.DetalleCampaniaDTO;
import com.admin.dto.DetallePorCampaniaDTO;
import com.admin.service.dao.ConsultaCampaniaDetalle;

@Service
public class ConsultaCampaniaDetalleImpl implements ConsultaCampaniaDetalle {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public ContadorDTO validarDetallePorIdCampania(Integer idCampania) {
		ContadorDTO contadorDTO = new ContadorDTO();
		String sql = "CALL registros_detalle_por_idcampania(?)";
		Integer resultado = jdbcTemplate.queryForObject(sql, Integer.class, idCampania);
		if (Objects.nonNull(resultado) && resultado>0) {
			contadorDTO.setContadorRegistros(resultado);
		}else {
			contadorDTO.setContadorRegistros(0);
		}
        return contadorDTO;
	}

	@Override
	public List<DetalleCampaniaDTO> recuperaPaginacionDetallePorIdCampania(Integer idCampania, Integer pagina, Integer limite) {
		String sql = "CALL recuperar_paginado_campana(?,?,?)";
		return jdbcTemplate.query(sql, new Object[] { idCampania,pagina,limite }, (rs, rowNum) -> {
			DetalleCampaniaDTO detalleCampaniaDTO = new DetalleCampaniaDTO();
			detalleCampaniaDTO.setIdCampanaDetalle(rs.getInt("idta_sms_detalla"));
			detalleCampaniaDTO.setDetalleCampana(rs.getString("detalle_campania"));
			return detalleCampaniaDTO;
		});
	}

	@Override
	public List<DetallePorCampaniaDTO> recuperaDetallePorIdCampania(Integer idCampania, Integer pagina, Integer limite) {
		String sql = "CALL recuperar_paginado_detallec(?,?,?)";
		return jdbcTemplate.query(sql, new Object[] { idCampania,pagina,limite}, (rs, rowNum) -> {
			DetallePorCampaniaDTO detallePorCampaniaDTO = new DetallePorCampaniaDTO();
			detallePorCampaniaDTO.setIdSmsDetalle(rs.getInt("idta_sms_detalla"));
			detallePorCampaniaDTO.setDetalleCampania(rs.getString("detalle_campania"));
			return detallePorCampaniaDTO;
		});
	}

}
