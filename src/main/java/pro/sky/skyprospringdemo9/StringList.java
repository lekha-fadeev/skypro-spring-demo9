package pro.sky.skyprospringdemo9;

import pro.sky.skyprospringdemo9.exception.InvalidIndexException;
import pro.sky.skyprospringdemo9.exception.NullItemException;
import pro.sky.skyprospringdemo9.exception.StorageIsFullException;

import java.util.Arrays;

public class IntegerList {


    private final Integer[] storage;
    private int size;

    public IntegerList() {
        storage = new Integer[10];
    }

    public IntegerList(int initSize) {
        storage = new Integer[initSize];
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void validateSize() {
        if (size == storage.length) {
            throw new StorageIsFullException();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index < size) {
            throw new InvalidIndexException();
        }
    }



        @Override
        public Integer add(Integer item){
        validateSize();
        validateItem(item);
        storage[size++] = item;
        return item;
        }

        @Override
        public Integer add(int index, Integer item){
        validateSize();
        validateItem(item);
        validateIndex(index);

        if(index == size){
            storage[size++] = item;
            return item;
        }

        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item){
        validateIndex(index);
        validateItem(item);
        storage[index]=item;
        return item;
    }

    @Override
    public Integer remove(Integer item){
        validateItem(item);

        int index = indexOf(item);

        return remove(index);
    }

    @Override
    public Integer remove(int index){
        validateIndex(index);

        Integer item = storage[index];

        if(index != size){
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }

        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item){
        Integer[] storageCopy = toArray();
        sort(storageCopy);
        return binarySearch(storageCopy, item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index){
        validateIndex(index);
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList){
        return Arrays.equals(this.toArray(),otherList.toArray());
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0 ;
    }

    @Override
    public void clear(){
        size = 0;
    }

    @Override
     public Integer[] toArray(){
        return Arrays.copyOf(storage, size);
    }

    public  void sort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
    public boolean binarySearch(Integer[] arr, Integer item) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
}
