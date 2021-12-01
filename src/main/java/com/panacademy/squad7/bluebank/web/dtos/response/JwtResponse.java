package com.panacademy.squad7.bluebank.web.dtos.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JwtResponse {
	private Long id;
	private String username;
	private String email;
	private List<String> roles;
	private String token;
	private String type = "Bearer";
}
