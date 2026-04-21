public class Sorting {
  public static void insertionSort(int[] arr, int low, int high) {
    for (int i = low + 1; i <= high; i++) {
      int key = arr[i];
      int j = i - 1;

      while (j >= low && arr[j] > key) {
        arr[j + 1] = arr[j];
        j--;
      }

      arr[j + 1] = key;
    }
  }

  public static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = low - 1;

    for (int j = low; j < high; j++) {
      if (arr[j] <= pivot) {
        i++;
        swap(arr, i, j);
      }
    }
    swap(arr, i + 1, high);
    return i + 1;
  }

  private static void swap(int[] arr, int i, int j) {
    if (i != j) {
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
    }
  }
}
