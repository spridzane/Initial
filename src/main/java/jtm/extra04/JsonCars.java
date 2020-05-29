package jtm.extra04;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONWriter;



public class JsonCars {

	/*- TODO #1
	 * Implement method, which returns list of cars from generated JSON string
	 */
	public List<Car> getCars(String jsonString) {

		/*- HINTS:
		 * You will need to use:
		 * - https://stleary.github.io/JSON-java/org/json/JSONObject.html
		 * - https://stleary.github.io/JSON-java/org/json/JSONArray.html
		 * You will need to initialize JSON array from "cars" key in JSON string
		 */	
		
		List<Car> cars = new ArrayList<>();
		JSONObject jo = new JSONObject(jsonString);
		JSONArray ja = jo.getJSONArray("cars");
		for(int i=0; i < ja.length(); i++) {
			JSONObject jsonObj = ja.getJSONObject(i);
			Car data = new Car(jsonObj.getString("model"),jsonObj.getInt("year"),jsonObj.getString("color"),jsonObj.getFloat("price"));
			cars.add(data);
		}
		
		return cars;
		
	}
		 /* Implement method, which returns JSON String generated from list of cars
		 */
	public String getJson(List<Car> cars) {
		/*- HINTS:
		 * You will need to use:
		 * - https://docs.oracle.com/javase/8/docs/api/index.html?java/io/StringWriter.html
		 * - http://static.javadoc.io/org.json/json/20180130/index.html?org/json/JSONWriter.html
		 * Remember to add "car" key as a single container for array of car objects in it.
		 */
		
		StringWriter sw = new StringWriter();
		JSONWriter jw = new JSONWriter(sw);
		try {
			jw.object().key("cars").array();
			for(Car car: cars) {
				jw.object().key("model").value(car.getModel());
				jw.key("year").value(car.getYear());
				jw.key("color").value(car.getColor());
				jw.key("price").value(car.getPrice());
				jw.endObject();
			}
			jw.endArray();
		}catch(JSONException e) {	
		}
		jw.endObject();
		return sw.toString();
	}

}