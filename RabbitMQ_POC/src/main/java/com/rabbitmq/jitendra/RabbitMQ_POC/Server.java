package com.rabbitmq.jitendra.RabbitMQ_POC;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;

public class Server 
{
    public static void main( String[] args ) throws java.io.IOException, TimeoutException
    {
    	File file = null;
    	// ...(file is initialised)...
    	byte[] fileContent = Files.readAllBytes(file.toPath());
    	
    	ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("localhost");
    	Connection connection = factory.newConnection();
    	Channel channel = connection.createChannel();
    	
    	channel.queueDeclare("javaPOCQueue", false, false, false, null);
    	String message = "Hello World from Java!";
    	channel.basicPublish("", "javaPOCQueue", null, message.getBytes());
    	System.out.println(" [x] Sent '" + message + "'");
    	
    	channel.close();
    	connection.close();
    }
}
