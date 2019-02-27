package com.redmart.exercise.processor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.redmart.exercise.pojo.Product;

public class ProductProcessor {

	public List<Product> getProductsList(final String fileName) {
		
		Path filePath = Paths.get(fileName);
		List<Product> productList = null;
		
		try(Stream<String> productStream = Files.lines(filePath)) {
			productList = productStream
							.filter(str -> str.trim().length() > 0)
							.map(line -> line.split(","))
							.map(arr -> {
									Product product = new Product();
									
									product.setProductId(Integer.valueOf(arr[0]));
									product.setPrice(Integer.valueOf(arr[1]));
									product.setLength(Integer.valueOf(arr[2]));
									product.setWidth(Integer.valueOf(arr[3]));
									product.setHeight(Integer.valueOf(arr[4]));
									product.setWeight(Integer.valueOf(arr[5]));
									
									return product;})
							.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return productList;
	} 
}
