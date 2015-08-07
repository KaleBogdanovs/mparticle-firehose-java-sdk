package com.mparticle.sdk.samples;

import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.MessageProcessor;
import com.mparticle.sdk.model.eventprocessing.Device;
import com.mparticle.sdk.model.eventprocessing.EventProcessingContext;
import com.mparticle.sdk.model.registration.RegistrationRequest;
import com.mparticle.sdk.model.registration.RegistrationResponse;
import com.mparticle.sdk.model.registration.Setting;

import java.util.ArrayList;
import java.util.Arrays;

public class SampleMessageProcessor extends MessageProcessor {

    @Override
    public RegistrationResponse processRegistrationRequest(RegistrationRequest request) {

        RegistrationResponse response = new RegistrationResponse();

        response.name="GoogleAnalytics";
        response.description="Google Analytics Event Forwarder";

        response.settings = new ArrayList<>();
        response.settings.add(new Setting("apiKey", Setting.DataType.STRING, "API Key"));
        response.settings.add(new Setting("timezoneUtcOffset", Setting.DataType.INT, "UTC Offset"));
        response.settings.add(new Setting("enableEnhancedCommerce", Setting.DataType.BOOL, "Enable Enhanced Commerce"));

        response.supportedEvents = Arrays.asList(Event.Type.APP_EVENT);

        response.requiredIdentities = Arrays.asList(UserIdentity.Type.ANDROID_DEVICE_ID, UserIdentity.Type.IOS_IDFV);

        response.maxDataAgeHours = 24;

        return response;
    }

    @Override
    public EventProcessingResult processAppEvent(AppEvent event, EventProcessingContext context) {

//        String apiKey = context.subscription.getStringSetting("apiKey", true, null);
//        Device.DeviceType deviceType = context.device.deviceType;

        return new EventProcessingResult(event.id, EventProcessingResult.Action.PROCESSED);
    }

    @Override
    public EventProcessingResult processSessionStartEvent(SessionStartEvent event, EventProcessingContext context) {
        return new EventProcessingResult(event.id, EventProcessingResult.Action.DISCARDED, 911, "IDFA is missing");
    }
}
