public class HeapSort {
    private int run;

    public HeapSort(){
        run = 0;
    }
    //create the heap
    private void buildHeap(CSVFile[] array){
        for(int index=(array.length-1)/2; index>=0; index--){
            heapify(array,array.length-1,index);
        }
    }

    //swap the elements
    private void swap(CSVFile[] array, int i, int j){
        CSVFile temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //heap the element
    private void heapify(CSVFile[] array, int arrayLength, int posElement) {
        int largestPos;

        int leftChildPos = 2*posElement + 1;
        int rightChildPos = 2*posElement + 2;
        run++;
        //check if left child is larger than root element
        if(leftChildPos<=arrayLength && array[leftChildPos].getClose() > array[posElement].getClose()){
            largestPos = leftChildPos;
        }else largestPos = posElement;

        //check if right child is larger than largest element
        if(rightChildPos<=arrayLength && array[rightChildPos].getClose() > array[largestPos].getClose()){
            largestPos = rightChildPos;
        }

        //if largest element is not root
        if(largestPos != posElement){
            swap(array,posElement, largestPos);
            heapify(array,arrayLength,largestPos);
        }
    }

    //sort the heap
    public void heapSort(CSVFile[] array) {
        buildHeap(array);
        int sizeArray = array.length - 1;
        for(int index = sizeArray; index>=0; index--){
            swap(array, 0, index);
            sizeArray--;
            heapify(array,sizeArray,0);
        }

    }

    public int getRun(){
        return run;
    }
}
