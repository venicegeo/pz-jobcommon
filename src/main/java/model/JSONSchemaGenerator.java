package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import model.service.metadata.Service;

public class JSONSchemaGenerator {
   
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        mapper.acceptJsonFormatVisitor(Service.class, visitor);
        JsonSchema jsonSchema = visitor.finalSchema();
        System.out.println(mapper
                .writerWithDefaultPrettyPrinter().writeValueAsString(jsonSchema));
    }
}