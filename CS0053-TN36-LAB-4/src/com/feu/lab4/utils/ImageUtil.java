package com.feu.lab4.utils;

import javax.swing.*;
import java.awt.*;

public class ImageUtil {
    public static ImageIcon getScaledIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

   
}
