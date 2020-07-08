package org.workholic.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class HamiltonianCycleFindProblem {

    private int[][] inputs={{0,1,1,0,1},{1,0,1,0,1},{1,1,0,1,0},{0,0,1,0,1},{1,1,0,1,0}};
    private int sizeOfVertices=5;
    public static void main(String args[])throws Exception{
        HamiltonianCycleFindProblem cycle=new HamiltonianCycleFindProblem();
        /*System.out.println("Please enter the size of vertices:");
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        cycle.sizeOfVertices=Integer.valueOf(reader.readLine());
        int[][] values=new int[cycle.sizeOfVertices][cycle.sizeOfVertices];
        System.out.println("Please enter the size of graph:");
        int length=Integer.valueOf(reader.readLine());
        List<String> builder=new ArrayList<>();
        for(int counter=0;counter<length;counter++){
            builder.add(reader.readLine());
        }
        builder.stream().forEach((sequence)->{
            String[] splited=sequence.split(" ");
            values[Integer.parseInt(splited[0])][Integer.parseInt(splited[1])]=1;
        });*/
       // cycle.inputs=values;
        cycle.findCycle(cycle.inputs,new int[]{1,2,3,4,5});
    }

    public void readInputs(){

    }

    public void findCycle(int[][] data,int[] vertices){
        int length= data.length;

        Stack<Integer> cycle= new Stack<>();

        List<int[]> resultsList=new ArrayList<>();
        int[] vertex=new int[vertices.length];
        int lastProcessedVertices=0,counter=0,verticesCounter=0;
        Set<Integer> processedNodes=new HashSet<>();
        while(true){
            if(counter<vertices.length){
                if(counter==0 && !processedNodes.contains(vertices[counter])){
                    vertex[verticesCounter]=vertices[counter];
                    processedNodes.add(vertices[counter]);
                    verticesCounter++;
                }else{
                    System.out.println(verticesCounter+" "+counter +" "+data[vertex[verticesCounter-1]-1][vertices[counter]-1]);
                    if(counter<vertices.length && verticesCounter<vertex.length&& data[vertex[verticesCounter-1]-1][vertices[counter]-1]==1 && !processedNodes.contains(vertices[counter])) {
                        processedNodes.add(vertices[counter]);
                        vertex[verticesCounter]=vertices[counter];
                        System.out.println("Adding vertex:"+vertex[verticesCounter]);
                        verticesCounter++;
                    }
                }
            }else {
                if(vertex[verticesCounter-1]==vertices[0]){
                    //no cycle found.
                    break;
                }else if(data[vertex[verticesCounter-1]-1][vertices[0]-1]==1){
                    //found loop from end to the first vertex, add it to the list.
                    //resultsList.add(Arrays.copyOfRange(vertex,0,vertex.length));
                    System.out.println("Found result");
                    for(int value:vertex){
                        System.out.print(value+" ");
                    }
                    System.out.println("End");
                    //pop last element.
                    processedNodes.remove(vertex[verticesCounter-1]);
                    vertex[verticesCounter-1]=0;verticesCounter--;
                    //make last visited node previous node.

                    lastProcessedVertices=vertex[verticesCounter-1];
                    vertex[verticesCounter-1]=0;verticesCounter--;
                    counter=0;
                }else{
                    processedNodes.remove(vertex[verticesCounter-1]);
                    vertex[verticesCounter-1]=0;verticesCounter--;
                    //make last visited node previous node.
                    lastProcessedVertices=vertex[verticesCounter-1];
                    vertex[verticesCounter-1]=0;verticesCounter--;
                    counter=0;
                }
            }
            counter++;
        }
        resultsList.forEach((list)->{
            for(int value: list)
            System.out.print(value+" ");
        });

    }

}
