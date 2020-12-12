package ua.avelzev.lab11.trees;

import org.w3c.dom.ls.LSOutput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;

public class BinaryHeap {
    private List<Integer> list;
    public int heapSize() {return this.list.size();}

    public BinaryHeap(){
        list = new LinkedList<>();
    }

    private void heapifyUp(int i){
        int parent = (i - 1)/2;

        while (i>0 && list.get(parent) < list.get(i)){
            int temp = list.get(i);
            list.set(i, list.get(parent));
            list.set(parent, temp);

            i = parent;
            parent = (i-1)/2;
        }
    }

    public void add(int value){
        list.add(value);
        heapifyUp(heapSize()-1);
    }

    private void heapifyDown(int i){
        int rightChild;
        int leftChild;
        int largestChild;

        for (;;)
        {
            leftChild = 2*i+1;
            rightChild = 2*i+2;
            largestChild = i;

            if (leftChild < heapSize()&& list.get(leftChild) > list.get(i))
                largestChild = leftChild;

            if (rightChild < heapSize()&& list.get(rightChild) > list.get(largestChild))
                largestChild = rightChild;

            if(largestChild == i){
                break;
            }

            int temp = list.get(i);
            list.set(i, list.get(largestChild));
            list.set(largestChild, temp);
            i = largestChild;
        }
    }

    public int deleteByIndex(int index){
        if( 0> index && index >= heapSize() ) {
            throw new IllegalArgumentException("The index out of array`s range!");
        }
        int result = list.get(index);
        list.set(index, list.get(heapSize()-1));
        list.remove(list.size()-1);
        heapifyDown(index);
        return result;
    }

    public void deleteByValue(int value) {

/*                System.out.println("Item at index " + i +  " removed");*/


    }

    public void buildHeap(Integer[] sourceArray) {
        for (int value: sourceArray)
            add(value);
    }

    public int getMax(){
        int result = list.get(0);
        list.set(0, list.get(list.size()-1));
        list.remove(heapSize()-1);
        heapifyDown(0);
        return result;
    }

    public void heapSort(Integer[] array){
        list.clear();
        buildHeap(array);
        for (int i = array.length-1; i>=0; i--)
            array[i] = getMax();
            heapifyDown(0);
    }

    public void print(){
        for(Integer a : list){
            System.out.print(a+ " ");

        }
    }
    public void clear()
    {
        list.clear();
    }
    static int stateMenu;
    private static void Menu()
    {
        Scanner in = new Scanner(System.in);

        System.out.println("(0) Exit");
        System.out.println("(1)Insert");
        System.out.println("(2)Delete by index");
        System.out.println("(3)Delete by value");
        System.out.println("(4)Print");
        System.out.println("(5)Build heap");
        System.out.println("(6)Heap sort");
        System.out.println("(7)Get max");
        System.out.println("(8)Clear()");
        System.out.println("Make your choice: ");

        stateMenu = in.nextInt();
    }
    public static void menu(){
        Scanner pause = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        BinaryHeap t = new BinaryHeap( );
        Integer[] array;
        Menu();


        while (stateMenu != 0){
            switch (stateMenu)
            {
                case 1:
                    System.out.println("Enter value: ");
                    t.add(in.nextInt());
                    System.out.println( t.heapSize());
                    pause.nextLine();
                    Menu();
                    break;

                case 2:
                    System.out.println("Enter index: ");
                    t.deleteByIndex(in.nextInt());
                    pause.nextLine();
                    Menu();
                    break;
                case 3:
                    System.out.println("Enter value: ");
                    t.deleteByValue(in.nextInt());
                    pause.nextLine();
                    Menu();
                    break;
                case 4:
                    t.print();
                    pause.nextLine();
                    Menu();
                    break;
                case 5:
                    System.out.println("Enter array length: ");
                    array = new Integer[in.nextInt()];
                    System.out.println("Enter array values: ");
                    for(int i = 0; i < array.length; i++)
                        array[i] = in.nextInt();
                    t.buildHeap(array);
                    pause.nextLine();
                    Menu();
                    break;
                case 6:
                    System.out.println("Enter array length: ");
                    array = new Integer[in.nextInt()];
                    System.out.println("Enter array values: ");
                    for(int i = 0; i < array.length; i++)
                        array[i] = in.nextInt();
                    t.heapSort(array);
                    for (Integer i : array )
                        System.out.println(i);
                    pause.nextLine();
                    Menu();
                    break;
                case 7:
                    t.getMax();
                    pause.nextLine();
                    Menu();
                    break;
                case 8:
                    t.clear();
                    pause.nextLine();
                    Menu();
                    break;
            }

        }
    }
}
