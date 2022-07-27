package com.example.knowledge.model.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT) //Chỉ cho những thuộc tính k có giá trị default vào response
@Builder
public class CarDTO implements Serializable {	
	
	/** The Constant serialVersionUID */
	private static final long serialVersionUID = -5459945621091646258L;

	private long carId;
	
	private String carName;
	
	private int carPrice;
	
}
