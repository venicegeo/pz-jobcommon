package model.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class TextKeyValueJsonDeserializer extends JsonDeserializer<List<TextKeyValue>> {

	@Override
	public List<TextKeyValue> deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		List<TextKeyValue> textKeyValues = new ArrayList<TextKeyValue>();

		JsonNode root = jp.getCodec().readTree(jp);

		for (int i = 0; i != root.size(); ++i) {

			TextKeyValue keyValue = new TextKeyValue(root.get(i).get("key").asText(),
					root.get(i).get("value").asText());

			textKeyValues.add(keyValue);
		}
		return textKeyValues;
	}

}