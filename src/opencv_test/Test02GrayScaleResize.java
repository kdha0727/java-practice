package opencv_test;

import opencv_test.constants.Const;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Test02GrayScaleResize {

    public static void main(String[] args) {

        Const.loadLibrary();

        try {

            Mat image = Imgcodecs.imread(Const.locPicture);
            Mat imageGray = new Mat();
            Mat imageResize = new Mat();

            Imgproc.cvtColor(image, imageGray, Imgproc.COLOR_RGB2GRAY);

            Size size = new Size(256, 128);
            Imgproc.resize(image, imageResize, size);

            HighGui.imshow("Sample", image);
            HighGui.imshow("SampleGray", imageGray);
            HighGui.imshow("SampleSize", imageResize);
            HighGui.waitKey();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
