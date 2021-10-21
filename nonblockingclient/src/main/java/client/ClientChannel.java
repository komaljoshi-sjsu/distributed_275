package client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;

import com.google.common.util.concurrent.ListenableFuture;
import com.message.proto.QueryProcessorGrpc;
import com.message.proto.QueryProcessorGrpc.QueryProcessorFutureStub;
import com.message.proto.QueryReply;
import com.message.proto.QueryRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolver;
import io.grpc.stub.StreamObserver;

import  com.message.resolver.MultiAddressNameResolverFactory;

public class ClientChannel {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
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
	private void makeBlockingRequest(ManagedChannel channel) throws InterruptedException {
		System.out.println("Connected to server");
		
		//Scanner sc = new Scanner(System.in);
		//System.out.println("Enter lower bound of latitude:");
		
		
		long startTime = System.currentTimeMillis();
		
		QueryProcessorFutureStub nonBlockingStub2 = QueryProcessorGrpc.newFutureStub(channel);
//		StreamObserver<QueryRequest> reqObserver = nonBlockingStub.sendQuery(new StreamObserver<>() {
//			
//		});
		for(int i = 1;i<=20;i++) {
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
			
			QueryRequest req =QueryRequest.newBuilder().setQuery(query).build();
			QueryProcessorGrpc.QueryProcessorStub nonBlockingStub = QueryProcessorGrpc.newStub(channel);
			ListenableFuture<QueryReply> resp = nonBlockingStub2.sendQuery(req);
			
			resp.addListener(() -> {
				try {
					long stopTime = System.currentTimeMillis();
					System.out.println("Server gave:==>"+ resp.get().getMessage());
					System.out.println(
							"total request processing time is " + ((stopTime - startTime) / 1000.0) + " seconds");
				} catch(Exception e) {
					e.printStackTrace();
				}
			}, command -> command.run());
			
//			nonBlockingStub.sendQuery(req, new StreamObserver<QueryReply>() {
//				
//				@Override
//				public void onNext(QueryReply value) {
//					System.out.println("Response from server is:");
//					System.out.println(value.getMessage());
//					
//				}
//				
//				@Override
//				public void onError(Throwable t) {
//					System.err.println("Error Occured"+t.getMessage());
//					
//				}
//				
//				@Override
//				public void onCompleted() {
//					long stopTime = System.currentTimeMillis();
//					
//					System.out.println(
//							"total request processing time is " + ((stopTime - startTime) / 1000.0) + " seconds");
//					
//				}
//			});
			
		}
		channel.awaitTermination(2, TimeUnit.MINUTES);
		
	}

}
