package opencv_test;

import opencv_test.constants.Const;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Test05GUI {

    public static void main(String[] args) {

        Const.loadLibrary();

        try {

            Mat imageLenna = Imgcodecs.imread(Const.locPicture);
            Imgproc.cvtColor(imageLenna, imageLenna, Imgproc.COLOR_RGB2GRAY);

            // Start Canny Edge
            Mat cannyLenna = new Mat();
            Imgproc.Canny(imageLenna, cannyLenna, 30, 30*2.5, 3);
            // End Canny Edge

            // Show
            JFrame window = HighGui.createJFrame("Lenna(feat.MouseEvent)", 1);
            window.setBounds(100, 100, 518, 541);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.getContentPane().setLayout(null);
            window.setVisible(true);

            // 선택된 영역
            JLabel selectedImgLabel = new JLabel();
            selectedImgLabel.setBounds(0, 0, 512, 512);
            window.getContentPane().add(selectedImgLabel);

            // 이미지
            Image img = HighGui.toBufferedImage(imageLenna);
            JLabel imgLabel = new JLabel(new ImageIcon(img));
            imgLabel.setBounds(0, 0, 512, 512);
            imgLabel.addMouseListener(new MouseListener() {
                int startX = 0, startY = 0;
                @Override
                public void mousePressed(MouseEvent e) {
                    startX = e.getX();
                    startY = e.getY();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    Rect box = new Rect();
                    box.x = startX; box.y = startY;
                    box.width = e.getX() - startX; box.height = e.getY() - startY;

                    Imgproc.Canny(new Mat(imageLenna, box), cannyLenna, 30, 30*2.5, 3);
                    Image img = HighGui.toBufferedImage(cannyLenna);
                    selectedImgLabel.setIcon(new ImageIcon(img));
                    selectedImgLabel.setBounds(box.x, box.y, box.width, box.height);
                    window.repaint();
                }

                @Override public void mouseExited(MouseEvent e) {/*Nothing...*/}
                @Override public void mouseEntered(MouseEvent e) {/*Nothing...*/}
                @Override public void mouseClicked(MouseEvent e) {/*Nothing...*/}
            });
            window.getContentPane().add(imgLabel);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
