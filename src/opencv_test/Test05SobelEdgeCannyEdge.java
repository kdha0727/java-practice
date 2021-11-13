package opencv_test;

import opencv_test.constants.Const;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Test05SobelEdgeCannyEdge {

    public static void main(String[] args) {

        Const.loadLibrary();

        try {
            Mat imageLenna = Imgcodecs.imread(Const.locPicture);
            Imgproc.cvtColor(imageLenna, imageLenna, Imgproc.COLOR_RGB2GRAY);

            // Start Canny Edge
            Mat cannyLenna = new Mat();
            Imgproc.Canny(imageLenna, cannyLenna, 30, 30*2.5, 3);
            // End Canny Edge

            // Start Sobel Edge
            Mat sobelLenna = new Mat();
            Mat sobelLenna_x = new Mat(), sobelLenna_y = new Mat();
            Mat sobelLenna_x_abs = new Mat(), sobelLenna_y_abs = new Mat();

            // Sobel Edge - horizontal direction
            Imgproc.Sobel(imageLenna, sobelLenna_x, CvType.CV_16S, 1, 0, 3, 1, 0);
            // Sobel Edge - vertical direction
            Imgproc.Sobel(imageLenna, sobelLenna_y, CvType.CV_16S, 0, 1, 3, 1, 0);

            //Calculating absolute value of gradients in both the direction
            Core.convertScaleAbs(sobelLenna_x, sobelLenna_x_abs);
            Core.convertScaleAbs(sobelLenna_y, sobelLenna_y_abs);

            //Calculating the resultant gradient
            Core.addWeighted(sobelLenna_x_abs, 0.5, sobelLenna_y, 0.5, 1, sobelLenna, 8);
            // End Sobel Edge
            // Show
            HighGui.imshow("Lenna", imageLenna);
            HighGui.imshow("Lenna(Canny Edge)", cannyLenna);
            HighGui.imshow("Lenna(Sobel Edge)", sobelLenna);

            HighGui.waitKey();
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
