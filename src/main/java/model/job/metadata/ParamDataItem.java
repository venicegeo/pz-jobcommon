package model.job.metadata;

import java.util.List;

public class ParamDataItem {
	String name;
	Integer minOccurs;
	Integer maxOccurs;
	InputType inputType;
	MetadataType metadata;
	List<Format> formats;

}
