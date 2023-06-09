package com.devex.SpringWebfluxJpa.dto;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User 
{
	@Id
	private Integer id;
	
	private String name;
	
	private double balance;
}
