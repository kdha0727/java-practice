package opencv_test;

import opencv_test.constants.Const;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class Test04EqualizationAndBinarization {

    public static void showEqualizedGrayImage() {

        Mat imageGray = Imgcodecs.imread(Const.locPicture);
        Imgproc.cvtColor(imageGray, imageGray, Imgproc.COLOR_RGB2GRAY);

        Mat equalizedGrayImage = new Mat();
        Imgproc.equalizeHist(imageGray, equalizedGrayImage);
        HighGui.imshow("Equalization", equalizedGrayImage);

    }

    public static void showBinarizationGrayImage() {

        Mat imageGray = Imgcodecs.imread(Const.locPicture);
        Imgproc.cvtColor(imageGray, imageGray, Imgproc.COLOR_RGB2GRAY);

        Mat binarizationGrayImage = new Mat();
        Imgproc.threshold(imageGray, binarizationGrayImage, 127, 255, Imgproc.THRESH_BINARY);
        HighGui.imshow("Binarization", binarizationGrayImage);

    }

    public static void main(String[] args) {
        Const.loadLibrary();
        showBinarizationGrayImage();
        showEqualizedGrayImage();
        HighGui.waitKey();
        System.exit(0);
    }

}
