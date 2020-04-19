public class ClassLoaderTest {
    public static void main(String[] args) throws Exception{
        MyClassLoader classLoader = new MyClassLoader("Tim's ClassLoader", "C:\\Users\\15291\\Desktop\\");
        System.out.println(classLoader); //MyClassLoader@4554617c
        System.out.println(classLoader.getParent()); //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(classLoader.getParent().getParent()); //sun.misc.Launcher$ExtClassLoader@74a14482
        System.out.println(classLoader.getParent().getParent().getParent()); //null
        Class<?> loaderClass = classLoader.loadClass("Math"); //Hello Demo!
        loaderClass.newInstance();

    }
}