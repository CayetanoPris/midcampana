package com.admin.service.impl;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dto.CampaniaDTO;
import com.admin.dto.ContadorDTO;
import com.admin.dto.ReporteCampaniaDTO;
import com.admin.service.SolicitudReporteService;
import com.admin.service.dao.ConsultaCampania;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SolicitudReporteServiceImpl implements SolicitudReporteService {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private ConsultaCampania consultaCampania;

	@Override
	public boolean solicitudReporteCampaniaPorFecha(ReporteCampaniaDTO reporteCampaniaDTO) {
		boolean isValid = false;
		ContadorDTO cont = consultaCampania.ejecutaSolicitud(reporteCampaniaDTO.getFecha());
		List<CampaniaDTO> list = null;
		int limiteP = 50;
		int paginas = cont.getContadorRegistros() / limiteP;
		log.info("Paginas en campania" + paginas);
		for (int i = 0; i <= paginas; i++) {
			int offset = (i-1) * limiteP;
			list = consultaCampania.ejecutaSolicitudPaginado(reporteCampaniaDTO.getFecha(), limiteP, offset);
			rabbitTemplate.convertAndSend("solitudesReportes", list);
		}
		isValid = true;
		return isValid;
	}

}
