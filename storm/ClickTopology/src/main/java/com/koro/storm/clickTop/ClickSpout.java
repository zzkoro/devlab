package com.koro.storm.clickTop;

import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import redis.clients.jedis.Jedis;

/**
 * 
 * ClickSpout
 * @author junemp
 * @date 2015. 3. 29.
 */
public class ClickSpout extends BaseRichSpout {
	private static final long serialVersionUID = 1L;
	
	private SpoutOutputCollector collector;
	private Jedis jedis;
	private String host;
	private int port;
	
	private static Logger LOG = LoggerFactory.getLogger(ClickSpout.class);

	/**
	 * 
	 */
	public void nextTuple() {
		String content = jedis.rpop("count");
		if (content==null || "nil".equals(content)) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {}
		} else {
			JSONObject obj = (JSONObject)JSONValue.parse(content);
			String ip = obj.get(FieldNames.IP).toString();
			String url = obj.get(FieldNames.URL).toString();
			String clientKey = obj.get(FieldNames.CLIENT_KEY).toString();
			
			collector.emit(new Values(ip, url, clientKey));
		}
	}

	/**
	 * 
	 */
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		host = conf.get(Conf.REDIS_HOST_KEY).toString();
		port = Integer.valueOf(conf.get(Conf.REDIS_PORT_KEY).toString());
		this.collector = collector;
		connectToRedis();

	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields(FieldNames.IP, FieldNames.URL, FieldNames.CLIENT_KEY));
		
	}
	
	private void connectToRedis() {
		jedis = new Jedis(host, port);
		jedis.connect();
	}

}
