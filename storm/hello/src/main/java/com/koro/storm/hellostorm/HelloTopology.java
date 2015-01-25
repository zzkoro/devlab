package com.koro.storm.hellostorm;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;

public class HelloTopology {

	public static void main(String args[]) {
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("HelloSpout", new HelloSpout(), 2);
		builder.setBolt("HelloBolt", new HelloBolt(), 4).shuffleGrouping("HelloSpout");
		
		Config conf = new Config();
		conf.setDebug(true);
		
		
		try {
			StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
		} catch (AlreadyAliveException e) {
			System.out.println(e);
		} catch (InvalidTopologyException e) {
			System.out.println(e);
		}
		
	}
}
