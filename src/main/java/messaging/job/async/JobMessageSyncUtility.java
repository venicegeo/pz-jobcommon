/**
 * Copyright 2016, RadiantBlue Technologies, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package messaging.job.async;

import java.util.Arrays;
import java.util.concurrent.Callable;

import messaging.job.KafkaClientFactory;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.joda.time.DateTime;
import org.joda.time.Period;

/**
 * Allows for Kafka messages to create new uniquely-named Topic that will allow
 * the consumer to produce a message on that one-time Topic as a response back
 * to the initial call. This can be used to mimic a 'call-and-response' type of
 * behavior with asynchronous Kafka messages.
 * 
 * @author Russell.Orf
 * 
 */
public class JobMessageSyncUtility implements Callable<ConsumerRecord<String, String>> {
	private ProducerRecord<String, String> message;
	private Producer<String, String> producer;
	private Consumer<String, String> consumer;
	private static final int DEFAULT_TIMEOUT = 30; // Seconds
	private int timeout;

	/**
	 * Constructor, defining Kafka and message parameters.
	 * 
	 * @param kafkaHost
	 *            The Kafka host
	 * @param kafkaPort
	 *            The Kafka port
	 * @param kafkaGroup
	 *            The Kafka group
	 * @param message
	 *            The Kafka message to be sent.
	 * @param timeout
	 *            The number of seconds before the message will time out.
	 */
	public JobMessageSyncUtility(final String kafkaHost, final String kafkaPort, final String kafkaGroup,
			ProducerRecord<String, String> message, int timeout) {
		producer = KafkaClientFactory.getProducer(kafkaHost, kafkaPort);
		consumer = KafkaClientFactory.getConsumer(kafkaHost, kafkaPort, kafkaGroup);
		this.message = message;
		this.timeout = timeout;
	}

	/**
	 * Constructor, defining Kafka and message parameters. Uses default timeout
	 * of 30 seconds.
	 * 
	 * @param kafkaHost
	 *            The Kafka host
	 * @param kafkaPort
	 *            The Kafka port
	 * @param kafkaGroup
	 *            The Kafka group
	 * @param message
	 *            The Kafka message to be sent.
	 */
	public JobMessageSyncUtility(final String kafkaHost, final String kafkaPort, final String kafkaGroup,
			ProducerRecord<String, String> message) {
		this(kafkaHost, kafkaPort, kafkaGroup, message, DEFAULT_TIMEOUT);
	}

	/**
	 * Sends the Kafka message through the Producer and awaits a message back on
	 * the new Topic.
	 */
	public ConsumerRecord<String, String> call() throws Exception {
		consumer.subscribe(Arrays.asList(message.key().toString()));
		ConsumerRecords<String, String> consumerRecords = consumer.poll(10);

		// blocks until complete
		producer.send(message).get();
		producer.close();

		DateTime startTime = DateTime.now();

		boolean done = false;
		ConsumerRecord<String, String> consumerRecord = null;
		while (!done) {
			// Check for Timeout
			if ((new Period(startTime, DateTime.now())).getSeconds() > timeout) {
				consumer.close();
				System.out.println("**ASync Utility has timed out.\n\n");
				throw new Exception("The Consumer has timed out while waiting for a response.");
			}

			// Poll for messages on the newly created Topic
			consumerRecords = consumer.poll(500);
			for (ConsumerRecord<String, String> record : consumerRecords) {
				// Only one should ever be sent; close consumer.
				done = true;
				consumerRecord = record;
				consumer.close();
			}
		}

		System.out.println("Returning record: " + consumerRecord.toString() + "\n\n");
		return consumerRecord;
	}
}
