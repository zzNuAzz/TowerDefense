package myUtils;

import game.Config;
import com.sun.javafx.geom.Vec2d;
import com.sun.javafx.geom.Vec2f;

import java.math.BigDecimal;

public class Coordinate {

    public static double fixAccuracy(double d) {
        BigDecimal temp = new BigDecimal(Double.toString(d));
        temp = temp.setScale(4, BigDecimal.ROUND_HALF_EVEN);
        return Double.parseDouble(temp.stripTrailingZeros().toPlainString());
    }

    public static double angle(double x1, double x2, double y1, double y2) {
        double d = Vec2d.distance(x1, y1, x2, y2);
        double cosA = (x2 - x1) / d;
        double sinA = (y2 - y1) / d;
        double angle = (Math.acos(cosA) * 180 / Math.PI);
        if (sinA > 0 && angle > 180) angle = -angle;
        if (sinA < 0 && angle < 180) angle = -angle;
        angle += 90;
        return angle;
    }
}
