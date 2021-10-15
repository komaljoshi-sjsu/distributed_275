package client;

import java.net.InetSocketAddress;
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
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter lower bound of latitude:");
		double lat = sc.nextDouble();
		System.out.println("Enter lower bound of longitude:");
		double lon = sc.nextDouble();
		System.out.println("Enter lower bound of elevation:");
		double ele = sc.nextDouble();
		JSONObject spatial = new JSONObject();
		spatial.put("lat", lat);
		spatial.put("lon", lon);
		spatial.put("elevation", ele);
		
		String query = spatial.toJSONString();
		
		QueryProcessorGrpc.QueryProcessorBlockingStub blockingStub = QueryProcessorGrpc.newBlockingStub(channel);
		System.out.println("Sending Request to server.....");
		
		String message = blockingStub.sendQuery(QueryRequest.newBuilder().setQuery(query).build()).getMessage();
		System.out.println("\n\nServer sent:"+message);
		sc.close();
	}

}
