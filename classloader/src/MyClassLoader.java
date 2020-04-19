import java.io.*;

public class MyClassLoader extends ClassLoader {
    private String name;
    private String path;

    public MyClassLoader(String name, String path) {
        this.name = name;
        this.path = path;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = loadClassData(name);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassData(String name) {
        name = path + name + ".class";
        InputStream in = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            in = new FileInputStream(new File(name));
            int i;
            while ((i = in.read()) != -1){
                byteArrayOutputStream.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}