package com.admin.service.dao;

import java.util.List;

import com.admin.dto.ContadorDTO;
import com.admin.dto.DetalleCampaniaDTO;
import com.admin.dto.DetallePorCampaniaDTO;

public interface ConsultaCampaniaDetalle {

	ContadorDTO validarDetallePorIdCampania(Integer idCampania);
	
	List<DetalleCampaniaDTO> recuperaPaginacionDetallePorIdCampania(Integer idCampania, Integer pagina, Integer limite);

	List<DetallePorCampaniaDTO> recuperaDetallePorIdCampania(Integer idCampania, Integer pagina, Integer limite);

}
