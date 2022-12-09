package com.example.knowledge.model.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.knowledge.label.LabelKey;
import com.example.knowledge.util.ValidateConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL) // Chỉ cho những thuộc tính k có giá trị default vào response
public class CarDTO implements Serializable {

	/** The Constant serialVersionUID */
	private static final long serialVersionUID = -5459945621091646258L;

	private Long carId;

	@NotBlank(message = LabelKey.ERROR_CAR_NAME_IS_REQUIRED)
	@Size(max = ValidateConstraint.LENGTH.CAR_NAME_MAX_LENGTH, message = LabelKey.ERROR_CAR_NAME_MAX_LENGTH_IS_INVALID)
	private String carName;

	@NotNull(message = LabelKey.ERROR_CAR_PRICE_IS_REQUIRED)
	private Integer carPrice;

	private Integer status;
	
	private String createdBy;

	private Instant createdDate;
	
	private String lastModifiedBy;

	private Instant lastModifiedDate;

}
