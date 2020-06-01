/**
 * @author Anis MNASRI
// * \brief class to set data from YAML file
 * 		It's very important to use it if need change every time
 * 		need testing with other data set
 * 
 */
package com.data;

import java.io.File;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import com.data.FromYAMLData;

public class SetGetData {

	/**
	 * Generate a data set from yaml
	 * 
	 * @return element
	 */
	public static Object[][] getDataSets(String yamlName) throws Exception {
		// Instantiating a new ObjectMapper as a YAMLFactory
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		File filePath = new File("src/main/resources/dataSets/" + yamlName + ".yaml");
		FromYAMLData YAMLData = mapper.readValue((filePath), FromYAMLData.class);
		return new Object[][] { { YAMLData } };
	}

}
