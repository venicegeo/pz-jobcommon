package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.job.result.type.*;
import model.job.type.*;
import model.logger.AuditElement;
import model.logger.LoggerPayload;
import model.logger.MetricElement;
import model.logger.Severity;
import model.request.LogRequest;
import model.request.PiazzaJobRequest;
import model.request.SearchRequest;
import model.resource.*;
import model.resource.UUID;
import model.response.*;
import model.security.authz.*;
import model.service.async.AsyncServiceInstance;
import model.swagger.SwaggerDataType;
import model.swagger.SwaggerFileLocation;
import model.swagger.SwaggerJobType;
import model.swagger.SwaggerResultType;
import org.apache.commons.beanutils.PropertyUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.venice.piazza.common.hibernate.dao.dataresource.DataResourceDaoImpl;
import org.venice.piazza.common.hibernate.entity.*;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Provider;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class PojoTest {
    private ObjectMapper mapper = new ObjectMapper();

    private Map<Class, Supplier<Object>> allObjects = new HashMap<>();

    @Test
    public void testObjects() throws
            Exception {

        registerPrimitiveProviders();

        instantiate(
                LogRequest.class,
                FileResult.class,
                SearchRequest.class,
                PiazzaJobRequest.class
        );

        instantiate(
                UserProfile.class,
                AuthorizationCheck.class,
                UserThrottles.class,
                Permission.class,
                ProfileTemplate.class
                //Throttle.class
        );

        instantiate(
                LoggerPayload.class,
                AuditElement.class,
                Severity.class,
                MetricElement.class
        );

        instantiate(
                FileResult.class,
                ErrorResult.class,
                DataResult.class,
                DeploymentResult.class,
                JobResult.class,
                TextResult.class
        );

        instantiate(
                AccessJob.class,
                ExecuteServiceJob.class,
                UpdateServiceJob.class,
                AbortJob.class,
                DeleteServiceJob.class,
                IngestJob.class,
                RepeatJob.class,
                RegisterServiceJob.class,
                SearchMetadataIngestJob.class,
                SearchServiceJob.class,
                DescribeServiceMetadataJob.class,
                ServiceMetadataIngestJob.class,
                ListServicesJob.class,
                SearchQueryJob.class
        );

        instantiate(
                AuthResponse.class,
                UserProfileResponse.class,
                ServiceJobResponse.class,
                EventTypeListResponse.class,
                EventListResponse.class,
                JobListResponse.class,
                JobResponse.class,
                ServiceIdResponse.class,
                UUIDResponse.class,
                ErrorResponse.class,
                EventResponse.class,
                DeploymentGroupResponse.class,
                EventTypeResponse.class,
                TriggerListResponse.class,
                TriggerResponse.class,
                WorkflowResponse.class
        );

        instantiate(
                CoreResource.class,
                NumericKeyValue.class,
                TextKeyValue.class,
                RegisterService.class,
                DBCoreResource.class,
                UUID.class
        );

        instantiate(
                ApiKeyEntity.class,
                AsyncServiceInstanceEntity.class,
                DataResourceEntity.class,
                DeploymentEntity.class,
                DeploymentGroupEntity.class,
                JobEntity.class,
                LeaseEntity.class,
                ServiceEntity.class,
                ServiceJobEntity.class,
                UserProfileEntity.class,
                UserThrottlesEntity.class
        );

        hydrateBeans();
        serializeBeans();
        testConstructors();

        System.out.println(allObjects);
    }

    private void testConstructors() {
        for (Map.Entry<Class, Supplier<Object>> kvp : this.allObjects.entrySet()) {
            testNonDefaultConstructors(kvp.getKey());
        }
    }

    private void testNonDefaultConstructors(Class clazz) {
        //System.out.println("Test constructors: " + clazz);
        Constructor[] constructors = clazz.getConstructors();

        for (Constructor ctor : constructors) {
            Class[] paramTypes = ctor.getParameterTypes();
            try {
                Object[] paramValues = Arrays.stream(paramTypes).map(x -> allObjects.get(x).get()).toArray();
                Object instance = ctor.newInstance(paramValues);
            } catch (Exception ex) {
                System.out.println("Could not insantiate " + ctor);
            }
        }
    }

    private void serializeBeans() throws IOException {
        for (Map.Entry<Class, Supplier<Object>> kvp : allObjects.entrySet()) {
            if (Arrays.stream(kvp.getKey().getInterfaces()).anyMatch(x -> x.equals(Serializable.class))) {
                serializeAndDeserialize(kvp.getValue().get());
            }
        }
    }

    private void serializeAndDeserialize(Object obj) throws IOException {
        try {
            String str1 = mapper.writeValueAsString(obj);
            String str2 = mapper.writeValueAsString(mapper.readValue(str1, obj.getClass()));

            if (!str1.equals(str2)) {
                System.out.println("Serialized versions did not match for " + obj.getClass());
            }
        } catch (Exception ex) {
            System.out.println("Serialization failed for " + obj);
        }
    }

    private void hydrateBeans() throws InvocationTargetException, IllegalAccessException {
        for (Map.Entry<Class, Supplier<Object>> kvp : allObjects.entrySet()) {
            if (!isBean(kvp.getKey()))
                continue;

            Object obj = kvp.getValue().get();

            PropertyDescriptor[] properties = PropertyUtils.getPropertyDescriptors(kvp.getKey());
            for (PropertyDescriptor prop : properties) {
                Method setter = prop.getWriteMethod();
                if (setter == null)
                    continue;

                Supplier<Object> propSupplier = this.allObjects.getOrDefault(prop.getPropertyType(), null);
                if (propSupplier == null) {
                    System.out.println("Could not find instance of " + prop.getPropertyType());
                } else {
                    Object propValue = propSupplier.get();
                    try {
                        setter.invoke(obj, propValue);
                    } catch (Exception ex) {
                        throw ex;
                    }
                }
            }
        }
    }

    private void registerPrimitiveProviders() {
        this.allObjects.put(Integer.class, () -> ThreadLocalRandom.current().nextInt(0, 1000000));
        this.allObjects.put(int.class, () -> (int) ThreadLocalRandom.current().nextInt(0, 1000000));
        this.allObjects.put(Long.class, () -> ThreadLocalRandom.current().nextLong(0, 1000000));
        this.allObjects.put(long.class, () -> (long) ThreadLocalRandom.current().nextLong(0, 1000000));
        this.allObjects.put(Boolean.class, () -> ThreadLocalRandom.current().nextBoolean());
        this.allObjects.put(boolean.class, () -> (boolean) ThreadLocalRandom.current().nextBoolean());
        this.allObjects.put(String.class, () -> DateTime.now().plusMillis(ThreadLocalRandom.current().nextInt()).toString());
        this.allObjects.put(DateTime.class, () -> DateTime.now().plusMillis(ThreadLocalRandom.current().nextInt()));
        this.allObjects.put(List.class, () -> new ArrayList<>());
        this.allObjects.put(Map.class, () -> new HashMap<>());
    }

    private void instantiate(Class<?>... types) throws InstantiationException, IllegalAccessException {
        for (Class c : types) {
            instantiateSelfAndDependencies(c);
        }
    }

    private void instantiateSelfAndDependencies(Class clazz) throws IllegalAccessException, InstantiationException {
        //Check whether an instance is already registered.
        if (!this.allObjects.containsKey(clazz) && isBean(clazz)) {
            //We need to make an instance of this class.
            Object instance = clazz.newInstance();
            registerInstance(instance);

            //Now register each of the dependent types.
            PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(clazz);
            for (PropertyDescriptor prop : descriptors) {
                if (prop.getWriteMethod() == null)
                    continue;

                instantiateSelfAndDependencies(prop.getPropertyType());
            }

            for (Class argType : Arrays.stream(clazz.getConstructors()).flatMap(x -> Arrays.stream(x.getParameterTypes())).collect(Collectors.toList())) {
                instantiateSelfAndDependencies(argType);
            }
        }
    }

    private void registerInstance(Object instance) {
        Supplier<Object> supplier = () -> instance;
        Class clazz = instance.getClass();

        if (!this.allObjects.containsKey(clazz)) {
            this.allObjects.put(clazz, supplier);

            Class[] interfaces = clazz.getInterfaces();
            for (Class inter : interfaces) {
                this.allObjects.putIfAbsent(inter, () -> instance);
            }
        }
    }

    /**
     * Determines whether the class has a default constructor and at least one
     * read-write property.
     *
     * @param clazz
     * @return
     */
    private boolean isBean(Class clazz) {
        Constructor[] ctors = clazz.getConstructors();
        PropertyDescriptor[] props = PropertyUtils.getPropertyDescriptors(clazz);

        boolean hasDefaultCtor = Arrays.stream(ctors).anyMatch(x -> x.getParameterCount() == 0);
        //boolean hasAnyReadWriteProp = Arrays.stream(props).anyMatch(x -> x.getWriteMethod() != null);

        return hasDefaultCtor; // && hasAnyReadWriteProp;
    }

    private void testClass() {


    }

    private Object getInstance(final Class clazz) throws Exception {
//        for (Object instance : allObjects) {
//            if (clazz.isAssignableFrom(instance.getClass()))
//                return instance;
//        }
//
//        if (!clazz.isInterface()) {
//            Object obj = instantiateAndHydrate(clazz);
//            return obj;
//        }
//
//        throw new Exception("Could not get instance of " + clazz);
        throw new Exception("Not implemented.");
        //return null;
    }

    private Object instantiateAndHydrate(Class clazz) throws Exception {
//        Object obj = clazz.newInstance();
//
//        for (PropertyDescriptor prop : PropertyUtils.getPropertyDescriptors(obj)) {
//
//            Method writeMethod = prop.getWriteMethod();
//            if (writeMethod != null) {
//
//                Object value = null;
//                Class valueType = prop.getPropertyType();
//
//                if (valueType == Long.class || valueType == Integer.class || value == Boolean.class || valueType.isPrimitive()) {
//                    value = getPrimitiveValue(valueType);
//                } else if (valueType == String.class) {
//                    value = DateTime.now().plusMillis(ThreadLocalRandom.current().nextInt()).toString();
//                } else {
//                    value = getInstance(valueType);
//                }
//
////                PropertyEditor editor = prop.createPropertyEditor(obj);
////                editor.setValue(value);
//                writeMethod.invoke(obj, value);
//            }
//        }
//
//        this.allObjects.add(obj);
//
//        return obj;
        throw new Exception("Not implemented.");
    }

//    private Object getPrimitiveValue(Class primitiveClass) throws InstantiationException {
//        if (primitiveClass == int.class || primitiveClass == Integer.class) {
//            return ThreadLocalRandom.current().nextInt();
//        } else if (primitiveClass == long.class || primitiveClass == Long.class) {
//            return ThreadLocalRandom.current().nextLong();
//        } else if (primitiveClass == boolean.class || primitiveClass == Boolean.class) {
//            return ThreadLocalRandom.current().nextBoolean();
//        }
//
//        throw new InstantiationException("Unexpected type.");
//    }

}
