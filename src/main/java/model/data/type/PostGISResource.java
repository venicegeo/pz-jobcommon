package model.data.type;

import model.data.ResourceType;

/**
 * Vector resource held by a PostGIS table. For example: a Shapefile loaded with
 * ogr2ogr.
 * 
 * TODO: This class is likely missing some necessary parameters.
 * 
 * @author Patrick.Doody
 * 
 */
public class PostGISResource implements ResourceType {
	private String type = "postgis";
	public String database;
	public String table;

	public PostGISResource() {

	}

	public String getType() {
		return type;
	}
}
