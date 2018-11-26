package com.example.grpc;

import com.xudashu.helloworld.GreeterGrpc;
import com.xudashu.helloworld.SayHelloRequest;
import com.xudashu.helloworld.SayHelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;

public class Client {
    public static void main(String[] args) {
        ManagedChannel channel = NettyChannelBuilder
                .forAddress("127.0.0.1", 50051)
                .negotiationType(NegotiationType.PLAINTEXT)
                .build();
        GreeterGrpc.GreeterBlockingStub blockingStub = GreeterGrpc.newBlockingStub(channel);
        for (int i = 0; i < 100; i++) {

            SayHelloRequest request = SayHelloRequest.newBuilder()
                                                     .setName(" wow")
                                                     .build();
            SayHelloResponse response = blockingStub.sayHello(request);
            System.out.println(response.getMessage());
        }
        channel.shutdown();
    }
}
