package client.utils;

import javax.swing.*;
import java.awt.*;

public class FontUtils {
    public static void setFont(JComponent comp, int size) {

        Font font = comp.getFont();
        font = font.deriveFont(font.getStyle(), size);
        comp.setFont(font);
    }
}

