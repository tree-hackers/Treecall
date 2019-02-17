package com.ethmeff.factorybackend.model;

import java.util.ArrayList;
import java.util.List;

public class PartTree {

	private Part part;
	private List<PartTree> partTrees = new ArrayList<>();

	public PartTree() {
	}

	public PartTree(Part part, List<PartTree> partTree) {
		super();
		this.part = part;
		this.partTrees = partTree;
	}

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public List<PartTree> getPartTrees() {
		return partTrees;
	}

	public void setPartTrees(List<PartTree> partTrees) {
		this.partTrees = partTrees;
	}

	public void addToPartTrees(PartTree partTree) {
		this.partTrees.add(partTree);
	}
}
