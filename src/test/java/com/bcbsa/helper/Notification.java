package com.bcbsa.helper;

public class Notification {

    private String headerText;
    private String bodyText;
    private String notificationTimeLapsed;

    public Notification(String headerText, String bodyText, String notificationTimeLapsed) {
        this.headerText = headerText;
        this.bodyText = bodyText;
        this.notificationTimeLapsed = notificationTimeLapsed;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public String getNotificationTimeLapsed() {
        return notificationTimeLapsed;
    }

    public void setNotificationTimeLapsed(String notificationTimeLapsed) {
        this.notificationTimeLapsed = notificationTimeLapsed;
    }
}
