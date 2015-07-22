package com.koro.storm.clickTop;

import java.util.Map;

import org.json.simple.JSONObject;
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
public class GeographyBolt extends BaseRichBolt {
	private IPResolver resolver;
	
	private OutputCollector collector;
	
	private static Logger LOG = LoggerFactory.getLogger(GeographyBolt.class);

	public GeographyBolt(IPResolver resolver) {
		this.resolver = resolver;
	}

	/* (non-Javadoc)
	 * @see backtype.storm.task.IBolt#prepare(java.util.Map, backtype.storm.task.TopologyContext, backtype.storm.task.OutputCollector)
	 */
	@Override
	public void prepare(Map conf, TopologyContext paramTopologyContext,
			OutputCollector collector) {
		this.collector = collector;
	}
	
	
	@Override
	public void execute(Tuple tuple) {
		String ip = tuple.getStringByField(FieldNames.IP);
		JSONObject json = resolver.resolveIP(ip);
		
		String city = (String)json.get(FieldNames.CITY);
		String country =(String)json.get(FieldNames.COUNTRY_NAME);
		
		collector.emit(new Values(country, city));

	}


	/* (non-Javadoc)
	 * @see backtype.storm.topology.IComponent#declareOutputFields(backtype.storm.topology.OutputFieldsDeclarer)
	 */
	@Override
	public void declareOutputFields(
			OutputFieldsDeclarer outputFieldsDeclarer) {
		outputFieldsDeclarer.declare(new Fields(FieldNames.COUNTRY, FieldNames.CITY));
		
	}
	
}
