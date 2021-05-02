import java.util.*;

public class withCloseStock {


        CSVProcessor csv_file = new CSVProcessor();


        private void sortingWithHeapSort(String FILE_PATH, String NEW_FILE_PATH) {

            HeapSort heapSortCloseData = new HeapSort();//object of MergeSort class

            //insert the path of file
            ArrayList<CSVFile> getList = csv_file.CsvReader(FILE_PATH);

            CSVFile[] array = getList.toArray(new CSVFile[0]); //convert list to Array

            long startTime = System.nanoTime();
            heapSortCloseData.heapSort(array); //heap sort data
            long endTime = System.nanoTime();
            System.out.println("Comparisons: "+ heapSortCloseData.getRun());
            System.out.println("Heap Sorting took:"+(double)(endTime-startTime)/1000000000+" seconds");

            //write the sorted data
            List<CSVFile> getSortedList = Arrays.asList(array); //converting to list
            csv_file.csvWriter(getSortedList, NEW_FILE_PATH);
            System.out.println("File has been written with heap sort,if already was created with counting sort then the file has been replaced");
        }
    private void sortingWithCountingSort(String FILE_PATH, String NEW_FILE_PATH) {
        //insert the path of file
        ArrayList<CSVFile> getList = csv_file.CsvReader(FILE_PATH);

        CSVFile[] array = getList.toArray(new CSVFile[0]); //convert list to Array

        //counting sort the data of file

        CountingSort countingSortOpenData = new CountingSort();

        long startTime = System.nanoTime();
        countingSortOpenData.countingSort(array, array.length);
        long endTime = System.nanoTime();
        System.out.println("Comparisons: "+ countingSortOpenData.getRun());
        System.out.println("Counting Sort took:"+(double)(endTime-startTime)/1000000000+" seconds");

        //write the sorted data
        List<CSVFile> getSortedList = Arrays.asList(array); //converting to list
        csv_file.csvWriter(getSortedList, NEW_FILE_PATH);

        System.out.println("File has been written with counting sort, if already was created with heap sort then the file has been replaced");
    }

        public void getOption(String FILE_PATH, String NEW_FILE_PATH) {
         try {
            System.out.print("Input 'Heap' to execute HeapSort or 'Counting' for CountingSort:");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();

            if (option.equals("Heap") || option.equals("heap")) {
                sortingWithHeapSort(FILE_PATH, NEW_FILE_PATH);
            } else if (option.equals("Counting") || option.equals("counting")) {
                sortingWithCountingSort(FILE_PATH, NEW_FILE_PATH);
            }
        } catch (InputMismatchException i) {
            System.out.println("Wrong input");
            getOption(FILE_PATH, NEW_FILE_PATH);
        }
    }


}
