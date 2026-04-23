
package jcm2606.thaumicmachina.core.helper;

public class ArrayHelper {

    public static <T> boolean contains(T[] array, T value) {
        if (array != null) {
            for (T entry : array) {
                if (entry.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }
}
