package com.example.grpc;

import io.grpc.ServerInterceptors;
import io.grpc.netty.NettyServerBuilder;

import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[] args) throws Exception {
        int port = 8080;
        NettyServerBuilder
                .forPort(port)
                .addService(ServerInterceptors.intercept(new GreeterImpl()))
                .build()
                .start();
        TimeUnit.HOURS.sleep(1);
    }
}
