package com.koro.storm.clickTop;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 * 
 * ClickSpout
 * @author junemp
 * @date 2015. 3. 29.
 */
public class VisitStatsBolt extends BaseRichBolt {
	
	private OutputCollector collector;
	
	private int total = 0;
	private int uniqueCount = 0;
	
	private static Logger LOG = LoggerFactory.getLogger(VisitStatsBolt.class);

	
	/* (non-Javadoc)
	 * @see backtype.storm.task.IBolt#prepare(java.util.Map, backtype.storm.task.TopologyContext, backtype.storm.task.OutputCollector)
	 */
	@Override
	public void prepare(Map conf, TopologyContext paramTopologyContext,
			OutputCollector collector) {
		this.collector = collector;
	}


	/* (non-Javadoc)
	 * @see backtype.storm.task.IBolt#execute(backtype.storm.tuple.Tuple)
	 */
	@Override
	public void execute(Tuple tuple) {
		
		boolean unique = Boolean.parseBoolean(tuple.getStringByField(FieldNames.UNIQUE));
		total++;
		if (unique)
			uniqueCount++;

		collector.emit(new Values(total, uniqueCount));
	}



	/* (non-Javadoc)
	 * @see backtype.storm.topology.IComponent#declareOutputFields(backtype.storm.topology.OutputFieldsDeclarer)
	 */
	@Override
	public void declareOutputFields(
			OutputFieldsDeclarer outputFieldsDeclarer) {
		outputFieldsDeclarer.declare(new Fields(FieldNames.TOTAL_COUNT, FieldNames.TOTAL_UNIQUE));
		
	}
}
