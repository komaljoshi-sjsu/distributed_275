package client;

import com.message.proto.QueryReply;

import io.grpc.stub.StreamObserver;

class ProductCallback implements StreamObserver<QueryReply> {

	@Override
	public void onNext(QueryReply value) {
		System.out.println("Response from server is:");
		System.out.println(value.getMessage());
		
	}
	
	@Override
	public void onError(Throwable t) {
		System.err.println("Error Occured"+t.getMessage());
		
	}
	
	@Override
	public void onCompleted() {
		long stopTime = System.currentTimeMillis();
		
//		System.out.println(
//				"total request processing time is " + ((stopTime - startTime) / 1000.0) + " seconds");
		
	}
}