package com.admin.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		boolean isValidFecha = validarFecha(fecha);
		if (isValidFecha) {
			ContadorDTO contadorRegistro = consultaCampania.ejecutaSolicitud(fecha);
			if (contadorRegistro.getContadorRegistros()>0) {
				solicitudReporteService.solReporteCampaniaPorFecha(getRequest(fecha, contadorRegistro.getContadorRegistros()));
				return "Reporte Generado";
			}else {
				return "No hay datos para reporte";
			}
		}else {
			return "Fecha no valida";
		}
	}
	
	private ReporteCampaniaDTO getRequest(String fecha,Integer contadorRegistros) {
		ReporteCampaniaDTO reporte = new ReporteCampaniaDTO();
		reporte.setFecha(fecha);
		reporte.setTotalRegistrosEnBD(contadorRegistros);
		reporte.setFechaSolicitud(new Date());
		return reporte;
	}
	
	public static boolean validarFecha(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        formato.setLenient(false); // Para que no acepte fechas no válidas
        try {
            Date fechaValida = formato.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
	
}
