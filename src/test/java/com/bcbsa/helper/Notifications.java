package com.bcbsa.helper;

public enum Notifications {

    A1CNotCompleted("A1C_2ND_HALF_TEST_NOT_COMPLTD", "It's time to submit another A1c test result"),
    OrderShipped("ORDER_SHPD_CONS", "Shipping Confirmation");

    private String notificationSubType;
    private String notificationHeaderText;

    Notifications(String notificationSubType, String notificationHeaderText) {
        this.notificationSubType = notificationSubType;
        this.notificationHeaderText = notificationHeaderText;
    }

    public static String getNotificationHeaderTextForSubType(String subType) {
        for (Notifications notification : Notifications.values()) {
            if (notification.notificationSubType.equals(subType)) {
                return notification.notificationHeaderText;
            }
        }
        return null;
    }

    public String getNotificationSubType() {
        return notificationSubType;
    }

    public String getNotificationHeaderText() {
        return notificationHeaderText;
    }
}
