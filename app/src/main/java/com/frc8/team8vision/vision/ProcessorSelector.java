package com.frc8.team8vision.vision;

import com.frc8.team8vision.vision.processors.CentroidProcessor;
import com.frc8.team8vision.vision.processors.DoubleTargetProcessor;
import com.frc8.team8vision.vision.processors.SingleTargetProcessor;

import java.util.HashMap;

/**
 * Select which processor to use
 */
public class ProcessorSelector {

	public enum ProcessorType {
		CENTROID, SINGLE_TARGET, DOUBLE_TARGET
	}

	private HashMap<ProcessorType, VisionProcessorBase> processor_map = new HashMap<>();
	private ProcessorType processor = null;

	public VisionProcessorBase getProcessor(){
		if(processor == null){
			this.setProcessor(ProcessorType.CENTROID);
		}
		return processor_map.get(this.processor);
	}

	public void setProcessor(ProcessorType type){
		this.processor = type;
		if(!processor_map.containsKey(type)){
			switch (type){
				case CENTROID:
					processor_map.put(type, new CentroidProcessor());
					break;
				case SINGLE_TARGET:
					processor_map.put(type, new SingleTargetProcessor());
					break;
				case DOUBLE_TARGET:
					processor_map.put(type, new DoubleTargetProcessor());
					break;
			}
		}
	}
}
