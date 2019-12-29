package com.snt.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse {

	private int codeStatus;
	
	private String message;
	
	private String description;
	
	private long took;
	
	private Object data;

}
