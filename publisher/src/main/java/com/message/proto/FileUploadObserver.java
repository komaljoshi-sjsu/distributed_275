package com.message.proto;

import io.grpc.stub.StreamObserver;

public class FileUploadObserver implements StreamObserver<FileReply> {

    @Override
    public void onNext(FileReply fileUploadResponse) {
        System.out.println(
                "File upload status :: " + fileUploadResponse.getStatus()
        );
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {

    }

}
