/*******************************************************************************
 * Copyright (c) 2020 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.broker.core.plugin;

import org.eclipse.kapua.service.device.call.message.kura.KuraPayload;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    private Test() {
    }

    public static void main(String argv[]) throws MqttPersistenceException, MqttException {
        String account = "kapua-sys";
        String clientId = "clientId";
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setKeepAliveInterval(60);
        options.setUserName("kapua-broker");
        options.setPassword(("kapua-password").toCharArray());
        MqttClientPersistence client2Persistence = new MemoryPersistence();
        MqttClient mqttClient = new MqttClient("tcp://localhost:1883", clientId, client2Persistence);
        mqttClient.setCallback(new MqttCallback() {

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                logger.info("Message arrived: {}", topic);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                logger.info("Delivery completed: {}", token.getTopics()[0]);
            }

            @Override
            public void connectionLost(Throwable cause) {
                logger.info("Connection lost! {}", cause);
            }
        });
        mqttClient.connect(options);
        mqttClient.publish("$EDC/kapua-sys/clientId/MQTT/BIRTH", new MqttMessage());
        String topicStr = "$EDC/" + account + "/" + clientId + "/MQTT/BIRTH";
        KuraPayload payloadKura = new KuraPayload();
        payloadKura.setBody(("Birth message").getBytes());
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(payloadKura.toByteArray());
        mqttClient.publish(topicStr, mqttMessage);
        logger.info("Published birth message");
        mqttClient.disconnect();
    }
}
