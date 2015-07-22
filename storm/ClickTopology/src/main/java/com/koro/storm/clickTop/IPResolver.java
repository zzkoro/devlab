/**
 * 
 */
package com.koro.storm.clickTop;

import org.json.simple.JSONObject;

/**
 * IPResolver
 * @author junemp
 * @date 2015. 3. 29.
 */
public interface IPResolver {
	public JSONObject resolveIP(String ip);

}
