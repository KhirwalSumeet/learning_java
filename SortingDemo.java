import java.util.*;

class Sorting {
    public void insertionSort (int[] A) {
        int key, j;
        for (int i = 1; i < A.length; i++) {
            key = A[i];
            j = i - 1;
            while (key < A[j] && j > 0) {
                A[j + 1] = A[j];
                A[j] = key;
                j = j - 1;
            }
        }
    }
    
    public void mergeSort (int[] A) {
        if (A.length > 1) {
            int mid = A.length/2;
            int[] lefthalf = Arrays.copyOfRange(A, 0, mid);
            int[] righthalf = Arrays.copyOfRange(A, mid, A.length);
            mergeSort(lefthalf);
            mergeSort(righthalf);
            
            int i, j, k;
            i = j = k =0;
            
            while (i < lefthalf.length && j < righthalf.length) {
                if (lefthalf[i] < righthalf[j])
                    A[k++] = lefthalf[i++];
                else
                    A[k++] = righthalf[j++]; 
            }
            
            while (i < lefthalf.length)
                A[k++] = lefthalf[i++];
                
            while (j < righthalf.length)
                A[k++] = righthalf[j++];
            
        }
    }
    
    public int partition(int[] A, int p, int n) {
        if (p > n)
            return n;
        
        int temp;
        int pivotValue = A[p];
        int j = p + 1;
        int i = p;
        for ( ; j < n; j++) {
            if (pivotValue > A[j]) {
                i = i + 1;
                temp = A[j];
                A[j] = A[i];
                A[i] = temp;
            }
        }
        temp = A[i];
        A[i] = A[p];
        A[p] = temp;
        return i;
    }
    
    public void quickSort(int[] A, int p, int r) {
        if (p < r) {
            int q = partition(A, p, r);
            quickSort(A, p, q);
            quickSort(A, q+1, r);
        }
    }
}

class SortingDemo {
    public static void main( String[] args) {
        Sorting sort = new Sorting();
        int[] arr = new int[]{5,7,3,10,4,8,15};
        sort.quickSort(arr, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
