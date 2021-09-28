package client;

import java.util.Scanner;

import org.json.simple.JSONObject;
import com.message.proto.QueryProcessorGrpc;
import com.message.proto.QueryRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ClientChannel {
	
	public static void main(String[] args) {
		ClientChannel c = new ClientChannel();
		c.makeBlockingRequest();
		
	}
	
	private void makeBlockingRequest() {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
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
	}

}
