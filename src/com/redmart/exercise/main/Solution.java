package com.redmart.exercise.main;

import java.util.List;

import com.redmart.exercise.pojo.Product;
import com.redmart.exercise.pojo.Tote;
import com.redmart.exercise.processor.ProductProcessor;

public class Solution {

	public static void main(String[] args) {
		Tote tote = new Tote();
		tote.setLength(45);
		tote.setWidth(30);
		tote.setHeight(35);

		ProductProcessor processor = new ProductProcessor();
		List<Product> productsList = processor.getProductsList("src/products.csv");

		List<Product> filteredProductsList = processor.getFittingProductsList(productsList, tote);
		System.out.println(processor.getMaxValueItems(filteredProductsList, tote));
	}
}
