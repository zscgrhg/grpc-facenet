package com.example.grpc;

import com.example.facematrix.FaceMatrix;
import com.example.facematrix.FaceTransformGrpc;
import com.google.common.io.Files;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;

import java.io.File;
import java.io.IOException;

public class FaceClient {
    public static void main(String[] args) throws IOException {
        ManagedChannel channel = NettyChannelBuilder
                .forAddress("127.0.0.1", 50051)
                .negotiationType(NegotiationType.PLAINTEXT)
                .build();
        FaceTransformGrpc.FaceTransformBlockingStub blockingStub = FaceTransformGrpc.newBlockingStub(channel);
        for (int i = 0; i < 1; i++) {

            File hx1 = new File("D:\\11010\\grpc-java-master\\grpc\\src\\main\\resources\\hx1.jpg");
            ByteString bytes1 = ByteString.copyFrom(Files.toByteArray(hx1));
            FaceMatrix.Face face = FaceMatrix.Face
                    .newBuilder()
                    .setFace(bytes1)
                    .build();
            FaceMatrix.Matrix matrix = blockingStub.getMatrix(face);
            System.out.println(matrix);
        }
        channel.shutdown();
    }
}
