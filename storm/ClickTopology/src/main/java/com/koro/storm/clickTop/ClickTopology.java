/**
 * 
 */
package com.koro.storm.clickTop;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;

/**
 * ClickTopology
 * @author junemp
 * @date 2015. 3. 29.
 */
public class ClickTopology {
	
	private TopologyBuilder builder = new TopologyBuilder();
	private Config conf = new Config();
	private LocalCluster cluster;
	
	static final String DEFAULT_JEDIS_PORT = "6379";
	
	public ClickTopology() {
		builder.setSpout("clickSpout", new ClickSpout(), 10);
		
		builder.setBolt("repeatsBolt", new RepeatVisitBolt(), 10).shuffleGrouping("clickSpout");
		builder.setBolt("geographyBolt", new GeographyBolt(new HttpIPResolver()), 10).shuffleGrouping("clickSpout");
		
		builder.setBolt("totalStats", new VisitStatsBolt(), 1).globalGrouping("repeatsBolt");
		builder.setBolt("geoStats", new GeoStatsBolt(), 10).fieldsGrouping("geographyBolt", new Fields(FieldNames.COUNTRY));
		
		conf.put(Conf.REDIS_PORT_KEY, DEFAULT_JEDIS_PORT);
	}
	
	private TopologyBuilder getBuilder() {
		return builder;
	}
	
	private LocalCluster getLocalCluster() {
		return cluster;
	}
	
	private Config getConf() {
		return conf;
	}
	
	public void runLocal(int runTime) {
		conf.setDebug(true);
		conf.put(Conf.REDIS_HOST_KEY, "localhost");
		cluster = new LocalCluster();
		cluster.submitTopology("test", conf, builder.createTopology());
		if (runTime > 0) {
			Utils.sleep(runTime);
			shutdownLocal();
		}
	}
	
	private void shutdownLocal() {
		if (cluster != null) {
			cluster.killTopology("test");
			cluster.shutdown();
		}
	}
	
	public void runCluster(String name, String redisHost) throws AlreadyAliveException, InvalidTopologyException {
		conf.setNumWorkers(20);
		conf.put(Conf.REDIS_HOST_KEY, redisHost);
		StormSubmitter.submitTopology(name, conf, builder.createTopology());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ClickTopology topology = new ClickTopology();
		
		if (args!=null && args.length > 1) {
			topology.runCluster(args[0], args[1]);
		} else {
			if (args!=null && args.length == 1) {
				topology.runLocal(10000);
			}
		}

	}

}
