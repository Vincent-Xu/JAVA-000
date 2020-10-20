package advance.homework.week01;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Custom classloader
 */
public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) {
        Class hello2 = new HelloClassLoader().findClass("Hello");
        try {
            hello2.getDeclaredMethod("hello").invoke(hello2.newInstance());
        } catch (Exception ex) {

        }
    }

    @Override
    protected Class<?> findClass(String name) {
        try {
            String path = "F:\\WorkStation\\Java Learning\\JAVA-000\\Week_01";
            String fullPath = path + File.separator + name.replaceAll("\\.", "/") + ".xlass";
            File file = new File(fullPath);
            byte[] data = getClassFileBytes(file);
            return defineClass(name, data, 0, data.length);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public byte[] getClassFileBytes(File file) throws Exception {
        // Use directory memory (-XX:MaxDirectMemorySize)
        try (FileInputStream fis = new FileInputStream(file); FileChannel fileC = fis.getChannel();
             ByteArrayOutputStream baos = new ByteArrayOutputStream(); WritableByteChannel outC = Channels.newChannel(baos);) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            while (true) {
                int i = fileC.read(buffer);
                if (i == 0 || i == -1) {
                    break;
                }
                // 反转再读
                buffer.flip();
                outC.write(buffer);
                buffer.clear();
            }
            byte[] bytes = baos.toByteArray();
            // Encoding x=255-x
            for (int j=0, len = bytes.length; j<len; j++) {
                bytes[j] = (byte) (255-bytes[j]);
            }
            return bytes;
        }
    }

//    public byte[] readClassFile() {
//        Path path = Paths.get("F:\\WorkStation\\BaiduNetDisk\\java进阶\\Hello\\Hello.xlass");
//        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_16)) {
//            String line = null;
//            StringBuilder result = new StringBuilder();
//            while ((line = reader.readLine()) !=null){
//                byte[] bytes = line.getBytes();
//                for (int i=0, len=bytes.length;i<len;i++) {
//                    bytes[i] = (byte)(255 - bytes[i]);
//                }
//                result.append(bytes.toString());
//
//            }
//            return result.toString().getBytes();
//        } catch(IOException ex) {
//            ex.printStackTrace();
//        }
//        return new byte[0];
//    }

}
