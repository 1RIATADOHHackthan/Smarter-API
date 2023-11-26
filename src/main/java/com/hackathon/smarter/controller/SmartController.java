/**
 * 
 */
package com.hackathon.smarter.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.rules.RuleFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;
import com.hackathon.smarter.schema.ServiceStatus;
import com.sun.codemodel.JCodeModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author veera
 * @param <K>
 *
 */
@RestController
public class SmartController<K> {

	@RequestMapping("/")
	public ServiceStatus smartController(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*InputStream inputStream = new FileInputStream("D:\\Tiru\\Projects\\One_records\\company.json");
		Object jsonObject = JsonUtils.fromInputStream(inputStream);
		Map context = new HashMap();
		JsonLdOptions options = new JsonLdOptions();
		Object compact = JsonLdProcessor.compact(jsonObject, context, options);
		System.out.println(JsonUtils.toPrettyString(compact));*/
		
		JCodeModel codeModel = new JCodeModel();

		URL source = SmartController.class.getResource("D:\\\\Tiru\\\\Projects\\\\One_records\\\\company.json");

		GenerationConfig config = new DefaultGenerationConfig() {
		@Override
		public boolean isGenerateBuilders() { // set config option by overriding method
		return true;
		}
		};

		SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
		mapper.generate(codeModel, "ClassName", "com.example", source);

		codeModel.build(Files.createTempDirectory("required").toFile());
		
		ServiceStatus serviceStatus = new ServiceStatus();
		
		
		serviceStatus.setUsername("Tirumurugan");
		serviceStatus.setPassword("Qatar@123");
		return serviceStatus;
	}
}
