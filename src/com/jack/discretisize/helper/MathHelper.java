package com.jack.discretisize.helper;

import java.util.Arrays;

public class MathHelper {
	
	public static int[] generateRandomNumbers(int size, int seedMin, int seedMax) {
		int[] randomMatrix = new int[size];
		
		for(int x = 0; x < size; ++x) {
			randomMatrix[x] = (int) (Math.random() * seedMax + seedMin);
		}
		
		return randomMatrix;
	}
	
	public static int determineMax(int[] maxArray) {
		
		int[] sorted = maxArray.clone();
		Arrays.sort(sorted);	
		
		return sorted[sorted.length-1];
	}
	
	public static int determineMin(int[] minArray) {
		
		int[] sorted = minArray.clone();
		Arrays.sort(sorted);
		
		return sorted[0];
	}
	
	public static double deteremineDiscretionWidth(double interval, int max, int min) {
		double width = 0;
		
		width = (double)((max - min) / interval);
		
		return Math.ceil(width);
	}
	
	public static int calculateMean(int[] projection) {
		int sum = 0;
		int average = projection.length;
		int mean = 0;
		
		for(int x = 0; x < projection.length; ++x) {
			sum += projection[x];
		}
		
		mean = sum / average;
		
		return mean;
	}
	
	public static double calculateDepth(int size, double interval) {
		return Math.floor((double)(size/interval));
	}
	
}
