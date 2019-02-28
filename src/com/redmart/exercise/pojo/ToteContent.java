package com.redmart.exercise.pojo;

import java.util.List;

public class ToteContent {

	private int price;
	
	private List<Integer> itemList;
	
	private int weight;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<Integer> getItemList() {
		return itemList;
	}

	public void setItemList(List<Integer> itemList) {
		this.itemList = itemList;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "ToteContent [price=" + price + ", itemList=" + itemList + ", weight=" + weight + "]";
	}
}
