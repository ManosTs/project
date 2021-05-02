public class QuickSort {

    private int run;
    public QuickSort(){
        run = 0;
    }

    private int partition(CSVFile[] array, int left, int right) {
        //define the pivot and set it to the most right element which is at the end of array(array.length - 1)
        CSVFile pivot = array[right];

        int storeIndex = left - 1;//this store the smallest position of an element

        for (int index = left; index < right; index++) {
            run++; //comparisons
            if (array[index].getOpen() <= pivot.getOpen()) {
                storeIndex++; //increase by one the variable storeIndex

                //swap if the value of the element which is located to the smallest position, is less than the value of the right element
                CSVFile temp = array[storeIndex]; //create an element which stores temporarily the element with the smallest position
                array[storeIndex] = array[index];
                array[index] = temp;
            }
        }

        //swap the pivot element with the element which is stored in (storeIndex + 1) position
        CSVFile temp = array[storeIndex + 1];
        array[storeIndex + 1] = array[right];
        array[right] = temp;

        return storeIndex + 1;
    }

    public void quickSort(CSVFile[] array, int start, int end) {
        if (start < end) {

            int part = partition(array, start, end);

            quickSort(array, start, part - 1); //before partition
            quickSort(array, part + 1, end); //after partition
        }
    }


    //get the Comparisons
   public int getRun(){
        return run;
   }

}
