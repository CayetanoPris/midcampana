package com.admin.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dto.ContadorDTO;
import com.admin.dto.DetallePorCampaniaDTO;
import com.admin.service.GeneraReporte;
import com.admin.service.dao.ConsultaCampaniaDetalle;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GeneraReporteImpl implements GeneraReporte {

	@Autowired
	private ConsultaCampaniaDetalle consultaCampaniaDetalle;
	
	@Override
	public boolean generaReportePorFechaCampania(Integer contador, Integer idCampania, String fecha) {
		String nombreArchivo = "D:\\develop_java\\reportes_campania\\reporte.csv";
		boolean isValid = false;
		ContadorDTO contadorRegistro = consultaCampaniaDetalle.validarDetallePorIdCampania(idCampania);
		if (contadorRegistro.getContadorRegistros()>0) {
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
				
				int limiteP = 4000;
				log.info("Registros detalle " + contadorRegistro.getContadorRegistros());
	    		int paginas = contadorRegistro.getContadorRegistros() / limiteP;
	    		log.info("Registros detalle paginas" + paginas);
	        		for (int i = 0; i <= paginas; i++) {
	        			int offset = (i-1) * limiteP;
	            		List<DetallePorCampaniaDTO> listDetallePorCampaniaDTO = consultaCampaniaDetalle.
	            				recuperaDetallePorIdCampania(idCampania, limiteP, offset);
	                            
								for (DetallePorCampaniaDTO detallePorCampaniaDTO : listDetallePorCampaniaDTO) {
	                                bw.write(generaLineaPorDetallePorCampaniaDTO(detallePorCampaniaDTO));
	                                bw.newLine();
	                            }
								
	            		}
	            		
					}catch (IOException e) {
            			log.info("error archivo#" + e.getLocalizedMessage());
            			
                    }
			}else {
				//System.out.println("No hay nada por procesar");
			}
		return isValid;
	}

	private String generaLineaPorDetallePorCampaniaDTO(DetallePorCampaniaDTO detallePorCampaniaDTO) {
		StringBuilder sb = new StringBuilder();
			sb.append(detallePorCampaniaDTO.getIdSmsDetalle());
			sb.append(",");
			sb.append(detallePorCampaniaDTO.getDetalleCampania());
		return sb.toString();
	}
}
