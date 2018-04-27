package com.bcbsa.helper;

public class OrderShippedNotificationBody extends NotificationAPIBody {

    private AdditionalData additionalData;

    public AdditionalData getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(AdditionalData additionalData) {
        this.additionalData = additionalData;
    }
}

