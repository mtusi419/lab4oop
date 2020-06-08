package lab4;
import java.awt.geom.Rectangle2D;

/**
 * Ётот класс предоставл€ет общий интерфейс и операции дл€ генераторов фракталов, 
 * которые можно просматривать в Fractal Explorer.
 */
public abstract class FractalGenerator {

    /**
     * Ёта статическа€ вспомогательна€ функци€ принимает целочисленную координату 
	 * и преобразует ее в значение двойной точности, соответствующее определенному диапазону. 
	 * ќн используетс€ дл€ преобразовани€ координат пикселей в значени€ 
	 * двойной точности дл€ вычислени€ фракталов и т. ƒ.
     *
     * @param rangeMin минимальное значение диапазона с плавающей зап€той
     * @param rangeMax максимальное значение диапазона с плавающей зап€той
     *
     * @param size размер измерени€, из которого беретс€ пиксельна€ координата.
     *        Ќапример, это может быть ширина изображени€ или высота изображени€.
     *
     * @param coord координата, чтобы вычислить значение двойной точности
     *         оордината должна находитьс€ в диапазоне [0, размер].
     */
    public static double getCoord(double rangeMin, double rangeMax,
        int size, int coord) {

        assert size > 0;
        assert coord >= 0 && coord < size;

        double range = rangeMax - rangeMin;
        return rangeMin + (range * (double) coord / (double) size);
    }


    /**
     * ”станавливает указанный пр€моугольник, чтобы содержать начальный диапазон, подход€щий
     * дл€ генерируемого фрактала.
     */
    public static void getInitialRange(Rectangle2D.Double range) {};
    public static int numIterations(double x, double y) { return 0; };

    /**
     * ќбновл€ет текущий диапазон с центром в указанных координатах, 
     * а также дл€ увеличени€ или уменьшени€ с помощью указанного коэффициента масштабировани€.
     */
    public static void recenterAndZoomRange(Rectangle2D.Double range,
        double centerX, double centerY, double scale) {

        double newWidth = range.width * scale;
        double newHeight = range.height * scale;

        range.x = centerX - newWidth / 2;
        range.y = centerY - newHeight / 2;
        range.width = newWidth;
        range.height = newHeight;
    }

    /**
     * —озданный класс дл€ фрактала ћандельброта
     * **/
    public static class Mandelbrot extends FractalGenerator {
        public static final int MAX_ITERATIONS = 2000;
        public static void getInitialRange(Rectangle2D.Double rect){
            rect.x = -2;
            rect.y = -1.5;
            rect.height = 3;
            rect.width = 3;
        }

        //¬озвращает количество итераций дл€ точки (x, y), при которых очевидно, что точка не принадлежит
        // набору. ¬озвращает -1, если точка находитс€ во множестве ћандельброта
        public static int numIterations(double x, double y){
            int iterations = 0;
            Complex c = new Complex(x, y);
            Complex z = new Complex(0, 0);
            while (iterations < MAX_ITERATIONS){
                iterations++;
                z = z.step2().sum(c);
                if (z.isMoreThan(2)){
                    return iterations;
                }
            }
            return -1;
        }
    }
}
