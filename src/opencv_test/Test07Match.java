package opencv_test;

import opencv_test.constants.Const;

import org.opencv.calib3d.Calib3d;
import org.opencv.core.*;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

import org.opencv.features2d.Features2d;
import org.opencv.features2d.SIFT;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class Test07Match {

    public static void showLennaMatch() {

        Const.loadLibrary();

        try {

            Mat imageLennaOri = Imgcodecs.imread(Const.locPicture, Imgcodecs.IMREAD_GRAYSCALE);
            Mat imageLennaTun = new Mat();
            Core.flip(imageLennaOri, imageLennaTun, -1);

            MatOfKeyPoint keyPointOfLennaOri = new MatOfKeyPoint(), keyPointOfLennaTun = new MatOfKeyPoint();
            SIFT.create().detect(imageLennaOri, keyPointOfLennaOri);
            SIFT.create().detect(imageLennaTun, keyPointOfLennaTun);

            Mat keyPointLennaOri = new Mat(), keyPointLennaTun = new Mat();
            Features2d.drawKeypoints(imageLennaOri, keyPointOfLennaOri, keyPointLennaOri);
            Features2d.drawKeypoints(imageLennaTun, keyPointOfLennaTun, keyPointLennaTun);

            Mat descriptorsOri = new Mat(), descriptorsTun = new Mat();
            SIFT.create().compute(imageLennaOri, keyPointOfLennaOri, descriptorsOri);
            SIFT.create().compute(imageLennaTun, keyPointOfLennaTun, descriptorsTun);

            // Match
            DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);
            MatOfDMatch matches = new MatOfDMatch();
            matcher.match(descriptorsOri, descriptorsTun, matches);

            double maxd = 0.0; double mind = 100.0;
            for (int index = 0; index < descriptorsOri.rows(); index++) {
                double dist = matches.toArray()[index].distance;
                if (dist<mind) mind=dist;
                if (dist>maxd) maxd=dist;
            }

            List<DMatch> aryGoodMatches= new ArrayList<>();
            MatOfDMatch goodMatches = new MatOfDMatch();
            for (int index = 0; index < descriptorsOri.rows(); index++) {
                if (matches.toArray()[index].distance <= Math.max(2*mind, 0.02)) aryGoodMatches.add(matches.toArray()[index]);
            }
            goodMatches.fromList(aryGoodMatches);

            Mat imageLennaMatch = new Mat();
            MatOfByte matchesMask = new MatOfByte();
            Features2d.drawMatches(imageLennaOri, keyPointOfLennaOri, imageLennaTun, keyPointOfLennaTun,
                    goodMatches, imageLennaMatch, Scalar.all(-1), Scalar.all(-1), matchesMask, Features2d.DrawMatchesFlags_NOT_DRAW_SINGLE_POINTS);

            // Show
            HighGui.imshow("Match", imageLennaMatch);

            HighGui.waitKey();
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showLennaTransform() {

        Const.loadLibrary();

        try {

            Mat
                    imageLennaOri = Imgcodecs.imread(Const.locPicture, Imgcodecs.IMREAD_GRAYSCALE),
                    tmp = new Mat(imageLennaOri, new Rect(240, 240, 60, 50)),
                    imageLennaTun = new Mat();
            tmp.copyTo(imageLennaTun);
            Core.flip(imageLennaTun, imageLennaTun, -1);

            MatOfKeyPoint keyPointOfLennaOri = new MatOfKeyPoint(), keyPointOfLennaTun = new MatOfKeyPoint();
            SIFT.create().detect(imageLennaOri, keyPointOfLennaOri);
            SIFT.create().detect(imageLennaTun, keyPointOfLennaTun);

            Mat keyPointLennaOri = new Mat(), keyPointLennaTun = new Mat();
            Features2d.drawKeypoints(imageLennaOri, keyPointOfLennaOri, keyPointLennaOri);
            Features2d.drawKeypoints(imageLennaTun, keyPointOfLennaTun, keyPointLennaTun);

            Mat descriptorsOri = new Mat(), descriptorsTun = new Mat();
            SIFT.create().compute(imageLennaOri, keyPointOfLennaOri, descriptorsOri);
            SIFT.create().compute(imageLennaTun, keyPointOfLennaTun, descriptorsTun);

            // Start Match
            DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);
            MatOfDMatch matches = new MatOfDMatch();
            matcher.match(descriptorsOri, descriptorsTun, matches);

            double maxd = 0.0; double mind = 100.0;
            for (int index = 0; index < descriptorsOri.rows(); index++) {
                double dist = matches.toArray()[index].distance;
                if (dist<mind) mind=dist;
                if (dist>maxd) maxd=dist;
            }

            List<DMatch> aryGoodMatches = new ArrayList<>();
            MatOfDMatch goodMatches = new MatOfDMatch();
            for (int index = 0; index < descriptorsOri.rows(); index++) {
                if (matches.toArray()[index].distance <= Math.max(2*mind, 0.02)) aryGoodMatches.add(matches.toArray()[index]);
            }
            goodMatches.fromList(aryGoodMatches);

            Mat imageLennaMatch = new Mat();
            MatOfByte matchesMask = new MatOfByte();
            Features2d.drawMatches(imageLennaOri, keyPointOfLennaOri, imageLennaTun, keyPointOfLennaTun,
                    goodMatches, imageLennaMatch, Scalar.all(-1), Scalar.all(-1), matchesMask, Features2d.DrawMatchesFlags_NOT_DRAW_SINGLE_POINTS);
            // End Match

            // Start Transform
            List<Point> aryModel_pt = new ArrayList<>(), aryScene_pt = new ArrayList<>();
            MatOfPoint2f model_pt = new MatOfPoint2f(), scene_pt = new MatOfPoint2f();
            for (DMatch aryGoodMatch : aryGoodMatches) {
                aryModel_pt.add(keyPointOfLennaOri.toArray()[aryGoodMatch.queryIdx].pt);
                aryScene_pt.add(keyPointOfLennaTun.toArray()[aryGoodMatch.trainIdx].pt);
            }
            model_pt.fromList(aryModel_pt);
            scene_pt.fromList(aryScene_pt);

            Mat H = Calib3d.findHomography(model_pt, scene_pt, Calib3d.RANSAC);

            MatOfPoint2f model_corner = new MatOfPoint2f();
            List<Point> aryModel_corner = new ArrayList<>();
            aryModel_corner.add(new Point(0, 0));
            aryModel_corner.add(new Point(imageLennaTun.cols(), 0));
            aryModel_corner.add(new Point(imageLennaTun.cols(), imageLennaTun.rows()));
            aryModel_corner.add(new Point(0, imageLennaTun.rows()));
            model_corner.fromList(aryModel_corner);

            MatOfPoint2f scene_corner = new MatOfPoint2f();
            Core.perspectiveTransform(model_corner, scene_corner, H);

            Point p = new Point(imageLennaOri.cols(),0);
            scene_corner.toArray()[0].x =+ p.x;
            scene_corner.toArray()[0].y =+ p.y;
            scene_corner.toArray()[1].x =+ p.x;
            scene_corner.toArray()[1].y =+ p.y;
            scene_corner.toArray()[2].x =+ p.x;
            scene_corner.toArray()[2].y =+ p.y;
            scene_corner.toArray()[3].x =+ p.x;
            scene_corner.toArray()[3].y =+ p.y;
            Imgproc.line(imageLennaMatch, scene_corner.toArray()[0], scene_corner.toArray()[1], new Scalar(0, 0, 255), 3);
            Imgproc.line(imageLennaMatch, scene_corner.toArray()[1], scene_corner.toArray()[2], new Scalar(0, 0, 255), 3);
            Imgproc.line(imageLennaMatch, scene_corner.toArray()[2], scene_corner.toArray()[3], new Scalar(0, 0, 255), 3);
            Imgproc.line(imageLennaMatch, scene_corner.toArray()[3], scene_corner.toArray()[0], new Scalar(0, 0, 255), 3);
            // End Transform

            // Show
            HighGui.imshow("Transform", imageLennaMatch);

            HighGui.waitKey();
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        showLennaTransform();
    }

}
