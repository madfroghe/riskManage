package mobile;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yubo.xuan on 17/5/12.
 */
public class OCRHelper {
    private final String LANG_OPTION = "-l";
    // private final String EOL = System.getProperty("line.separator");
    /**
     * 文件位置我防止在，项目同一路径
     */
    // private String tessPath = new File("tesseract").getAbsolutePath();

    /**
     * @param imageFile
     *            传入的图像文件
     * @return 识别后的字符串
     */
    public String recognizeText(File imageFile) throws Exception {
        /**
         * 设置输出文件的保存的文件目录
         */
        File outputFile = new File(imageFile.getParentFile(), "output");

        StringBuffer strB = new StringBuffer();
        List<String> cmd = new ArrayList<String>();
        cmd.add("/opt/local/bin/tesseract");

        cmd.add(" ");
        cmd.add(outputFile.getName());
        cmd.add(LANG_OPTION);
        // cmd.add("chi_sim");
        cmd.add("eng");

        ProcessBuilder pb = new ProcessBuilder();
        /**
         * Sets this process builder's working directory.
         */
        pb.directory(imageFile.getParentFile());
        cmd.set(1, imageFile.getName());
        pb.command(cmd);
        pb.redirectErrorStream(true);
        Process process = pb.start();
        // tesseract.exe 1.jpg 1 -l chi_sim
        // Runtime.getRuntime().exec("tesseract.exe 1.jpg 1 -l chi_sim");
        /**
         * the exit value of the process. By convention, 0 indicates normal
         * termination.
         */
        // System.out.println(cmd.toString());
        int w = process.waitFor();
        if (w == 0)// 0代表正常退出
        {
            BufferedReader in = new BufferedReader(
                            new InputStreamReader(new FileInputStream(outputFile.getAbsolutePath() + ".txt"), "UTF-8"));
            String str;

            while ((str = in.readLine()) != null) {
                strB.append(str);
            }
            in.close();
        } else {
            String msg;
            switch (w) {
                case 1:
                    msg = "Errors accessing files. There may be spaces in your image's filename.";
                    break;
                case 29:
                    msg = "Cannot recognize the image or its selected region.";
                    break;
                case 31:
                    msg = "Unsupported image format.";
                    break;
                default:
                    msg = "Errors occurred.";
            }
            throw new RuntimeException(msg);
        }
        new File(outputFile.getAbsolutePath() + ".txt").delete();
        return strB.toString();// .replaceAll("\\r", "")
    }

    /*
     * 存储验证码图片
     */
    public static void saveAsImage(String path, byte[] body) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        output.write(body, 0, body.length);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            output.writeTo(fos);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            output.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            output.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            fos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String showPicture(String path, String captcha) {
        byte[] bytes = Base64.decodeBase64(captcha);
        saveAsImage(path, bytes);
        return showPicture(path);
    }

    // 通过JOptionPane显示图片并输入验证码
    public static String showPicture(String path) {

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String captcha = JOptionPane.showInputDialog(new ImageIcon(image));
        return captcha;
    }

    public static void main(String[] args) {
        OCRHelper ocr = new OCRHelper();

        try {
            String str = ocr.recognizeText(new File("/Users/gehui/output/5a691dbcae75477fa729c2f40a9878c8"));
            System.out.print(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

