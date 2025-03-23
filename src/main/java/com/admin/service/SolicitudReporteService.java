package com.admin.service;

import java.util.concurrent.CompletableFuture;

import com.admin.dto.ReporteCampaniaDTO;

public interface SolicitudReporteService {
	
	boolean solicitudReporteCampaniaPorFecha(ReporteCampaniaDTO reporteCampaniaDTO);
	
	CompletableFuture<String> solReporteCampaniaPorFecha(ReporteCampaniaDTO reporteCampaniaDTO);

}
