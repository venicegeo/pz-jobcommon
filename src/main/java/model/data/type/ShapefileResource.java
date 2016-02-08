package model.data.type;

import model.data.DataType;
import model.data.location.FileLocation;

/**
 * Shapefile resource with accompanying files on disk. Shapefiles are typically
 * sent to the Gateway compressed into a single .zip, so as to define the user
 * only send a single file through the POST message. Upon receiving the message,
 * the appropriate components will inflate this zip file as necessary.
 * 
 * @author Patrick.Doody
 * 
 */
public class ShapefileResource implements DataType {
	public static final String type = "shapefile";
	public String databaseTableName;
	public FileLocation location;

	public ShapefileResource() {

	}

	public String getType() {
		return type;
	}

	/**
	 * Gets the location of the raw, zipped shapefile that was sent to the
	 * Gateway upon Ingest.
	 * 
	 * @return Shapefile location
	 */
	public FileLocation getLocation() {
		return location;
	}

	/**
	 * Sets the location of the raw, zipped shapefile that was sent to the
	 * Gateway upon Ingest.
	 * 
	 * @param location
	 *            The current shapefile location
	 */
	public void setLocation(FileLocation location) {
		this.location = location;
	}

	/**
	 * Gets the Piazza PostGIS table name that this Shapefile was inserted into
	 * 
	 * Shapefiles are more easily managed as PostGIS database tables, than as
	 * flat shapefiles. Because of this, all Shapefiles that are ingested into
	 * Piazza are then inserted into the internal Piazza PostGIS database. From
	 * this point on, they are then referenced via this table.
	 * 
	 * @return Table name for this Resource
	 */
	public String getDatabaseTableName() {
		return databaseTableName;
	}

	/**
	 * Sets the table name for the Piazza PostGIS database that holds this
	 * dataset.
	 * 
	 * Shapefiles are more easily managed as PostGIS database tables, than as
	 * flat shapefiles. Because of this, all Shapefiles that are ingested into
	 * Piazza are then inserted into the internal Piazza PostGIS database. From
	 * this point on, they are then referenced via this table.
	 * 
	 * @param tableName
	 *            The Table name in the database for this dataset.
	 */
	public void setDatabaseTableName(String databaseTableName) {
		this.databaseTableName = databaseTableName;
	}
}
