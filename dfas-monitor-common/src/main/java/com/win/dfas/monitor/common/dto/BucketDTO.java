package com.win.dfas.monitor.common.dto;

import java.util.List;

import com.win.dfas.monitor.common.dto.microservice.ApplicationInstance;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BucketDTO {

	private String key;
	private Long count;
	
}
