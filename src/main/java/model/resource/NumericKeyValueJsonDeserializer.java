package model.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class NumericKeyValueJsonDeserializer extends JsonDeserializer<List<NumericKeyValue>> {

	@Override
	public List<NumericKeyValue> deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		List<NumericKeyValue> numberKeyValues = new ArrayList<NumericKeyValue>();

		JsonNode root = jp.getCodec().readTree(jp);

		for (int i = 0; i != root.size(); ++i) {

			NumericKeyValue keyValue = new NumericKeyValue(root.get(i).get("key").asText(),
					Double.parseDouble(root.get(i).get("value").asText()));

			numberKeyValues.add(keyValue);
		}
		return numberKeyValues;
	}

}
