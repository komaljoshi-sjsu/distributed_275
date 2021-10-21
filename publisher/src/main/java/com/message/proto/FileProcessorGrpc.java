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
 * The file processing service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: File.proto")
public final class FileProcessorGrpc {

  private FileProcessorGrpc() {}

  public static final String SERVICE_NAME = "com.message.proto.FileProcessor";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.message.proto.FileRequest,
      com.message.proto.FileReply> getUploadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "upload",
      requestType = com.message.proto.FileRequest.class,
      responseType = com.message.proto.FileReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.message.proto.FileRequest,
      com.message.proto.FileReply> getUploadMethod() {
    io.grpc.MethodDescriptor<com.message.proto.FileRequest, com.message.proto.FileReply> getUploadMethod;
    if ((getUploadMethod = FileProcessorGrpc.getUploadMethod) == null) {
      synchronized (FileProcessorGrpc.class) {
        if ((getUploadMethod = FileProcessorGrpc.getUploadMethod) == null) {
          FileProcessorGrpc.getUploadMethod = getUploadMethod = 
              io.grpc.MethodDescriptor.<com.message.proto.FileRequest, com.message.proto.FileReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.message.proto.FileProcessor", "upload"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.message.proto.FileRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.message.proto.FileReply.getDefaultInstance()))
                  .setSchemaDescriptor(new FileProcessorMethodDescriptorSupplier("upload"))
                  .build();
          }
        }
     }
     return getUploadMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FileProcessorStub newStub(io.grpc.Channel channel) {
    return new FileProcessorStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FileProcessorBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new FileProcessorBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FileProcessorFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new FileProcessorFutureStub(channel);
  }

  /**
   * <pre>
   * The file processing service definition.
   * </pre>
   */
  public static abstract class FileProcessorImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a file
     * </pre>
     */
    public void upload(com.message.proto.FileRequest request,
        io.grpc.stub.StreamObserver<com.message.proto.FileReply> responseObserver) {
      asyncUnimplementedUnaryCall(getUploadMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getUploadMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.message.proto.FileRequest,
                com.message.proto.FileReply>(
                  this, METHODID_UPLOAD)))
          .build();
    }
  }

  /**
   * <pre>
   * The file processing service definition.
   * </pre>
   */
  public static final class FileProcessorStub extends io.grpc.stub.AbstractStub<FileProcessorStub> {
    private FileProcessorStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FileProcessorStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileProcessorStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FileProcessorStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a file
     * </pre>
     */
    public void upload(com.message.proto.FileRequest request,
        io.grpc.stub.StreamObserver<com.message.proto.FileReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUploadMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The file processing service definition.
   * </pre>
   */
  public static final class FileProcessorBlockingStub extends io.grpc.stub.AbstractStub<FileProcessorBlockingStub> {
    private FileProcessorBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FileProcessorBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileProcessorBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FileProcessorBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a file
     * </pre>
     */
    public com.message.proto.FileReply upload(com.message.proto.FileRequest request) {
      return blockingUnaryCall(
          getChannel(), getUploadMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The file processing service definition.
   * </pre>
   */
  public static final class FileProcessorFutureStub extends io.grpc.stub.AbstractStub<FileProcessorFutureStub> {
    private FileProcessorFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FileProcessorFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileProcessorFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FileProcessorFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a file
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.message.proto.FileReply> upload(
        com.message.proto.FileRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUploadMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_UPLOAD = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FileProcessorImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FileProcessorImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPLOAD:
          serviceImpl.upload((com.message.proto.FileRequest) request,
              (io.grpc.stub.StreamObserver<com.message.proto.FileReply>) responseObserver);
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

  private static abstract class FileProcessorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FileProcessorBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.message.proto.FileProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FileProcessor");
    }
  }

  private static final class FileProcessorFileDescriptorSupplier
      extends FileProcessorBaseDescriptorSupplier {
    FileProcessorFileDescriptorSupplier() {}
  }

  private static final class FileProcessorMethodDescriptorSupplier
      extends FileProcessorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FileProcessorMethodDescriptorSupplier(String methodName) {
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
      synchronized (FileProcessorGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FileProcessorFileDescriptorSupplier())
              .addMethod(getUploadMethod())
              .build();
        }
      }
    }
    return result;
  }
}
