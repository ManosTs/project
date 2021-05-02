public class MergeSort {
    private int run;

    //default constructor
    public MergeSort() {
        run = 0;
    }

    //merge and quick sort
    private void merge(CSVFile[] array, int start, int middle, int end) {
        //create the sizes of left and right arrays

        int leftArraySize = middle - start + 1;
        int rightArraySize = end - middle;

        //create the subArrays
        CSVFile[] leftArray = new CSVFile[leftArraySize];
        CSVFile[] rightArray = new CSVFile[rightArraySize];

        //write data to the left array

        for (int leftDataPos = 0; leftDataPos < leftArraySize; ++leftDataPos) {
            leftArray[leftDataPos] = array[start + leftDataPos];
        }
        //write data to the right array
        for (int rightDataPos = 0; rightDataPos < rightArraySize; ++rightDataPos) {
            rightArray[rightDataPos] = array[middle + 1 + rightDataPos];
        }

        int leftDataPos = 0;
        int rightDataPos = 0;
        int j = start;

        while (leftDataPos < leftArraySize && rightDataPos < rightArraySize) {
            run++;//comparisons
            if (leftArray[leftDataPos].getOpen() <= rightArray[rightDataPos].getOpen()) { //get the Open data in order to sort them
                array[j] = leftArray[leftDataPos];
                leftDataPos++;
            } else {
                array[j] = rightArray[rightDataPos];
                rightDataPos++;
            }
            j++;
        }


        while (leftDataPos < leftArraySize) { //in case there are remained elements of left array, write em' to array
            run++;
            array[j] = leftArray[leftDataPos];
            leftDataPos++;
            j++;
        }

        while (rightDataPos < rightArraySize) { //in case there are unwritten elements of right array, write em' to array
            run++;
            array[j] = rightArray[rightDataPos];
            rightDataPos++;
            j++;
        }

    }

    public void mergeSort(CSVFile[] array, int start, int end) {

        if (start < end) {
            int middle = start + (end - start) / 2;

            mergeSort(array, start, middle); //sort the left array
            mergeSort(array, middle + 1, end); //sort the right array

            merge(array, start, middle, end); //merge left and right array


        }

    }

    //get the Comparisons
    public int getRun() {
        return run;
    }

}
