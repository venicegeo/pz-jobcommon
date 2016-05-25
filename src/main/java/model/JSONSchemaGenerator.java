package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.customProperties.HyperSchemaFactoryWrapper;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import model.service.metadata.Service;

public class JSONSchemaGenerator {
   
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        mapper.acceptJsonFormatVisitor(Service.class, visitor);
        JsonSchema jsonSchema = visitor.finalSchema();
        System.out.println("PRINTING THe JSON Schema for the Service Model");
        System.out.println("==============================================");

        System.out.println(mapper
                .writerWithDefaultPrettyPrinter().writeValueAsString(jsonSchema));
        
        HyperSchemaFactoryWrapper serviceVisitor = new HyperSchemaFactoryWrapper();
        mapper.acceptJsonFormatVisitor(Service.class, serviceVisitor);
        jsonSchema = serviceVisitor.finalSchema();
        System.out.println("PRINTING THe Hyper JSON Schema for the Service Model");
        System.out.println("====================================================");
        System.out.println(mapper
                .writerWithDefaultPrettyPrinter().writeValueAsString(jsonSchema));
    }
}