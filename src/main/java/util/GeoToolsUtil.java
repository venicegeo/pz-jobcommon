package util;

import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.opengis.feature.simple.SimpleFeatureType;

/**
 * Helpful utility methods used in conjunction with GeoTools Data Stores and
 * Features.
 * 
 * The Ingest and Access components use GeoTools heavily for data parsing and
 * manipulation, and this utility aims to create a common place for redundant
 * code.
 * 
 * @author Patrick.Doody
 * 
 */
public class GeoToolsUtil {
	/**
	 * GeoTools offers no simple way to rename an existing FeatureType. This
	 * method provides a nice way to clone a feature type with a new name.
	 * 
	 * @param featureType
	 *            The existing Feature Type to clone
	 * @param newTypeName
	 *            The name of the new, cloned Feature Type
	 * @return New Feature Type with the specified name.
	 */
	public static SimpleFeatureType cloneFeatureType(SimpleFeatureType featureType, String newTypeName) {
		SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
		builder.setName(newTypeName);
		builder.setNamespaceURI(featureType.getName().getNamespaceURI());
		builder.setCRS(featureType.getCoordinateReferenceSystem());
		builder.addAll(featureType.getAttributeDescriptors());
		builder.setDefaultGeometry(featureType.getGeometryDescriptor().getLocalName());
		return builder.buildFeatureType();
	}
}
