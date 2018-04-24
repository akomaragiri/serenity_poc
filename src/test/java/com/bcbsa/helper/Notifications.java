package com.bcbsa.helper;

public enum Notifications {

    A1CNotCompleted("A1C_2ND_HALF_TEST_NOT_COMPLTD", "It's time to submit another A1c test result");

    private String notificationSubType;
    private String notificationMessage;

    Notifications(String notificationSubType, String notificationMessage) {
        this.notificationSubType = notificationSubType;
        this.notificationMessage = notificationMessage;
    }

    public static String getNotificationMessageForSubType(String subType) {
        for (Notifications notification : Notifications.values()) {
            if (notification.notificationSubType.equals(subType)) {
                return notification.notificationMessage;
            }
        }
        return null;
    }

    public String getNotificationSubType() {
        return notificationSubType;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }
}
