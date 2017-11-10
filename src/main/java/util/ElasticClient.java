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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ElasticClient {
	@Value("${vcap.services.pz-elasticsearch.credentials.transportClientPort}")
	private Integer elasticSearchPort;
	@Value("${vcap.services.pz-elasticsearch.credentials.hostname}")
	private String elasticSearchHost;
	@Value("${elasticsearch.clustername}")
	private String clustername;

	/**
	 * Spring bean for injecting elasticsearch client.
	 * 
	 * @return Elasticsearch client
	 * @throws UnknownHostException
	 */
	@Bean
	public Client getClient() throws UnknownHostException {
		Settings settings = Settings.builder().put("cluster.name", clustername).build();
		TransportClient transportClient = new PreBuiltTransportClient(settings);
		// Check if the ES Host property has multiple Hosts.
		if (elasticSearchHost.contains(";")) {
			// (In the form of "host;host2;host3")
			//
			// Multiple hosts. Split the string and add each host.
			List<String> hosts = Arrays.asList(elasticSearchHost.split(";"));
			for (String host : hosts) {
				transportClient.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(host, elasticSearchPort)));
			}
		} else {
			// (A single host)
			//
			// Single host. Add this one host.
			transportClient
					.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(elasticSearchHost, elasticSearchPort)));
		}
		return transportClient;
	}
}
