package opencv_test.constants;

import org.opencv.core.Core;

public class Const {

    public static final String
            locPicture = "C:\\Users\\kdha0\\Projects\\Java\\learning\\imageLenna.png",
            defaultLibraryPath = "C:\\opencv\\build\\java\\x64\\opencv_java452.dll";

    private static int loaded = 0;

    public static void loadLibrary() {
        if (loaded != 0) return;
        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            System.load(defaultLibraryPath);
        }
        loaded = 1;
    }

}
