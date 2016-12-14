/**
 * Created by Jacob on 12/14/2016.
 * Utility class with useful methods to be used for a wide variety of things
 */
public class Util {

    private Util(){}

    /**
     * Generic System.out print handling for Arrays
     * @param array the Array to print
     * @param <T> the type of the objects in array
     */
    public static <T> void printar(T[] array){
        String out = "{";
        for (T elem : array){
           out += elem.toString() + ", ";
        }
        out = out.substring(0, out.length()-2);
        out += "}";
        System.out.println(out);
    }
}