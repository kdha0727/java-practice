package opencv_test;

import opencv_test.constants.Const;

import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Scalar;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.SIFT;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Test06SIFTKeyPoint {

    public static void main(String[] args) {

        Const.loadLibrary();

        try {

            Mat imageLenna = Imgcodecs.imread(Const.locPicture);
            Imgproc.cvtColor(imageLenna, imageLenna, Imgproc.COLOR_RGB2GRAY);

            // Start SIFT KeyPoint
            MatOfKeyPoint keyPointOfLenna = new MatOfKeyPoint();
            SIFT.create().detect(imageLenna, keyPointOfLenna);

            Mat keyPointLenna = new Mat();
            Features2d.drawKeypoints(imageLenna, keyPointOfLenna
                    , keyPointLenna, new Scalar(0, 0, 255)
                    , Features2d.DrawMatchesFlags_DRAW_RICH_KEYPOINTS);
            // End SIFT KeyPoint

            // Show
            HighGui.imshow("SIFT KeyPoint", keyPointLenna);

            HighGui.waitKey();
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
