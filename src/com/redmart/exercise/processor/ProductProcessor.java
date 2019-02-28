package com.redmart.exercise.processor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.redmart.exercise.pojo.Product;
import com.redmart.exercise.pojo.Tote;
import com.redmart.exercise.pojo.ToteContent;

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
	
	public List<Product> getFittingProductsList(final List<Product> productList, final Tote tote) {
		return productList
				.stream()
				.filter(prod -> (prod.getLength() <= tote.getLength() && 
					prod.getWidth() <= tote.getWidth() && 
					prod.getHeight() <= tote.getHeight()))
				.collect(Collectors.toList());
	}
	
	public ToteContent getMaxValueItems(final List<Product> productList, final Tote tote) {
		Map<Long, ToteContent> toteContentsMap = new HashMap<>();
		ToteContent emptyToteContent = new ToteContent();
		emptyToteContent.setItemList(new ArrayList<Integer>());
		toteContentsMap.put(tote.getVolume(), emptyToteContent);
		
		for (Product product : productList) {
			Map<Long, ToteContent> tempContentsMap = new HashMap<>();
			tempContentsMap.putAll(toteContentsMap);
			
			for (long volume : tempContentsMap.keySet()) {
				long modifiedVolume = volume - product.getVolume();
				if(modifiedVolume >= 0) {
					ToteContent newToteContent = getToteAggregateContent(toteContentsMap.get(volume), product);
					if (!toteContentsMap.containsKey(modifiedVolume)) {
						toteContentsMap.put(modifiedVolume, newToteContent);
					} else {
						ToteContent existingToteContent = toteContentsMap.get(modifiedVolume);
						if (existingToteContent.getPrice() < newToteContent.getPrice() || 
								(existingToteContent.getPrice() == newToteContent.getPrice() 
								&& existingToteContent.getWeight() >= newToteContent.getWeight())) {
							toteContentsMap.put(modifiedVolume, newToteContent);
						}
					}
				}
			}
		}
		
		return Collections.max(toteContentsMap.values(), Comparator.comparingInt(ToteContent::getPrice));
	}
	
	private ToteContent getToteAggregateContent (final ToteContent existingContent, final Product product) {
		List<Integer> itemsList = new ArrayList<Integer>(existingContent.getItemList());
		itemsList.add(product.getProductId());
		
		ToteContent toteContent = new ToteContent();
		toteContent.setPrice(existingContent.getPrice() + product.getPrice());
		toteContent.setWeight(existingContent.getWeight() + product.getWeight());
		toteContent.setItemList(itemsList);
		
		return toteContent;
	}
}
