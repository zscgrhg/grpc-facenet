package com.example.grpc;


import com.xudashu.helloworld.GreeterGrpc;
import com.xudashu.helloworld.SayHelloRequest;
import com.xudashu.helloworld.SayHelloResponse;
import io.grpc.stub.StreamObserver;

public class GreeterImpl extends GreeterGrpc.GreeterImplBase {
    @Override
    public void sayHello(SayHelloRequest request, StreamObserver<SayHelloResponse> responseObserver) {
        SayHelloResponse response = SayHelloResponse
                .newBuilder()
                .setMessage("Hallo " + request.getName())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}


