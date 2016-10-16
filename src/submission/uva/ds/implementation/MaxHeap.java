package submission.uva.ds.implementation;

import java.util.Arrays;

public class MaxHeap {

	public static void main(String[] args) {
		int arr[] = {12, 11, 13, 5, 6, 7};
		
		int[] sortedArr = MaxHeap.sort(arr);
		System.out.println(Arrays.toString(sortedArr));
		
		sortedArr = MaxHeap.sortDesc(arr);
		System.out.println(Arrays.toString(sortedArr));
	}
	
	private int[] arr;
	private int size;
	
	private final int INITIAL_CAPACITY = 1000;
	private final long MAX_CAPACITY = (long) (Integer.MAX_VALUE - 1);
	private int capacity;
	
	public MaxHeap() {
		this.capacity = INITIAL_CAPACITY;
		arr = new int[INITIAL_CAPACITY];
		size = 0;
	}
	
	public MaxHeap(int capacity) {
		if(capacity > 0) {
			this.capacity = capacity;
			arr = new int[this.capacity];
		}else {
			this.capacity = INITIAL_CAPACITY;
			arr = new int[INITIAL_CAPACITY];
		}		
		size = 0;
	}
	
	private void swap(int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	private void grow() {
		if(size < capacity - 1)
			return;
		
		long intendedCapacity = (long) size + (long) capacity;
		if(intendedCapacity > MAX_CAPACITY) {
			System.out.println("Heap Overflow");
			return;
		}
		
		int[] newArr = new int[(int) intendedCapacity];
		System.arraycopy(arr, 0, newArr, 0, arr.length);
		arr = newArr;
	}
	
	private int parent(int index) {
		return (index - 1)/2;
	}
	
	private int left(int index) {
		return 2 * index + 1;
	}
	
	private int right(int index) {
		return 2 * index + 2;
	}
	
	public int front() {
		if(size <= 0) {
			System.out.println("Heap Underflow");
			return Integer.MIN_VALUE;
		}		
		return arr[0];
	}
	
	public int pop() {
		if(size <= 0) {
			System.out.println("Heap Underflow");
			return Integer.MIN_VALUE;
		}
		
		if(size == 1) {
			size--;
			return arr[0];
		}
		
		int root = arr[0];
		arr[0] = arr[size - 1];
		size--;
		maxHeapify(0);
		
		return root;
	}
	
	public void push(int key) {
		grow();				
		arr[size++] = key;
		siftUp(size - 1);
	}

	private void siftUp(int index) {
		while(index != 0 && arr[index] > arr[parent(index)]) {
			swap(index, parent(index));
			index = parent(index);
		}
	}
	
	private void maxHeapify(int index) {
		int l = left(index);
		int r = right(index);
		
		int largest = index;
		
		if(l < size && arr[l] > arr[largest])
			largest = l;
		
		if(r < size && arr[r] > arr[largest])
			largest = r;
		
		if(largest != index) {
			swap(index, largest);
			maxHeapify(largest);
		}
	}
	
	public static int[] sortDesc(int[] arr) {
		MaxHeap mxHeap = new MaxHeap();
		for(int i = 0; i < arr.length; i++) {
			mxHeap.push(arr[i]);
		}
		
		int[] result = new int[arr.length];
		for(int i = 0; i < result.length; i++) {
			result[i] = mxHeap.pop();
		}
		
		return result;
	}
	
	public static int[] sort(int[] arr) {
		MaxHeap mxHeap = new MaxHeap();
		for(int i = 0; i < arr.length; i++) {
			mxHeap.push(-arr[i]);
		}
		
		int[] result = new int[arr.length];
		for(int i = 0; i < result.length; i++) {
			result[i] = -mxHeap.pop();
		}
		
		return result;
	}
}
