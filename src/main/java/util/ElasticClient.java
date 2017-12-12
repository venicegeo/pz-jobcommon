package util;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
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
	private String clusterId;
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

		String elasticCredentials = String.format("%s:%s", elasticUsername, elasticPassword);

        // Build the settings for our client.
        Settings settings = Settings.builder()
            .put("client.transport.nodes_sampler_interval", "5s")
            .put("client.transport.sniff", false)
            .put("transport.tcp.compress", true)
            .put("cluster.name", clusterId)
            .put("xpack.security.transport.ssl.enabled", true)
            .put("request.headers.X-Found-Cluster", clusterId)
            //.put("xpack.security.user", elasticCredentials)
            .put("xpack.security.user", elasticUsername)
            .build();
        
        
        TransportClient transportClient = new PreBuiltXPackTransportClient(settings);
		//TransportClient transportClient = new PreBuiltTransportClient(settings);
        
		try {
			transportClient.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(elasticSearchHost, elasticSearchPort)));
		} catch (Exception e) {
			LOGGER.info("====================================== Unable to get the host" + e.getMessage());
		}
        
		
		/* Add multiple host support after fixing connection issues for version 5.4
		// Check if the ES Host property has multiple Hosts.
		if (elasticSearchHost.contains(";")) {
			// (In the form of "host;host2;host3")
			// Multiple hosts. Split the string and add each host.
			List<String> hosts = Arrays.asList(elasticSearchHost.split(";"));
			for (String host : hosts) {
				//String hostName = String.format("%s.%s", clusterName, host);
				LOGGER.info("elastic adding a multiple --hosts: " + host + " --Port: " + elasticSearchPort);
				transportClient.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(host, elasticSearchPort)));
			}
		} else {
			// (A single host)
			//
			// Single host. Add this one host.
			LOGGER.info("elastic search adding a single --host: " + elasticSearchHost + " --Port: " + elasticSearchPort);
			transportClient.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(elasticSearchHost, elasticSearchPort)));
		}
		*/
		
		return transportClient;

	}
}
