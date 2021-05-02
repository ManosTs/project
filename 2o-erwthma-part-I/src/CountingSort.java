public class CountingSort {
    private int run ;
    public CountingSort(){
        run = 0;
    }

    public void countingSort(CSVFile[] array, int length) {
        CSVFile max = array[0];

        //find max
        for(int index = 0;index<length;index++){
            run++; //comparisons
            if((int)Math.round(array[index].getClose()) > (int)Math.round(max.getClose())){
                max = array[index];
            }
        }

        int[] count = new int[(int)Math.round(max.getClose())+1]; //holds the count of each element at their index

        CSVFile[] sortedArray = new CSVFile[length + 1];

        //initialize the count array
        for(int index = 0;index< (int)Math.round(max.getClose());index++){
            count[index] = 0;
        }

        //store the count of elements
        for(int index = 0; index<length;index++){
            count[(int)Math.round(array[index].getClose())]++;
        }//if zero then the numbers does not belong to array.

        //get the index of the sorted element
        for (int index = 1;index<=(int)Math.round(max.getClose());index++){
            count[index] += count[index-1];
        }

        //find the index of each element
        for(int index = length-1;index>=0;index--){                                                 //n            //k = n + 1
                run++; //length + 1 comparisons, however time complexity of counting sort is O(size of array + (size of array + 1))
                sortedArray[count[(int)Math.round(array[index].getClose())]- 1]  = array[index];
                count[(int)Math.round(array[index].getClose())]--;
        }

        //copy the elements to sortedArray
        for(int index=0;index<length;index++){
            array[index] = sortedArray[index] ;
        }
    }

    public int getRun(){
        return run;
    }
}
