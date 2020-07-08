package org.workholic.algo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class SumOfSubset {
    public static void main(String args[]){
        SumOfSubset subsetProblem=new SumOfSubset();
        int[] data=new int[]{5,10,12,13,15,18};
        int[] result=new int[data.length];
        for(int counter=0;counter<data.length-1;counter++) {
            subsetProblem.findSubset(Arrays.copyOfRange(data,counter,data.length), 30, 73, 6);
        }
    }

    public void findSubset(int[] data,int sum,int total,int length){
        Stack<Node> stack=new Stack<>();
        int counter=0;
        int[] array=new int[data.length];
        int value=0;int rest=total;
        while(true){
            if(counter<data.length){
                value=value+data[counter];
                rest=rest-data[counter];
                if(value<sum && rest>(sum-value)){
                    Node node=new Node(counter,value,rest);
                    stack.push(node);
                    array[counter]=1;
                }else if(sum==value){
                    array[counter]=1;
                    stack.clear();break;
                }else{
                    array[counter]=0;
                    Node node=stack.peek();
                    node.setRestValue(rest);
                    value=node.getElementValue();
                }

            }else{
                Node node=stack.pop();
                counter=node.getPosition();
                array[node.getPosition()]=0;
                value=node.getElementValue()-data[node.getPosition()];
                if(stack.isEmpty())
                    return;
                rest=stack.peek().getRestValue()-data[node.getPosition()];
                if(counter==0){
                    break;
                }
            }
            counter++;
        }
        StringBuilder builder=new StringBuilder();
        builder.append("{");
        for(int counter1=0;counter1<array.length;counter1++){
            if(array[counter1]==1)
                builder.append(data[counter1]).append(",");
        }
        builder.append("}");
        System.out.println(builder.toString());
        builder.delete(0,builder.length());
    }




    public class Node{
        private int position=-1;
        private int elementValue;
        private int restValue;

        Node(int position,int elementValue,int restValue) {
            this.position = position;
            this.elementValue = elementValue;
            this.restValue = restValue;
        }
        public int getElementValue() {
            return elementValue;
        }

        public void setElementValue(int elementValue) {
            this.elementValue = elementValue;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getRestValue() {
            return restValue;
        }

        public void setRestValue(int restValue) {
            this.restValue = restValue;
        }
    }

}
