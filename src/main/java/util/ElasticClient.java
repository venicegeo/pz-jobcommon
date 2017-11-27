package util;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class ElasticClient {
	@Value("${elasticsearch.transportClientPort}")
	private Integer elasticSearchPort;
	@Value("${vcap.services.pz-elasticsearch.credentials.host}")
	private String elasticSearchHost;
	@Value("${vcap.services.pz-elasticsearch.credentials.clusterId}")
	private String clustername;
	@Value("${vcap.services.pz-elasticsearch.credentials.username}")
	private String elasticUsername;
	@Value("${vcap.services.pz-elasticsearch.credentials.password}")
	private String elasticPassword;

	private final Logger LOGGER = LoggerFactory.getLogger(PiazzaLogger.class);
	
	/**
	 * Spring bean for injecting elasticsearch client.
	 * 
	 * @return Elasticsearch client
	 * @throws UnknownHostException
	 */
	@Bean
	public Client getClient() throws UnknownHostException {

		LOGGER.info(String.format("elasticSearchPort: %s,  elasticSearchHost: %s, clustername: %s, elasticUsername: %s, elasticPassword: %s", elasticSearchPort, elasticSearchHost, clustername, elasticUsername, elasticPassword));
		
		String elasticCredentials = String.format("%s:%s", elasticUsername, elasticPassword);
		Settings settings = Settings.builder().put("cluster.name", clustername).put("xpack.security.user", elasticCredentials).build();

		//TransportClient transportClient = new PreBuiltTransportClient(settings);
		TransportClient transportClient = new PreBuiltXPackTransportClient(settings);
				
		// Check if the ES Host property has multiple Hosts.
		if (elasticSearchHost.contains(";")) {
			// (In the form of "host;host2;host3")
			// Multiple hosts. Split the string and add each host.
			List<String> hosts = Arrays.asList(elasticSearchHost.split(";"));
			for (String host : hosts) {
				String hostName = String.format("%s.%s", clustername, host); 
				transportClient.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(hostName, elasticSearchPort)));
			}
		} else {
			// (A single host)
			//
			// Single host. Add this one host.
			transportClient.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(elasticSearchHost, elasticSearchPort)));
		}
		return transportClient;
	}
}
