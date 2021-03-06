package com.mparticle.sdk.samples;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.MessageSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AwsLambdaEndpoint implements RequestStreamHandler {

    private final MessageSerializer serializer = new MessageSerializer();

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {

        SampleExtension processor = new SampleExtension();
        Message request = serializer.deserialize(input, Message.class);
        Message response = processor.processMessage(request);
        serializer.serialize(output, response);
    }
}
