package com.message;

import com.message.proto.QueryReply;
import com.message.proto.QueryRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.message.proto.QueryProcessorGrpc.QueryProcessorImplBase;

import io.grpc.stub.StreamObserver;
import server.ServerChannel;

public class QueryProcessor extends QueryProcessorImplBase {
	
	ServerChannel server;
	int serverNum;
	public QueryProcessor(ServerChannel server, int serverNum) {
		this.server = server;
		this.serverNum = serverNum;
	}
	
	@Override
	public void sendQuery(QueryRequest request, StreamObserver<QueryReply> responseObserver) {
		QueryReply reply = null;
		System.out.println("Server "+serverNum+" is processing the request...");
		try {
			String reqString = request.getQuery();
			JSONParser parser = new JSONParser(); 
			JSONObject req = (JSONObject) parser.parse(reqString);

			JSONArray response = server.searchCsv(req);
			reply = QueryReply.newBuilder().setMessage(response.toJSONString()).build();
		} catch(ParseException pe) {
			reply = QueryReply.newBuilder().setMessage("Failed to parse query sent by client").build();
		}
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

}
