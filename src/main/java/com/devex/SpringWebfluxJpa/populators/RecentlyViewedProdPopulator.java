package com.devex.SpringWebfluxJpa.populators;

import com.devex.SpringWebfluxJpa.dto.Product;
import com.devex.SpringWebfluxJpa.dto.User;
import com.devex.SpringWebfluxJpa.entity.RecentlyViewedProducts;

public class RecentlyViewedProdPopulator 
{
	public static RecentlyViewedProducts setUserDetails(User user, RecentlyViewedProducts recent)
	{
			 recent.setUserId(user.getId());
			 recent.setUserName(user.getName());
			 return recent;				 
	}
	
	
	public static RecentlyViewedProducts setProductDetails(Product prod, RecentlyViewedProducts recent)
	{
			 recent.setProdCode(prod.getCode());
			 recent.setProdName(prod.getName());
			 return recent;				 
	}
}
