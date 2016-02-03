package model.data.type;

import model.data.ResourceType;

/**
 * Represents simple text that can be stored directly within MongoDB's Resource
 * collection.
 * 
 * @author Patrick.Doody
 * 
 */
public class TextResource implements ResourceType {
	private String type = "text";
	public String content;
	public String mimeType;

	public TextResource() {

	}

	public String getType() {
		return type;
	}

	public String getContent() {
		return content;
	}

	public String getMimeType() {
		return mimeType;
	}

}
