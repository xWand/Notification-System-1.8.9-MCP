package net.flow.ui.notification;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xWand
 */
public class NotificationManager {

    public static final Queue<Notification> pendingNotifications = new LinkedBlockingQueue<>();
    private static Notification currentNotification = null;

    public static void addNotification(Notification notification) {
        pendingNotifications.add(notification);
        check();
    }

    public static void check() {
        if (currentNotification == null || !currentNotification.isShown()) {
            currentNotification = pendingNotifications.poll();

            if (currentNotification == null) return;

            currentNotification.start();
        }

        if (currentNotification.isShown()) currentNotification.draw();
    }
}
