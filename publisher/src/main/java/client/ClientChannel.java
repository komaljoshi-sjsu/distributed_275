package client;

import java.io.File;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONObject;

import com.message.proto.FileProcessorGrpc;
import com.message.proto.FileRequest;
import com.message.proto.FileUploadObserver;
import com.message.proto.QueryProcessorGrpc;
import com.message.proto.QueryRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import gash.obs.madis.MesonetProcessor;

public class ClientChannel {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		ClientChannel c = new ClientChannel();

	    ManagedChannel channel = ManagedChannelBuilder.forTarget("service").build();
		c.makeBlockingRequest(channel);
		
	}
	
	@SuppressWarnings("unchecked")
	private void makeBlockingRequest(ManagedChannel channel) {
		System.out.println("Connected to server");
		// request observer
		
		File csvFile = MesonetProcessor.readMesoFileIntoCsv();
		
		
		FileProcessorGrpc.FileProcessorStub blockingStub = FileProcessorGrpc.newStub(channel);
		StreamObserver<FileRequest> streamObserver = blockingStub.upload(new FileUploadObserver());
		long startTime = System.currentTimeMillis();
		
		String message = blockingStub.upload(FileRequest.newBuilder().setFile(Files.readAllBytes(csvFile.toPath())).build()).getStatus();
		System.out.println("\n\nPublished request  to servers-->"+message);
		
		long stopTime = System.currentTimeMillis();
		
		System.out.println(
				"total request processing time is " + ((stopTime - startTime) / 1000.0) + " seconds");
	}

}
