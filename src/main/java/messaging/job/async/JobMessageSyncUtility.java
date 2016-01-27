package messaging.job.async;

import java.util.Arrays;
import java.util.concurrent.Callable;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import messaging.job.KafkaClientFactory;

/**
 * 
 * @author Russell.Orf
 * 
 */
public class JobMessageSyncUtility implements Callable<ConsumerRecord<String, String>> {
	
	private ProducerRecord<String, String> message;
	private Producer<String, String> producer;
	private Consumer<String, String> consumer;
	
	public JobMessageSyncUtility(final String KAFKA_HOST, final String KAFKA_PORT, 
			final String KAFKA_GROUP, ProducerRecord<String,String> msg) {
		producer = KafkaClientFactory.getProducer(KAFKA_HOST, KAFKA_PORT);
		consumer = KafkaClientFactory.getConsumer(KAFKA_HOST, KAFKA_PORT, KAFKA_GROUP);

		message = msg;
	}
	
	public ConsumerRecord<String, String> call() throws Exception {
		producer.send(message);
		consumer.subscribe(Arrays.asList(message.key().toString()));

		while(true){
			ConsumerRecords<String, String> consumerRecords = consumer.poll(1000);
			System.out.println("Checking for records; size is: " + consumerRecords.count());
			for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
				System.out.println("Returning record: " + consumerRecord.toString());				
				return consumerRecord;
			}
		}
	}	
}
