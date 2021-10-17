package client;

import java.net.InetSocketAddress;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONObject;
import com.message.proto.QueryProcessorGrpc;
import com.message.proto.QueryRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolver;

import  com.message.resolver.MultiAddressNameResolverFactory;

public class ClientChannel {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		ClientChannel c = new ClientChannel();
		NameResolver.Factory nameResolverFactory = new MultiAddressNameResolverFactory(
	            new InetSocketAddress("localhost", 8080),
	            new InetSocketAddress("localhost", 8081),
	            new InetSocketAddress("localhost", 8082)
	    );

	    ManagedChannel channel = ManagedChannelBuilder.forTarget("service")
	            .nameResolverFactory(nameResolverFactory)
	            .defaultLoadBalancingPolicy("round_robin")
	            .usePlaintext()
	            .build();
		c.makeBlockingRequest(channel);
		
	}
	
	@SuppressWarnings("unchecked")
	private void makeBlockingRequest(ManagedChannel channel) {
		System.out.println("Connected to server");
		
		//Scanner sc = new Scanner(System.in);
		//System.out.println("Enter lower bound of latitude:");
		
		
		QueryProcessorGrpc.QueryProcessorBlockingStub blockingStub = QueryProcessorGrpc.newBlockingStub(channel);
		
		for(int i = 1;i<=5;i++) {
			System.out.println("Client "+i+" Sending Request to server.....");
			Random rand = new Random(); //instance of random class
	        int upperbound = 100;
	        int intNum = rand.nextInt(upperbound);
			double lat = rand.nextDouble()*intNum;
			//System.out.println("Enter lower bound of longitude:");
			intNum = rand.nextInt(upperbound);
			double lon = rand.nextDouble()*intNum;
			//
			//System.out.println("Enter lower bound of elevation:");
			intNum = rand.nextInt(upperbound);
			double ele = rand.nextDouble()*intNum;
			
			JSONObject spatial = new JSONObject();
			spatial.put("lat", lat);
			spatial.put("lon", lon);
			spatial.put("elevation", ele);
			
			String query = spatial.toJSONString();
			String message = blockingStub.sendQuery(QueryRequest.newBuilder().setQuery(query).build()).getMessage();
			System.out.println("\n\nServer sent to request  "+i+":"+message);
		}
		
		//sc.close();
	}

}
