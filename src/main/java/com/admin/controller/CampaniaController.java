package com.admin.controller;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.dto.ContadorDTO;
import com.admin.dto.ReporteCampaniaDTO;
import com.admin.service.SolicitudReporteService;
import com.admin.service.dao.ConsultaCampania;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/reporte")
@Slf4j
public class CampaniaController {

	@Autowired
	private ConsultaCampania consultaCampania;
	
	@Autowired
	private SolicitudReporteService solicitudReporteService;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public String solicitudReporteCampania(@RequestParam String fecha) {
		log.info("Solicitud para reporte-->>");
		String sucess = "No hay data para reporte de acuerdo a la fecha introducida";
		try {
			ContadorDTO valid = consultaCampania.ejecutaSolicitud(fecha);
			if (valid.getContadorRegistros()>0) {
				ReporteCampaniaDTO reporte = new ReporteCampaniaDTO();
				reporte.setFecha(fecha);
				reporte.setTotalRegistrosEnBD(valid.getContadorRegistros());
				reporte.setFechaSolicitud(new Date());
				solicitudReporteService.solicitudReporteCampaniaPorFecha(reporte);
				sucess = "Reporte generado";
			}
			return sucess;
		} catch (Exception e) {
			  return "Error: Formato de fecha inválido. Use yyyy-MM-dd";
		}
		
	}
	
}
