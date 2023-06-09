package com.devex.SpringWebfluxJpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "recentlyViewedProd")
public class RecentlyViewedProducts 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	private String prodCode;
	
	private String prodName;
	
	private Integer userId;
	
	private String userName;
	
}
