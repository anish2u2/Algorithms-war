package org.workholic.algo;

public class KmpPattternMatchingAlgo {

    private char[] pattern;

    private char[] inputChars;

    private int[] positions;

    public static void main(String args[]){
        String inputStr="iudgfiugabdabciyoi";
        String pat="abdade";
        KmpPattternMatchingAlgo patternMatching=new KmpPattternMatchingAlgo();
        patternMatching.inputChars=inputStr.toCharArray();
        patternMatching.pattern=pat.toCharArray();
        patternMatching.preparePatternPositioning();
        System.out.println(patternMatching.matchPattern());
    }


    public void preparePatternPositioning(){
        int counter=1; int position=0;
        this.positions=new int[this.pattern.length];
        this.positions[position]=0;
        while (counter<pattern.length){
            if(this.pattern[counter]==this.pattern[position]){
                this.positions[counter]=++position;
                counter++;
            }else{
                if(position!=0) {
                    while (position > 0) {
                        position = this.positions[position - 1];
                        if (this.pattern[counter] == this.pattern[position]) {
                            this.positions[counter] = position;
                            position++;
                            counter++;
                            break;
                        }
                    }
                }else{
                    counter++;
                }
            }

        }
        for(int value:this.positions)
        System.out.print(value+" ");
    }

    public boolean matchPattern(){
        int counter=0,position=-1;
        while (counter<this.inputChars.length){
            if(position==pattern.length-1){
                return true;
            }
            if(inputChars[counter]==pattern[position+1]){
                counter++;
                position++;
            }else if(position==-1) {
                counter++;
            }
            else{

                position=positions[position]-1;

            }
        }

        if(position==pattern.length){
            return true;
        }

        return false;
    }

}
