package com.jack.discretisize;

import java.util.ArrayList;
import java.util.List;

import com.jack.discretisize.helper.MathHelper;

public class Bin {
	
	int[] originalArray;
	int onset;
	int offset;
	int width;
	int depth;
	int position;
	
	public Bin(int[] originalArray, int onset, int offset, int width, int depth,int position) {
		this.originalArray = originalArray.clone();
		this.onset = onset;
		this.offset = offset;
		this.width = width;
		this.depth = depth;
		this.position = position;
	}
	
	public int[] determineEqualWidth() {
		List<Integer> binContainer = new ArrayList<Integer>();
		
		for(int x = 0; x < originalArray.length; ++x) {
			if(originalArray[x] <= offset && originalArray[x] >= onset) {
				binContainer.add(originalArray[x]);
			}
		}
		
		int[] equalWidth = new int[binContainer.size()];
		for(int x = 0; x < binContainer.size(); ++x) {
			equalWidth[x] = binContainer.get(x).intValue();
		}
		
		return equalWidth;
	}
	
	public int[] determineEqualDepth() {
		List<Integer> binContainer = new ArrayList<Integer>();
		int flag = 0;
		for(int x = 0; x < depth; ++x) {
			flag = x + ((position-1) * depth);
			if(flag < originalArray.length)
				binContainer.add(originalArray[flag]);
			else
				break;
		}

		int[] equalDepth = new int[binContainer.size()];
		for(int x = 0; x < binContainer.size(); ++x) {
			equalDepth[x] = binContainer.get(x).intValue();
		}
		
		return equalDepth;
	}
	
	public double[] smoothByMean() {
		int[] equalDepth = determineEqualDepth();
		double mean = MathHelper.calculateMean(equalDepth);
		double[] smoothedByMeans = new double[equalDepth.length];
		
		for(int x = 0; x < smoothedByMeans.length; ++x) {
			smoothedByMeans[x] = mean;
		}
		
		return smoothedByMeans;
	}
	
	public int[] smoothByBoundaries() {
		int[] equalDepth = determineEqualDepth();
		int max = MathHelper.determineMax(equalDepth);
		int min = MathHelper.determineMin(equalDepth);	
		
		
		for(int x = 0; x < equalDepth.length; ++x) {
			if((min - equalDepth[x]) > (equalDepth[x] - max)) {
				equalDepth[x] = min;
			} else
				equalDepth[x] = max;
		}
		
		return equalDepth;
	}
	
}
