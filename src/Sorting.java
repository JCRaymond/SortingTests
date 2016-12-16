import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jacob on 12/14/2016.
 * Class with a variety of sorting algorithms, cannot be instantiated
 */
public class Sorting {

    private Sorting() {
    }

    //region Utilties

    /**
     * Swaps the objects at position p1 and p2 in the list if the object at p1 is "greater" than the object at p2
     *
     * @param list the ArrayList containing the values
     * @param p1   the position of the first object
     * @param p2   the position of the second object
     * @param <T>  the type of the objects in list
     * @return     the list with the two values swapped (modifies original list)
     */
    private static <T extends Comparable<T>> ArrayList<T> swapif(ArrayList<T> list, int p1, int p2) {
        if (list.get(p1).compareTo(list.get(p2)) == 1) {
            T elem = list.get(p1);
            list.set(p1, list.get(p2));
            list.set(p2, elem);
        }
        return list;
    }

    //endregion

    //region Bubble Sort

    /**
     * Uses the bubble sort algorithm to sort a generic ArrayList
     *
     * @param list the ArrayList to sort
     * @param <T>  the type of the objects in list
     * @return     the sorted list (modifies original list)
     */
    public static <T extends Comparable<T>> ArrayList<T> bubblesort(ArrayList<T> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                swapif(list, j, j+1);
            }
        }
        return list;
    }

    /**
     * Uses the bubble sort algorithm to sort a generic Array
     *
     * @param list the Array to sort
     * @param <T>  the type of the objects in list
     * @return     the sorted list (modifies original list)
     */
    public static <T extends Comparable<T>> T[] bubblesort(T[] list) {
        return bubblesort(new ArrayList<>(Arrays.asList(list))).toArray(list);
    }

    //endregion

    //region Insert Sort

    /**
     * Uses the insert sort algorithm to sort a generic ArrayList
     *
     * @param list the ArrayList to sort
     * @param <T>  the type of the objects in list
     * @return     the sorted list (modifies original list)
     */
    public static <T extends Comparable<T>> ArrayList<T> insertsort(ArrayList<T> list) {
        for (int i = 1; i < list.size(); i++) {
            T elem = list.get(i);
            int newIndex = i - 1;
            while (newIndex >= 0 && list.get(newIndex).compareTo(elem) == 1) {
                list.set(newIndex + 1, list.get(newIndex));
                newIndex--;
            }
            list.set(newIndex + 1, elem);
        }
        return list;
    }

    /**
     * Uses the insert sort algorithm to sort a generic Array
     *
     * @param list the Array to sort
     * @param <T>  the type of the objects in list
     * @return     the sorted list (modifies original list)
     */
    public static <T extends Comparable<T>> T[] insertsort(T[] list) {
        return insertsort(new ArrayList<>(Arrays.asList(list))).toArray(list);
    }

    //endregion

    //region Selection Sort

    /**
     * Uses the selection sort algorithm to sort a generic ArrayList
     *
     * @param list the ArrayList to sort
     * @param <T>  the type of the objects in list
     * @return     the sorted list (modifies original list)
     */
    public static <T extends Comparable<T>> ArrayList<T> selectsort(ArrayList<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int lowestPos = i;
            T lowestElem = list.get(lowestPos);
            for (int j = i + 1; j < list.size(); j++) {
                if (lowestElem.compareTo(list.get(j)) == 1) {
                    lowestPos = j;
                    lowestElem = list.get(lowestPos);
                }
            }
            list.set(lowestPos, list.get(i));
            list.set(i, lowestElem);
        }
        return list;
    }

    /**
     * Uses the selection sort algorithm to sort a generic Array
     *
     * @param list the Array to sort
     * @param <T>  the type of the objects in list
     * @return     the sorted list (modifies original list)
     */
    public static <T extends Comparable<T>> T[] selectsort(T[] list) {
        return selectsort(new ArrayList<>(Arrays.asList(list))).toArray(list);
    }

    //endregion

    //region Merge Sort

    /**
     * merge function that merges two pre-sorted sublists in list
     *
     * @param list the ArrayList which contains the two sublists
     * @param s    the (inclusive) start index of the first sublist
     * @param m    the (inclusive) end index of the first sublist
     *             (m+1) is the (inclusive) start of the second sublist
     * @param e    the (inclusive) end index of the second sublist
     * @param <T>  the type of the objects in the list
     * @return     the list with the two sorted sublists merged (modifies original list)
     */
    private static <T extends Comparable<T>> ArrayList<T> merge(ArrayList<T> list, int s, int m, int e) {
        ArrayList<T> subList1 = new ArrayList<>(list.subList(s, m + 1));
        ArrayList<T> subList2 = new ArrayList<>(list.subList(m + 1, e + 1));
        int s1 = 0;
        int s2 = 0;
        int i = s;
        while (s1 < subList1.size() && s2 < subList2.size()) {
            T elem;
            if (subList1.get(s1).compareTo(subList2.get(s2)) == -1) {
                elem = subList1.get(s1++);
            } else {
                elem = subList2.get(s2++);
            }
            list.set(i++, elem);
        }
        while (s1 < subList1.size()) {
            list.set(i++, subList1.get(s1++));
        }
        while (s2 < subList2.size()) {
            list.set(i++, subList2.get(s2++));
        }
        return list;
    }

    /**
     * mergesort method that handles the sorting, uses indexes instead of splitting the ArrayList
     *
     * @param list the ArrayList to sort
     * @param s    the (inclusive) start index of the sublist to mergesort
     * @param e    the (inclusive) end index of the sublist to mergesort
     * @param <T>  the type of the objects in the list
     * @return     the sublist in list sorted (modifies original list)
     */
    private static <T extends Comparable<T>> ArrayList<T> mergesort(ArrayList<T> list, int s, int e) {
        if (s == e) {
            return list;
        }
        if (e-s == 1) {
            return swapif(list, s, e);
        }
        int m = (e - s) / 2 + s;
        mergesort(list, s, m);
        mergesort(list, m + 1, e);
        return merge(list, s, m, e);
    }

    /**
     * Uses the merge sort algorithm to sort a generic ArrayList
     *
     * @param list the ArrayList to sort
     * @param <T>  the type of the objects in list
     * @return     the sorted list (modifies original list)
     */
    public static <T extends Comparable<T>> ArrayList<T> mergesort(ArrayList<T> list) {
        return mergesort(list, 0, list.size() - 1);
    }

    /**
     * Uses the merge sort algorithm to sort a generic Array
     *
     * @param list the Array to sort
     * @param <T>  the type of the objects in list
     * @return     the sorted list (modifies original list)
     */
    public static <T extends Comparable<T>> T[] mergesort(T[] list) {
        return mergesort(new ArrayList<>(Arrays.asList(list))).toArray(list);
    }

    //endregion

    //region Quick Sort

    /**
     * quicksort method that handles the sorting, uses indexes instead of splitting the ArrayList
     *
     * @param list the ArrayList containing the sublist to quicksort
     * @param s    the (inclusive) start index of the sublist to quicksort
     * @param e    the (inclusive) end index of the sublist to quicksort
     * @param <T>  the type of objects in list
     * @return     the sublist in list sorted (modifies original list)
     */
    private static <T extends Comparable<T>> ArrayList<T> quicksort(ArrayList<T> list, int s, int e) {
        if (s >= e) {
            return list;
        }
        if (e-s == 1) {
            return swapif(list, s, e);
        }
        T pivot = list.get(e);
        int pivInd = e;
        for (int i = s; i < e; i++) {
            T elem = list.get(i);
            if (elem.compareTo(pivot) == 1) {
                switchHiLo:
                {
                    for (int j = e-1; j > i; j--) {
                        if (list.get(j).compareTo(pivot) == -1) {
                            list.set(i, list.get(j));
                            list.set(j, elem);
                            break switchHiLo;
                        }
                    }
                    list.set(e, elem);
                    list.set(i, pivot);
                    pivInd = i;
                    break;
                }
            }
        }
        quicksort(list, s, pivInd - 1);
        quicksort(list, pivInd + 1, e);
        return list;
    }

    /**
     * Uses the quick sort algorithm to sort a generic ArrayList
     *
     * @param list the ArrayList to sort
     * @param <T>  the type of the objects in list
     * @return     the sorted list (modifies original list)
     */
    public static <T extends Comparable<T>> ArrayList<T> quicksort(ArrayList<T> list) {
        return quicksort(list, 0, list.size() - 1);
    }

    /**
     * Uses the quick sort algorithm to sort a generic Array
     *
     * @param list the Array to sort
     * @param <T>  the type of the objects in list
     * @return     the sorted list (modifies original list)
     */
    public static <T extends Comparable<T>> T[] quicksort(T[] list) {
        return quicksort(new ArrayList<>(Arrays.asList(list))).toArray(list);
    }

    //endregion

}