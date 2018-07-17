/*
Jacqueline Saelee
COMP 282
Project #6
Minimum Integer Heap
May 10, 2018
*/

package minintheap;

import java.util.*;

public class MinIntHeap {

  
    // - - - VARS - - - //
    
    int[] Heap;
    int LastNode;
    int Capacity;
    
    
    // - - - PUBLIC - - - //
    
    public MinIntHeap(int m){
        int heapSize = m + 1;
        
        // Initialize Capacity of Heap
        Heap = new int[heapSize];
        Capacity = m;
        LastNode = 0;
        
    }
    
    public MinIntHeap(int[] b, int m){
        
       // Initialize Heap
       Heap = new int[m + 1];
       Capacity = m;
       LastNode = b.length;
       int k = 1;
       
       // Insert Values into Heap
       for(int i = 0; i < b.length; i++){
           
           Heap[k] = b[i];
           k++;      
       }
       
       buildHeap();
 
    }
    
     public boolean isEmpty(){
        if(LastNode <= 0)
            return true;
        return false;
    }
    
    
    public int size(){
        return LastNode;
    }
    
    public void heapInsert(int v){
        
        if(LastNode++ == Capacity){
            System.out.println("\nHeap is full. Value not added = " + v);
            --LastNode;
            return;
        }
        
        //Insert after current LastNode
        Heap[LastNode] = v;
        bubbleUp(LastNode);
        
    }
    
    
    public int removeMin(){
        
        // If Heap is EMPTY
        if(isEmpty() == true){
            System.out.println("Cannot delete min from an empty heap");
            return -9999;
        }
        
        int min = Heap[1];
       
        swap(1, LastNode);
        --LastNode;  
        bubbleDown(1);
       
        return min;
    }
    
    public int min(){
        
        if(isEmpty() == true){
            System.out.println("No Min: Empty Heap");
            return -999;
        }
        
        return Heap[1];
    }
    
    public void printHeapValues(){
        
        System.out.print("{");
        for(int i = 1; i <= LastNode; i++){
            
            if(i == LastNode){
                System.out.print(Heap[i]);
            }
            else
                System.out.print(Heap[i] + ", ");
        }
        System.out.print("}\n");
    }
    
    public int[] getHeapValues(){
        
        int[] ret = new int[LastNode];
        
        for(int i = 1; i <= LastNode; i++){
            ret[i - 1] = Heap[i];
        }
        
        return ret;
    }
    
    public static void heapSort(int[] b){
        
       int i = 0;
       MinIntHeap heap1 = new MinIntHeap(b, b.length);
      
       while((heap1.size()) >= 1){
           b[i] = heap1.removeMin();
           i++;
       }
     
    }
    
    
    // - - - PRIVATE - - - //
    
    private void bubbleUp(int currNodePos){
        
        // If currNodePos is EMPTY, RETURN
        if(isEmpty() == true)
            return;
        
        if(currNodePos == 1)
            return;
        
        // Set Parent Node
        int parentPos = currNodePos / 2;
        
        // SWAP If Parent Value is GREATER than currNodePos Value
        if(Heap[parentPos] > Heap[currNodePos]){
            swap(parentPos, currNodePos);
            bubbleUp(parentPos);
        }
    }
    
    private void bubbleDown(int currNodePos){
        
        // RETURN IF Node has NO Children
        if(((currNodePos * 2)) > LastNode)
            return;
        
        // Initialize VARS
        int leftChildPos = currNodePos * 2;
        int rightChildPos = (currNodePos * 2) + 1;
        int min;   

        // 2 Children
        if(rightChildPos <= LastNode){
            
            if(Heap[rightChildPos] < Heap[leftChildPos])
                min = rightChildPos;
            else 
                min = leftChildPos;
        }
        // ONLY LEFT Child
        else{
            min = leftChildPos;
        }
        
        // SWAP IF Smallest Child is < Parent
        if(Heap[min] < Heap[currNodePos]){
            swap(min, currNodePos);
            bubbleDown(min);
        }   
    }
    
    private void buildHeap(){
        
        // Go through each "mini" heap
        for(int i = (LastNode / 2); i >= 1; i-- ){
            minHeapify(i);
        }
  
        // BubbleDown ROOT
        bubbleDown(1);
    }
    
    private void minHeapify(int currNodePos){
        
        // RETURN if NO Children
        if(((currNodePos * 2)) > LastNode)
            return;
        
        // Set VARS
        int parent = currNodePos;
        int leftChildPos = parent * 2;
        int rightChildPos = (parent * 2) + 1;
        int min;
       
        // 2 Children
        if(rightChildPos <= LastNode){
            
            if(Heap[rightChildPos] < Heap[leftChildPos])
                min = rightChildPos;
            else 
                min = leftChildPos;
          
        }
        // LEFT Child ONLY
        else{
            min = leftChildPos;
        }
        
        if(Heap[min] < Heap[parent]){
           
           swap(min, parent);
           bubbleDown(min);
           
        }     
    }
    
    // Swap values in array
    private void swap(int a, int b){
        
       int temp = Heap[a];
       Heap[a] = Heap[b];
       Heap[b] = temp;
       
    }
    
}
