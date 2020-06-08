package lab4;
import java.awt.geom.Rectangle2D;

/**
 * ���� ����� ������������� ����� ��������� � �������� ��� ����������� ���������, 
 * ������� ����� ������������� � Fractal Explorer.
 */
public abstract class FractalGenerator {

    /**
     * ��� ����������� ��������������� ������� ��������� ������������� ���������� 
	 * � ����������� �� � �������� ������� ��������, ��������������� ������������� ���������. 
	 * �� ������������ ��� �������������� ��������� �������� � �������� 
	 * ������� �������� ��� ���������� ��������� � �. �.
     *
     * @param rangeMin ����������� �������� ��������� � ��������� �������
     * @param rangeMax ������������ �������� ��������� � ��������� �������
     *
     * @param size ������ ���������, �� �������� ������� ���������� ����������.
     *        ��������, ��� ����� ���� ������ ����������� ��� ������ �����������.
     *
     * @param coord ����������, ����� ��������� �������� ������� ��������
     *        ���������� ������ ���������� � ��������� [0, ������].
     */
    public static double getCoord(double rangeMin, double rangeMax,
        int size, int coord) {

        assert size > 0;
        assert coord >= 0 && coord < size;

        double range = rangeMax - rangeMin;
        return rangeMin + (range * (double) coord / (double) size);
    }


    /**
     * ������������� ��������� �������������, ����� ��������� ��������� ��������, ����������
     * ��� ������������� ��������.
     */
    public static void getInitialRange(Rectangle2D.Double range) {};
    public static int numIterations(double x, double y) { return 0; };

    /**
     * ��������� ������� �������� � ������� � ��������� �����������, 
     * � ����� ��� ���������� ��� ���������� � ������� ���������� ������������ ���������������.
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
     * ��������� ����� ��� �������� ������������
     * **/
    public static class Mandelbrot extends FractalGenerator {
        public static final int MAX_ITERATIONS = 2000;
        public static void getInitialRange(Rectangle2D.Double rect){
            rect.x = -2;
            rect.y = -1.5;
            rect.height = 3;
            rect.width = 3;
        }

        //���������� ���������� �������� ��� ����� (x, y), ��� ������� ��������, ��� ����� �� �����������
        // ������. ���������� -1, ���� ����� ��������� �� ��������� ������������
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
