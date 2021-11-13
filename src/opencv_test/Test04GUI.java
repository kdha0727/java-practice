package opencv_test;

import opencv_test.constants.Const;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Test04GUI {

    public static void main(String[] args) {

        Const.loadLibrary();

        try {

            Mat imageGrayLenna = Imgcodecs.imread(Const.locPicture);
            Imgproc.cvtColor(imageGrayLenna, imageGrayLenna, Imgproc.COLOR_RGB2GRAY);
            Mat imageBinarizationGrayLenna = new Mat();

            // Binarization Start
            Imgproc.threshold(imageGrayLenna, imageBinarizationGrayLenna
                    , 127, 255, Imgproc.THRESH_BINARY);
            // Binarization End

            // Show
            JFrame trackbarWindow = new JFrame("BinarizationGrayWithTrackbar");

            trackbarWindow.setBounds(100, 100, 528, 582);
            trackbarWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            trackbarWindow.getContentPane().setLayout(null);

            // 이미지
            Image img = HighGui.toBufferedImage(imageBinarizationGrayLenna);
            JLabel imgLabel = new JLabel(new ImageIcon(img));
            imgLabel.setBounds(0, 31, 512, 512);

            trackbarWindow.getContentPane().add(imgLabel);

            // 임계값 텍스트
            JLabel levelLabel = new JLabel("임계값 : " + 127);
            levelLabel.setBounds(10, 10, 75, 15);
            trackbarWindow.getContentPane().add(levelLabel);

            // 트랙바
            JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 255, 127);
            slider.setBounds(86, 5, 410, 26);
            slider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    JSlider source = (JSlider)e.getSource();
                    int level = source.getValue();
                    //System.out.println(level);
                    // Binarization Start
                    Imgproc.threshold(imageGrayLenna, imageBinarizationGrayLenna
                            ,level, 255, Imgproc.THRESH_BINARY);
                    // Binarization End
                    Image img = HighGui.toBufferedImage(imageBinarizationGrayLenna);
                    levelLabel.setText("임계값 : " + level);
                    imgLabel.setIcon(new ImageIcon(img));
                    trackbarWindow.repaint();
                }
            });
            trackbarWindow.getContentPane().add(slider, BorderLayout.NORTH);
            trackbarWindow.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
