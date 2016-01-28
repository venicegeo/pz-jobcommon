package model.alerter;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJson {
	private Event event;
	@Before
	public void Setup() {
		event = new Event();
		HashMap<String,String> testMap = new HashMap<String,String>();
		testMap.put("parrot1","Hope");
		testMap.put("parrot2","Marie");
		event.setId("testid");
		event.setType("testType");
		event.setDate("testDate");
		event.setData(testMap);
	}
	@Test
	public void testEvent() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String testJson = mapper.writeValueAsString(event);
			Event tevent = mapper.readValue(testJson, Event.class);
			assertTrue(event.getId().contains(tevent.getId()));
			assertTrue(event.getType().contains(tevent.getType()));
			assertTrue(event.getDate().contains(tevent.getDate()));
			assertTrue(event.getData().get("parrot2").contains(tevent.getData().get("parrot2")));
			
			System.out.println(testJson);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
