// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: File.proto

package com.message.proto;

public final class FileProto {
  private FileProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_message_proto_FileRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_message_proto_FileRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_message_proto_FileReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_message_proto_FileReply_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nFile.proto\022\021com.message.proto\"\033\n\013FileR" +
      "equest\022\014\n\004file\030\001 \001(\014\"\033\n\tFileReply\022\016\n\006sta" +
      "tus\030\001 \001(\t2Y\n\rFileProcessor\022H\n\006upload\022\036.c" +
      "om.message.proto.FileRequest\032\034.com.messa" +
      "ge.proto.FileReply\"\000B&\n\021com.message.prot" +
      "oB\tFileProtoP\001\242\002\003HLWb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_message_proto_FileRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_message_proto_FileRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_message_proto_FileRequest_descriptor,
        new java.lang.String[] { "File", });
    internal_static_com_message_proto_FileReply_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_message_proto_FileReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_message_proto_FileReply_descriptor,
        new java.lang.String[] { "Status", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
