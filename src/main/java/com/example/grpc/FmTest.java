package com.example.grpc;

import com.example.facematrix.FaceMatrix;
import com.google.common.io.Files;
import com.google.protobuf.ByteString;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FmTest {
    public static void main(String[] args) throws IOException {
        File hx1 = new File("D:\\11010\\grpc-java-master\\grpc\\src\\main\\resources\\hx1.jpg");
        ByteString bytes1 = ByteString.copyFrom(Files.toByteArray(hx1));
        FaceMatrix.Face face = FaceMatrix.Face
                .newBuilder()
                .setFace(bytes1)
                .build();
        /*byte[] bytes = face.getFace().toByteArray();
        FileOutputStream fileOutputStream = new FileOutputStream(new File("hx2.jpg"));
        fileOutputStream.write(bytes);
        fileOutputStream.close();*/

        face.writeTo(new FileOutputStream("face.pb"));
        FaceMatrix.Face face1 = FaceMatrix.Face.parseFrom(new FileInputStream("face.pb"));
        byte[] bytes = face1
                .getFace()
                .toByteArray();
        FileOutputStream fileOutputStream = new FileOutputStream(new File("hx3.jpg"));
        fileOutputStream.write(bytes);
        fileOutputStream.close();

        float[] data = new float[]{1.1f, 2.2f};
        FaceMatrix.Matrix.Builder builder = FaceMatrix.Matrix.newBuilder();
        for (float datum : data) {
            builder.addMatrix(datum);
        }
        FaceMatrix.Matrix build = builder.build();
        build.writeTo(new FileOutputStream("matrix.pb"));
        FaceMatrix.Matrix matrix = FaceMatrix.Matrix.parseFrom(new FileInputStream("matrix.pb"));

        for (Float aFloat : matrix.getMatrixList()) {
            System.out.println(aFloat);
        }


    }
}
