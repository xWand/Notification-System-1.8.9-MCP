# Notification System 1.8.9 MCP

Simple system which adds notifications to your client. I made this for a PvP client called "Flow".

Usage:
1. Import the files into your client
2. Add a notification using NotificationManager

Example:
```java
Notification notification = new Notification("Module enabled", "Scoreboard was enabled.", Notificaion.NotificationType.INFO, 1000L);
NotificationManager.addNotification(notification);
```
