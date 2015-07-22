package com.koro.storm.clickTop;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
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
public class RepeatVisitBolt extends BaseRichBolt {
	private static final long serialVersionUID = 1L;
	
	private OutputCollector collector;
	private Jedis jedis;
	private String host;
	private int port;
	
	private static Logger LOG = LoggerFactory.getLogger(RepeatVisitBolt.class);

	
	/* (non-Javadoc)
	 * @see backtype.storm.task.IBolt#prepare(java.util.Map, backtype.storm.task.TopologyContext, backtype.storm.task.OutputCollector)
	 */
	public void prepare(Map conf, TopologyContext paramTopologyContext,
			OutputCollector collector) {
		this.collector = collector;
		host = conf.get(Conf.REDIS_HOST_KEY).toString();
		port = Integer.valueOf(conf.get(Conf.REDIS_PORT_KEY).toString());
		connectToRedis();
	}


	/* (non-Javadoc)
	 * @see backtype.storm.task.IBolt#execute(backtype.storm.tuple.Tuple)
	 */
	public void execute(Tuple tuple) {
		String ip = tuple.getStringByField(FieldNames.IP);
		String clientKey = tuple.getStringByField(FieldNames.CLIENT_KEY);
		String url = tuple.getStringByField(FieldNames.URL);
		
		String key = url + ":" + clientKey;
		String value = jedis.get(key);
		if (value == null) {
			jedis.set(key,  "visited");
			collector.emit(new Values(clientKey, url, Boolean.TRUE.toString()));
		} else {
			collector.emit(new Values(clientKey, url, Boolean.FALSE.toString()));
		}
		
	}



	
	/* (non-Javadoc)
	 * @see backtype.storm.topology.IComponent#declareOutputFields(backtype.storm.topology.OutputFieldsDeclarer)
	 */
	public void declareOutputFields(
			OutputFieldsDeclarer outputFieldsDeclarer) {
		outputFieldsDeclarer.declare(new Fields(FieldNames.CLIENT_KEY, FieldNames.URL, FieldNames.UNIQUE));
		
	}
	
	public boolean isConnected() {
		if (jedis==null) return false;
		return jedis.isConnected();
	}
	
	private void connectToRedis() {
		jedis = new Jedis(host, port);
		jedis.connect();
	}

}
