
package jcm2606.thaumicmachina.core.helper;

public class ArrayHelper {

    public static <T> boolean contains(T[] array, T value) {
        boolean b = false;
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                T entry = array[i];
                if (!entry.equals(value)) continue;
                b = true;
                break;
            }
        }
        return b;
    }
}
