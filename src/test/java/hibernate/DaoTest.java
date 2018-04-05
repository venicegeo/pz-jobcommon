package hibernate;

import model.response.Pagination;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.test.util.ReflectionTestUtils;
import org.venice.piazza.common.hibernate.dao.dataresource.DataResourceDaoImpl;
import org.venice.piazza.common.hibernate.dao.deployment.DeploymentDaoImpl;
import org.venice.piazza.common.hibernate.dao.service.ServiceDaoImpl;
import org.venice.piazza.common.hibernate.entity.DataResourceEntity;
import org.venice.piazza.common.hibernate.entity.DeploymentEntity;
import org.venice.piazza.common.hibernate.entity.ServiceEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;

public class DaoTest {


    @Test
    public void testDataResourceDaoImpl() {
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Query query = Mockito.mock(Query.class);
        Mockito.when(entityManager.createNativeQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(query);
        Mockito.when(entityManager.createNativeQuery(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.getSingleResult()).thenReturn(BigInteger.ZERO);

        DataResourceDaoImpl dao = new DataResourceDaoImpl();
        ReflectionTestUtils.setField(dao, "entityManager", entityManager);
        Pagination pagination = new Pagination(100L, 2, 10, "id", "asc");

        Page<DataResourceEntity> page1 = dao.getDataResourceForUserAndKeyword("my_keyword", "my_username", pagination);
        Page<DataResourceEntity> page2 = dao.getDataResourceListByUser("my_username", pagination);
        Page<DataResourceEntity> page3 = dao.getDataResourceListByKeyword("my_keyword", pagination);
        Page<DataResourceEntity> page4 = dao.getDataResourceListByCreatedJobId("my_created_by_job_id", pagination);
        Page<DataResourceEntity> page5 = dao.getDataResourceList(pagination);

    }

    @Test
    public void testServiceDaoImpl() {
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Query query = Mockito.mock(Query.class);
        Mockito.when(entityManager.createNativeQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(query);
        Mockito.when(entityManager.createNativeQuery(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.getSingleResult()).thenReturn(BigInteger.ZERO);

        ServiceDaoImpl dao = new ServiceDaoImpl();
        ReflectionTestUtils.setField(dao, "entityManager", entityManager);
        Pagination pagination = new Pagination(100L, 2, 10, "id", "asc");

        Page<ServiceEntity> page1 = dao.getServiceListForUserAndKeyword("my_keyword", "my_username", pagination);
        Page<ServiceEntity> page2 = dao.getServiceListByUser("my_username", pagination);
        Page<ServiceEntity> page3 = dao.getServiceListByKeyword("my_keyword", pagination);
        Page<ServiceEntity> page4 = dao.getServiceList(pagination);
    }
}
