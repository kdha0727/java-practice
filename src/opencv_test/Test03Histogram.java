package opencv_test;

import opencv_test.constants.Const;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;

import java.util.List;
import java.util.ArrayList;

public class Test03Histogram {

    public static String locPicture = Const.locPicture;

    public static void showRGBHist() {

        Const.loadLibrary();

        Mat image = Imgcodecs.imread(locPicture);

        List<Mat> bgrPlanes = new ArrayList<>();
        Core.split(image, bgrPlanes);

        int histSize = 256;

        float[] range = {0, 256};
        MatOfFloat histRange = new MatOfFloat(range);
        Mat bHist = new Mat(), gHist = new Mat(), rHist = new Mat();
        Imgproc.calcHist(bgrPlanes, new MatOfInt(0), new Mat(),
                bHist, new MatOfInt(histSize), histRange, false);
        Imgproc.calcHist(bgrPlanes, new MatOfInt(1), new Mat(),
                gHist, new MatOfInt(histSize), histRange, false);
        Imgproc.calcHist(bgrPlanes, new MatOfInt(2), new Mat(),
                rHist, new MatOfInt(histSize), histRange, false);

        int histW = 512, histH = 400;
        int binW = (int) Math.round((double) histW / histSize);

        Mat imageList = new Mat(histH, histW, CvType.CV_8UC3, new Scalar(0, 0, 0));

        Core.normalize(bHist, bHist, 0, imageList.rows(), Core.NORM_MINMAX);
        Core.normalize(gHist, gHist, 0, imageList.rows(), Core.NORM_MINMAX);
        Core.normalize(rHist, rHist, 0, imageList.rows(), Core.NORM_MINMAX);

        float[] bHistData = new float[(int) (bHist.total() * bHist.channels())];
        bHist.get(0, 0, bHistData);
        float[] gHistData = new float[(int) (gHist.total() * gHist.channels())];
        gHist.get(0, 0, gHistData);
        float[] rHistData = new float[(int) (rHist.total() * rHist.channels())];
        rHist.get(0, 0, rHistData);

        for( int i = 1; i < histSize; i++ ) {
            Imgproc.line(imageList,
                    new Point(binW * (i - 1), histH - Math.round(bHistData[i - 1])),
                    new Point(binW * (i), histH - Math.round(bHistData[i])),
                    new Scalar(255, 0, 0), 1);
            Imgproc.line(imageList,
                    new Point(binW * (i - 1), histH - Math.round(gHistData[i - 1])),
                    new Point(binW * (i), histH - Math.round(gHistData[i])),
                    new Scalar(0, 255, 0), 1);
            Imgproc.line(imageList,
                    new Point(binW * (i - 1), histH - Math.round(rHistData[i - 1])),
                    new Point(binW * (i), histH - Math.round(rHistData[i])),
                    new Scalar(0, 0, 255), 1);
        }
        // Histogram End

        // Show
        HighGui.imshow("image", image);
        HighGui.imshow("imageHist", imageList);

    }

    static public void showGrayHist() {

        Const.loadLibrary();

        Mat imageGray = Imgcodecs.imread(locPicture);
        Imgproc.cvtColor(imageGray, imageGray, Imgproc.COLOR_RGB2GRAY);

        // Histogram Start
        List<Mat> bgrPlanes = new ArrayList<>();
        Core.split(imageGray, bgrPlanes);

        int histSize = 256;
        boolean accumulate = false;

        float[] range = {0, 256};
        MatOfFloat histRange = new MatOfFloat(range);
        Mat iHist = new Mat();
        Imgproc.calcHist(bgrPlanes, new MatOfInt(0), new Mat(),
                iHist, new MatOfInt(histSize), histRange, accumulate);

        int histW = 512, histH = 400;
        int binW = (int) Math.round((double) histW / histSize);

        Mat imageHist = new Mat(histH, histW, CvType.CV_8UC3, new Scalar(0, 0, 0));

        Core.normalize(iHist, iHist, 0, imageHist.rows(), Core.NORM_MINMAX);

        float[] iHistData = new float[(int) (iHist.total() * iHist.channels())];
        iHist.get(0, 0, iHistData);

        for( int i = 1; i < histSize; i++ ) {
            Imgproc.line(imageHist
                    ,new Point(binW * (i - 1), histH - Math.round(iHistData[i - 1]))
                    ,new Point(binW * (i), histH - Math.round(iHistData[i]))
                    ,new Scalar(255, 255, 255), 1);
        }
        // Histogram End

        // Show
        HighGui.imshow("LennaGray", imageGray);
        HighGui.imshow("LennaGrayHist", imageHist);


    }

    public static void main(String[] args) {
        showGrayHist();
        showRGBHist();
        HighGui.waitKey();
    }

}
