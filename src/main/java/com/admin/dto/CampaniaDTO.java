package com.admin.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampaniaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4349124200996926620L;

	private Integer idCampania;
	
	private Integer estatus;
	
}
