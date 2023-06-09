package com.devex.SpringWebfluxJpa.dto;

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
public class Product 
{
	private String _id;
	
	private String code;
	
	private String name;
	
	private double price;
}
