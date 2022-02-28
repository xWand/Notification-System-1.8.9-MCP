package net.flow.ui.notification;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;

/**
 * @author xWand
 */
public class Notification {

    private static Minecraft mc = Minecraft.getMinecraft();

    public enum NotificationType {
        INFO, WARNING, ERROR
    }

    private String title, info;
    private NotificationType type;
    private long start, duration;
    private final long in = 250, out = 250;
    private int animation;

    public Notification(String title, String info, NotificationType type, long duration) {
        this.title = title;
        this.info = info;
        this.type = type;
        this.duration = duration;
        this.animation = 0;
    }

    public void start() {
        start = System.currentTimeMillis();
    }

    public boolean isShown() {
        return System.currentTimeMillis() < start + duration + in + out;
    }

    public boolean isAnimatingIn() {
        return System.currentTimeMillis() - start - duration - out < -duration;
    }

    public boolean isAnimatingOut() {
        return System.currentTimeMillis() - start - duration - out > 0;
    }

    public void draw() {
        ScaledResolution sr = new ScaledResolution(mc);

        int left = sr.getScaledWidth();
        int right = sr.getScaledWidth();
        int top = sr.getScaledHeight() - 70;
        int bottom = sr.getScaledHeight() - 30;

        int color;

        if (type.equals(NotificationType.ERROR)) {
            if (!title.contains("ERROR")) title = "ERROR: " + title;
            color = Color.RED.getRGB();
        } else if (type.equals(NotificationType.WARNING)) {
            if (!title.contains("WARNING")) title = "WARNING: " + title;
            color = Color.ORANGE.getRGB();
        } else {
            if (!title.contains("INFO")) title = "INFO: " + title;
            color = Color.WHITE.getRGB();
        }

        int stringWidth_title = mc.fontRendererObj.getStringWidth(title);
        int stringWidth_info = mc.fontRendererObj.getStringWidth(info);

        if (isAnimatingIn()) {
            if (animation < Math.max(stringWidth_info, stringWidth_title) + 10) animation++;
        } else if (isAnimatingOut()) {
            if (animation > 5) animation--;
        } else animation = Math.max(stringWidth_info, stringWidth_title) + 10;

        Gui.drawRect(left - animation - 5 - 1, top - 1, right + 11 + animation, bottom + 1, Color.black.getRGB());
        Gui.drawRect(left - animation - 5, top, right + 10 + animation, bottom, new Color(0, 147, 199).getRGB());
        mc.fontRendererObj.drawString(title, left - animation , top + 5, color);
        mc.fontRendererObj.drawString(info, left - animation, bottom - 15, -1);
    }
}
