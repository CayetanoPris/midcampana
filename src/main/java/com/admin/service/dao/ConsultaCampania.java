package com.admin.service.dao;

import java.util.List;

import com.admin.dto.CampaniaDTO;
import com.admin.dto.ContadorDTO;

public interface ConsultaCampania {

	ContadorDTO ejecutaSolicitud(String fecha);
	
	List<CampaniaDTO> ejecutaSolicitudPaginado(String fecha,Integer pagina,Integer limite);
	
	List<CampaniaDTO> solicitaReportesPorFecha(String fecha);
}
