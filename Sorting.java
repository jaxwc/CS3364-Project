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

  public static void quickHybridSort(int[] arr, int k) {
    if (arr == null || arr.length < 2) return;
    quickHybridSortRecursive(arr, 0, arr.length - 1, k);
  }

  private static void quickHybridSortRecursive(int[] arr, int low, int high, int k) {
    if (low < high) {
      int size = high - low + 1;

      if (size <= k) {
        insertionSort(arr, low, high);
      } else {
        int index = partition(arr, low, high);
        quickHybridSortRecursive(arr, low, index - 1, k);
        quickHybridSortRecursive(arr, index + 1, high, k);
      }
    }
  }

  private static void swap(int[] arr, int i, int j) {
    if (i != j) {
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
    }
  }
}
