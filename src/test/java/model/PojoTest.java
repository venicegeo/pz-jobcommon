//package model;
//
//import java.beans.PropertyDescriptor;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ThreadLocalRandom;
//import java.util.function.Supplier;
//import java.util.stream.Collectors;
//
//import org.apache.commons.beanutils.PropertyUtils;
//import org.joda.time.DateTime;
//import org.junit.Test;
//import org.venice.piazza.common.hibernate.entity.ApiKeyEntity;
//import org.venice.piazza.common.hibernate.entity.AsyncServiceInstanceEntity;
//import org.venice.piazza.common.hibernate.entity.DataResourceEntity;
//import org.venice.piazza.common.hibernate.entity.DeploymentEntity;
//import org.venice.piazza.common.hibernate.entity.DeploymentGroupEntity;
//import org.venice.piazza.common.hibernate.entity.JobEntity;
//import org.venice.piazza.common.hibernate.entity.LeaseEntity;
//import org.venice.piazza.common.hibernate.entity.ServiceEntity;
//import org.venice.piazza.common.hibernate.entity.ServiceJobEntity;
//import org.venice.piazza.common.hibernate.entity.UserProfileEntity;
//import org.venice.piazza.common.hibernate.entity.UserThrottlesEntity;
//
//import model.job.metadata.ResourceMetadata;
//import model.job.result.type.DataResult;
//import model.job.result.type.DeploymentResult;
//import model.job.result.type.ErrorResult;
//import model.job.result.type.FileResult;
//import model.job.result.type.JobResult;
//import model.job.result.type.TextResult;
//import model.job.type.AbortJob;
//import model.job.type.AccessJob;
//import model.job.type.DeleteServiceJob;
//import model.job.type.DescribeServiceMetadataJob;
//import model.job.type.ExecuteServiceJob;
//import model.job.type.IngestJob;
//import model.job.type.ListServicesJob;
//import model.job.type.RegisterServiceJob;
//import model.job.type.RepeatJob;
//import model.job.type.SearchMetadataIngestJob;
//import model.job.type.SearchQueryJob;
//import model.job.type.SearchServiceJob;
//import model.job.type.ServiceMetadataIngestJob;
//import model.job.type.UpdateServiceJob;
//import model.logger.AuditElement;
//import model.logger.LoggerPayload;
//import model.logger.MetricElement;
//import model.logger.Severity;
//import model.request.LogRequest;
//import model.request.PiazzaJobRequest;
//import model.request.SearchRequest;
//import model.resource.CoreResource;
//import model.resource.DBCoreResource;
//import model.resource.NumericKeyValue;
//import model.resource.RegisterService;
//import model.resource.TextKeyValue;
//import model.resource.UUID;
//import model.response.AuthResponse;
//import model.response.DeploymentGroupResponse;
//import model.response.ErrorResponse;
//import model.response.EventListResponse;
//import model.response.EventResponse;
//import model.response.EventTypeListResponse;
//import model.response.EventTypeResponse;
//import model.response.JobListResponse;
//import model.response.JobResponse;
//import model.response.ServiceIdResponse;
//import model.response.ServiceJobResponse;
//import model.response.TriggerListResponse;
//import model.response.TriggerResponse;
//import model.response.UUIDResponse;
//import model.response.UserProfileResponse;
//import model.response.WorkflowResponse;
//import model.security.authz.AuthorizationCheck;
//import model.security.authz.Permission;
//import model.security.authz.ProfileTemplate;
//import model.security.authz.UserProfile;
//import model.security.authz.UserThrottles;
//import model.service.metadata.Service;
//
///**
// * Instantiates and hydrates domain objects using reflection.
// */
//public class PojoTest {
//
//    /**
//     * A map of object types, including interfaces, and a supplier that can return an instance.
//     * For most types the same reference will be returned for multiple invocations. For simpler types, a unique/random value may
//     * be instantiated each time.
//     * These objects are used for dependency injection.
//     */
//    private Map<Class, Supplier<Object>> allObjects = new HashMap<>();
//
//    @Test
//    public void testObjects() throws
//            Exception {
//
//        registerNonTestedDependencyTypes();
//        this.allObjects.put(Service.METHOD_TYPE.class, () -> Service.METHOD_TYPE.POST);
//        this.allObjects.put(ResourceMetadata.STATUS_TYPE.class, () -> ResourceMetadata.STATUS_TYPE.ONLINE);
//        this.allObjects.put(Severity.class, () -> model.logger.Severity.WARNING);
//
//        //Make sure all the types that we want to test are registered.
//        //Order is not important.
//        //A type can be included multiple times, e.g. as a dependency of another class, without issue.
//        registerAll(
//                LogRequest.class,
//                FileResult.class,
//                SearchRequest.class,
//                PiazzaJobRequest.class,
//                UserProfile.class,
//                AuthorizationCheck.class,
//                UserThrottles.class,
//                Permission.class,
//                ProfileTemplate.class,
//                LoggerPayload.class,
//                AuditElement.class,
//                Severity.class,
//                MetricElement.class,
//                FileResult.class,
//                ErrorResult.class,
//                DataResult.class,
//                DeploymentResult.class,
//                JobResult.class,
//                TextResult.class,
//                AccessJob.class,
//                ExecuteServiceJob.class,
//                UpdateServiceJob.class,
//                AbortJob.class,
//                DeleteServiceJob.class,
//                IngestJob.class,
//                RepeatJob.class,
//                RegisterServiceJob.class,
//                SearchMetadataIngestJob.class,
//                SearchServiceJob.class,
//                DescribeServiceMetadataJob.class,
//                ServiceMetadataIngestJob.class,
//                ListServicesJob.class,
//                SearchQueryJob.class,
//                AuthResponse.class,
//                UserProfileResponse.class,
//                ServiceJobResponse.class,
//                EventTypeListResponse.class,
//                EventListResponse.class,
//                JobListResponse.class,
//                JobResponse.class,
//                ServiceIdResponse.class,
//                UUIDResponse.class,
//                ErrorResponse.class,
//                EventResponse.class,
//                DeploymentGroupResponse.class,
//                EventTypeResponse.class,
//                TriggerListResponse.class,
//                TriggerResponse.class,
//                WorkflowResponse.class,
//                CoreResource.class,
//                NumericKeyValue.class,
//                TextKeyValue.class,
//                RegisterService.class,
//                DBCoreResource.class,
//                UUID.class,
//                ApiKeyEntity.class,
//                AsyncServiceInstanceEntity.class,
//                DataResourceEntity.class,
//                DeploymentEntity.class,
//                DeploymentGroupEntity.class,
//                JobEntity.class,
//                LeaseEntity.class,
//                ServiceEntity.class,
//                ServiceJobEntity.class,
//                UserProfileEntity.class,
//                UserThrottlesEntity.class
//        );
//
//        testGettersAndSetters();
//        testConstructors();
//    }
//
//    private void testConstructors() {
//        for (Map.Entry<Class, Supplier<Object>> kvp : this.allObjects.entrySet()) {
//            if (isTestedClass(kvp.getKey()))
//                testNonDefaultConstructors(kvp.getKey());
//        }
//    }
//
//    /**
//     * Attempt invoke all non-default constructors.
//     * @param clazz
//     */
//    private void testNonDefaultConstructors(Class clazz) {
//        Constructor[] constructors = clazz.getConstructors();
//
//        for (Constructor ctor : constructors) {
//            Class[] paramTypes = ctor.getParameterTypes();
//            try {
//                Object[] paramValues = Arrays.stream(paramTypes).map(x -> allObjects.get(x).get()).toArray();
//                Object instance = ctor.newInstance(paramValues);
//            } catch (Exception ex) {
//                System.out.println("Could not insantiate " + ctor);
//            }
//        }
//    }
//
//    /**
//     * For each tested object registered in the object map, attempt to invoke every property getter and setter.
//     * This will "hydrate" all the properties of the tested objects.
//     * @throws InvocationTargetException
//     * @throws IllegalAccessException
//     */
//    private void testGettersAndSetters() throws InvocationTargetException, IllegalAccessException {
//        for (Map.Entry<Class, Supplier<Object>> kvp : allObjects.entrySet()) {
//            if (!isTestedClass(kvp.getKey()))
//                continue;
//
//            Object obj = kvp.getValue().get();
//
//            PropertyDescriptor[] properties = PropertyUtils.getPropertyDescriptors(kvp.getKey());
//            for (PropertyDescriptor prop : properties) {
//                Method setter = prop.getWriteMethod();
//                if (setter == null)
//                    continue;
//
//                //Get an instance of this property type from the object map.
//                Supplier<Object> propSupplier = this.allObjects.getOrDefault(prop.getPropertyType(), null);
//                if (propSupplier == null) {
//                    System.out.println("Could not find instance of " + prop.getPropertyType());
//                } else {
//                    Object propValue = propSupplier.get();
//                    setter.invoke(obj, propValue);
//
//                    Method getter = prop.getReadMethod();
//                    if (getter != null) {
//                        Object retrievedValue = getter.invoke(obj);
//                        if (!retrievedValue.equals(propValue))
//                            System.out.println("Setter returned different value. " + getter);
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * Dependencies that return true for {@link #isTestedClass(Class)} will be registered automatically.
//     * This method registers other types that may be needed.
//     */
//    private void registerNonTestedDependencyTypes() {
//        //For simple types we can return random values to make serialization more interesting.
//
//        this.allObjects.put(Integer.class, () -> ThreadLocalRandom.current().nextInt(0, 1000000));
//        this.allObjects.put(int.class, () -> ThreadLocalRandom.current().nextInt(0, 1000000));
//        this.allObjects.put(Long.class, () -> ThreadLocalRandom.current().nextLong(0, 1000000));
//        this.allObjects.put(long.class, () -> ThreadLocalRandom.current().nextLong(0, 1000000));
//        this.allObjects.put(Double.class, () -> ThreadLocalRandom.current().nextDouble(0.0, 100000.0));
//        this.allObjects.put(double.class, () -> ThreadLocalRandom.current().nextDouble(0.0, 100000.0));
//        this.allObjects.put(Boolean.class, () -> ThreadLocalRandom.current().nextBoolean());
//        this.allObjects.put(boolean.class, () -> ThreadLocalRandom.current().nextBoolean());
//
//        //One of the tested classes tries to parse a string as a date. So let's just return a date.
//        this.allObjects.put(String.class, () -> DateTime.now().plusMillis(ThreadLocalRandom.current().nextInt()).toString());
//        this.allObjects.put(DateTime.class, () -> DateTime.now().plusMillis(ThreadLocalRandom.current().nextInt()));
//        this.allObjects.put(List.class, () -> new ArrayList<>());
//        this.allObjects.put(Map.class, () -> new HashMap<>());
//        this.allObjects.put(Object.class, () -> new Object());
//    }
//
//    /**
//     * Adds all the objects to the object map as well as all dependencies.
//     * @param types
//     * @throws InstantiationException
//     * @throws IllegalAccessException
//     */
//    private void registerAll(Class<?>... types) throws InstantiationException, IllegalAccessException {
//        for (Class c : types) {
//            registerSelfAndDependencies(c);
//        }
//    }
//
//    private void registerSelfAndDependencies(Class clazz) throws IllegalAccessException, InstantiationException {
//        if (!this.allObjects.containsKey(clazz) && isTestedClass(clazz)) {
//            //We need to make an instance of this class.
//
//            //Instantiate the class and register it in the object collection.
//            Object instance = clazz.newInstance();
//            registerInstance(instance);
//
//            //Now register all dependencies. This includes any types used in property getter/setters and
//            //any types in public constructor signatures.
//
//            //Register the types in getters/setters.
//            PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(clazz);
//            for (PropertyDescriptor prop : descriptors) {
//                if (prop.getWriteMethod() == null)
//                    continue;
//
//                registerSelfAndDependencies(prop.getPropertyType());
//            }
//
//            //Register the types in the constructor signatures.
//            for (Class argType : Arrays.stream(clazz.getConstructors()).flatMap(x -> Arrays.stream(x.getParameterTypes())).collect(Collectors.toList())) {
//                registerSelfAndDependencies(argType);
//            }
//        }
//    }
//
//    /**
//     * Adds entries for the instance to the object map if they do not already exist.
//     * Entries are made for the concrete type as well as any interfaces that it implements.
//     * @param instance
//     */
//    private void registerInstance(Object instance) {
//        Supplier<Object> supplier = () -> instance;
//        Class clazz = instance.getClass();
//
//        if (!this.allObjects.containsKey(clazz)) {
//            this.allObjects.put(clazz, supplier);
//
//            Class[] interfaces = clazz.getInterfaces();
//            for (Class inter : interfaces) {
//                this.allObjects.putIfAbsent(inter, () -> instance);
//            }
//        }
//    }
//
//    /**
//     * Indicates whether the class should be tested.
//     *
//     * @param clazz
//     * @return
//     */
//    private boolean isTestedClass(Class clazz) {
//        Constructor[] ctors = clazz.getConstructors();
//        boolean hasDefaultCtor = Arrays.stream(ctors).anyMatch(x -> x.getParameterCount() == 0);
//
//        String fullName = clazz.getName();
//        boolean isInTestedPackage = fullName.startsWith("model.") || fullName.startsWith("org.venice.piazza.common.hibernate.entity");
//
//        return hasDefaultCtor && isInTestedPackage;
//    }
//
//}
