package com.lls.sms.utils;

import javax.swing.*;
import java.awt.*;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.utils
 * @ClassName: DimensionUtil
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/6/1 15:02
 */

public class DimensionUtil {

    private DimensionUtil() {
    }

    public static Rectangle getBounds() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(new JFrame().getGraphicsConfiguration());
        Rectangle rectangle = new Rectangle(screenInsets.left - 300, screenInsets.top - 300
                , screenSize.width - screenInsets.left - screenInsets.right - 200,
                screenSize.height - screenInsets.top - screenInsets.bottom - 200);
        return rectangle;
    }

}
