package com.jack.discretisize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jack.discretisize.helper.MathHelper;

public class Discretization {

	/**
	 * Variables for all operations, equal-width etc
	 */
	int size;
	double interval;
	
	int[] matrix;
	int[] sortedMatrix;
	
	int max;
	int min;
	
	double width;
	double depth;
	
	/** Bins */
	List<Bin> bins;
	
	public Discretization(int size, int interval, int seedMin, int seedMax) {
		this.size = size;
		this.interval = interval;
		this.matrix = MathHelper.generateRandomNumbers(size, seedMin, seedMax);
		this.sortedMatrix = matrix.clone();
		Arrays.sort(sortedMatrix);
		
		this.max = MathHelper.determineMax(matrix);
		this.min = MathHelper.determineMin(matrix);	
		
		width = MathHelper.deteremineDiscretionWidth(this.interval, this.max, this.min);
		
		this.depth = MathHelper.calculateDepth(size, interval);
		
		for(int x = 0; x < size; ++x) {
//			System.out.println(matrix[x]);
			System.out.println("Sorted: " + sortedMatrix[x]);
		}
		System.out.println("MAX:" + max);
		System.out.println("MIN:" + min);
		System.out.println("WIDTH:" + width);
		System.out.println("DEPTH:" + depth);	
		
		generateBins();
	}
	
	public void generateBins() {
		bins = new ArrayList<Bin>();
		
		for(int x = 1; x <= interval; ++x) {
			int onset = min + ((int)width * (x - 1));
			int offset = onset + (int)width;
			if(x>=2)
				onset++;
			Bin bin = new Bin(sortedMatrix, onset, offset, (int)width, (int)depth, x);
			bins.add(bin);
		}
	}

	public List<Bin> getBins() {
		return bins;
	}
	
}
