package com.message.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * The query processing service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: Query.proto")
public final class QueryProcessorGrpc {

  private QueryProcessorGrpc() {}

  public static final String SERVICE_NAME = "com.message.proto.QueryProcessor";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.message.proto.QueryRequest,
      com.message.proto.QueryReply> getSendQueryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendQuery",
      requestType = com.message.proto.QueryRequest.class,
      responseType = com.message.proto.QueryReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.message.proto.QueryRequest,
      com.message.proto.QueryReply> getSendQueryMethod() {
    io.grpc.MethodDescriptor<com.message.proto.QueryRequest, com.message.proto.QueryReply> getSendQueryMethod;
    if ((getSendQueryMethod = QueryProcessorGrpc.getSendQueryMethod) == null) {
      synchronized (QueryProcessorGrpc.class) {
        if ((getSendQueryMethod = QueryProcessorGrpc.getSendQueryMethod) == null) {
          QueryProcessorGrpc.getSendQueryMethod = getSendQueryMethod = 
              io.grpc.MethodDescriptor.<com.message.proto.QueryRequest, com.message.proto.QueryReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.message.proto.QueryProcessor", "sendQuery"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.message.proto.QueryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.message.proto.QueryReply.getDefaultInstance()))
                  .setSchemaDescriptor(new QueryProcessorMethodDescriptorSupplier("sendQuery"))
                  .build();
          }
        }
     }
     return getSendQueryMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static QueryProcessorStub newStub(io.grpc.Channel channel) {
    return new QueryProcessorStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static QueryProcessorBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new QueryProcessorBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static QueryProcessorFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new QueryProcessorFutureStub(channel);
  }

  /**
   * <pre>
   * The query processing service definition.
   * </pre>
   */
  public static abstract class QueryProcessorImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a query
     * </pre>
     */
    public void sendQuery(com.message.proto.QueryRequest request,
        io.grpc.stub.StreamObserver<com.message.proto.QueryReply> responseObserver) {
      asyncUnimplementedUnaryCall(getSendQueryMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendQueryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.message.proto.QueryRequest,
                com.message.proto.QueryReply>(
                  this, METHODID_SEND_QUERY)))
          .build();
    }
  }

  /**
   * <pre>
   * The query processing service definition.
   * </pre>
   */
  public static final class QueryProcessorStub extends io.grpc.stub.AbstractStub<QueryProcessorStub> {
    private QueryProcessorStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QueryProcessorStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryProcessorStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QueryProcessorStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a query
     * </pre>
     */
    public void sendQuery(com.message.proto.QueryRequest request,
        io.grpc.stub.StreamObserver<com.message.proto.QueryReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendQueryMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The query processing service definition.
   * </pre>
   */
  public static final class QueryProcessorBlockingStub extends io.grpc.stub.AbstractStub<QueryProcessorBlockingStub> {
    private QueryProcessorBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QueryProcessorBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryProcessorBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QueryProcessorBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a query
     * </pre>
     */
    public com.message.proto.QueryReply sendQuery(com.message.proto.QueryRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendQueryMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The query processing service definition.
   * </pre>
   */
  public static final class QueryProcessorFutureStub extends io.grpc.stub.AbstractStub<QueryProcessorFutureStub> {
    private QueryProcessorFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QueryProcessorFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryProcessorFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QueryProcessorFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a query
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.message.proto.QueryReply> sendQuery(
        com.message.proto.QueryRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendQueryMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_QUERY = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final QueryProcessorImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(QueryProcessorImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_QUERY:
          serviceImpl.sendQuery((com.message.proto.QueryRequest) request,
              (io.grpc.stub.StreamObserver<com.message.proto.QueryReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class QueryProcessorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QueryProcessorBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.message.proto.QueryProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("QueryProcessor");
    }
  }

  private static final class QueryProcessorFileDescriptorSupplier
      extends QueryProcessorBaseDescriptorSupplier {
    QueryProcessorFileDescriptorSupplier() {}
  }

  private static final class QueryProcessorMethodDescriptorSupplier
      extends QueryProcessorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    QueryProcessorMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (QueryProcessorGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new QueryProcessorFileDescriptorSupplier())
              .addMethod(getSendQueryMethod())
              .build();
        }
      }
    }
    return result;
  }
}
