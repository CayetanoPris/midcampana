package com.admin.service.impl;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.admin.dto.CampaniaDTO;
import com.admin.service.GeneraReporte;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReporteServiceListenerImpl {
	
	@Autowired
	private GeneraReporte generaReporte;
	
	@RabbitListener(queues = "solitudesReportes")
    public void receiveReporte(List<CampaniaDTO> listCampaniaDTO) {
        try {
        	for (CampaniaDTO campaniaDTO : listCampaniaDTO) {
        		generaReporte.generaReportePorFechaCampania(null, campaniaDTO.getIdCampania(), null);
			}//for
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
