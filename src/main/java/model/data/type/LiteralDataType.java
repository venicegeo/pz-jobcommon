package model.data.type;

import model.data.DataType;

public class LiteralDataType implements DataType {
	 public enum LITERAL{DOUBLE,FLOAT,SHORT,LONG,BYTE,CHAR,BOOLEAN,STRING};
	public static final String type = "literal";
	private LITERAL literalType = LITERAL.STRING;
	
	public LITERAL getLiteralType() {
		return literalType;
	}


	public void setLiteralType(LITERAL literalType) {
		this.literalType = literalType;
	}

	private String value = "";
	
   
	public String getValue() {
		return value;
	}

	
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public String getMimeType() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
