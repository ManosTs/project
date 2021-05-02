import java.util.*;

public class withOpenStock {

    CSVProcessor csv_file = new CSVProcessor(); //object of CSVProcessor class

    //run the merge sort write the sorted data to file
    private void sortingWithMergeSort(String FILE_PATH, String NEW_FILE_PATH) {

        MergeSort mergeSortOpenData = new MergeSort();//object of MergeSort class

        //insert the path of file
        ArrayList<CSVFile> getList = csv_file.CsvReader(FILE_PATH);

        CSVFile[] array = getList.toArray(new CSVFile[0]); //convert list to Array


        long startTime = System.nanoTime();
        mergeSortOpenData.mergeSort(array, 0, array.length - 1);
        long endTime = System.nanoTime();
        System.out.println("Comparisons:" + mergeSortOpenData.getRun()); // print how many comparisons MergeSort made
        System.out.println("Execution of Merge Sort took:"+(double)(endTime - startTime)/1000000000+" seconds"); //how much it took to complete the sorting


        //write the sorted data
        List<CSVFile> getSortedList = Arrays.asList(array); //converting to list
        csv_file.csvWriter(getSortedList, NEW_FILE_PATH);
        System.out.println("File has been written with merge sort,if already was created with Quick sort then the file has been replaced");
    }

    //run the quick sort and write the sorted data to file
    private void sortingWithQuickSort(String FILE_PATH, String NEW_FILE_PATH) {
        //insert the path of file
        ArrayList<CSVFile> getList = csv_file.CsvReader(FILE_PATH);

        CSVFile[] array = getList.toArray(new CSVFile[0]); //convert list to Array

        //quick sort the data of file
        QuickSort quickSortOpenData = new QuickSort();

        long startTime = System.nanoTime();
        quickSortOpenData.quickSort(array, 0, array.length - 1);
        long endTime = System.nanoTime();
        System.out.println("Comparisons:" + quickSortOpenData.getRun()); // print how many comparisons QuickSort made
        System.out.println("Execution of Quick Sort took:"+(double)(endTime - startTime)/1000000000+" seconds");//how much it took to complete the sorting

        //write the sorted data
        List<CSVFile> getSortedList = Arrays.asList(array); //converting to list
        csv_file.csvWriter(getSortedList, NEW_FILE_PATH);

        System.out.println("File has been written with quick sort, if already was created with Merge sort then the file has been replaced");
    }

    public void getOption(String FILE_PATH, String NEW_FILE_PATH) {
        try {
            System.out.print("Input 'Merge' to execute MergeSort or 'Quick' for QuickSort:");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();

            if (option.equals("Merge") || option.equals("merge")) {
                sortingWithMergeSort(FILE_PATH, NEW_FILE_PATH);
            } else if (option.equals("Quick") || option.equals("quick")) {
                sortingWithQuickSort(FILE_PATH, NEW_FILE_PATH);
            }
        } catch (InputMismatchException i) {
            System.out.println("Wrong input");
            getOption(FILE_PATH, NEW_FILE_PATH);
        }
    }
}
