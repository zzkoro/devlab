package com.koro.storm.hellostorm;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

public class HelloBolt extends BaseBasicBolt {

	private static final long serialVersionUID = 1L;

	public void execute(Tuple paramTuple, BasicOutputCollector paramBasicOutputCollector) {

		String value = paramTuple.getStringByField("say");
		System.out.println("Tuple value is" + value);
		
	}

	public void declareOutputFields(
			OutputFieldsDeclarer paramOutputFieldsDeclarer) {

		
	}

}
