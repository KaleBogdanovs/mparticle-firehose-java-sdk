package com.mparticle.sdk.samples;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.mparticle.sdk.Logger;

public final class LambdaLoggerAdapter implements Logger {

    public LambdaLoggerAdapter(LambdaLogger logger) {
        this.logger = logger;
    }

    @Override
    public void log(String message) {
        if (logger != null) {
            logger.log(message);
        }
    }

    private final LambdaLogger logger;
}
