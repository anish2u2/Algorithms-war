package org.workholic.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StockBuySellProblem {



    public static void main(String[] args){
        int[] stockPrices={2,5,7,1,4,3,1,3};
        StockBuySellProblem problem=new StockBuySellProblem();
        problem.findMaxProfit(stockPrices,4);
    }

    public void findMaxProfit(int[] stockPrices,int numberOfTransactions){
        int[][] maxProfit=new int[numberOfTransactions][stockPrices.length];
        int temp=0;int maxProfitCalculatedonTheDay=0;
        for(int transaction=1;transaction<numberOfTransactions;transaction++){
            for(int sellDay=1;sellDay<stockPrices.length;sellDay++){
                temp=0;maxProfitCalculatedonTheDay=0;
                for(int buyDay=0;buyDay<sellDay;buyDay++){
                    temp=stockPrices[sellDay]-stockPrices[buyDay]+maxProfit[transaction-1][buyDay];
                    maxProfitCalculatedonTheDay=temp>maxProfitCalculatedonTheDay?temp:maxProfitCalculatedonTheDay;
                }
                maxProfit[transaction][sellDay]=Math.max(maxProfit[transaction][sellDay-1],maxProfitCalculatedonTheDay);
            }
        }
        int days=0;int pre=0;
        for(int transaction=0;transaction<numberOfTransactions;transaction++){
            for(int sellDay=0;sellDay<stockPrices.length;sellDay++){
            System.out.print(maxProfit[transaction][sellDay]+" ");
            if(transaction==numberOfTransactions-1)
                if(pre<maxProfit[transaction][sellDay]){
                    pre=maxProfit[transaction][sellDay];
                    days=sellDay;
                }
            }
            System.out.println();
        }

        findBuyAndSellDays(maxProfit,stockPrices,numberOfTransactions,days);
    }

    public void findBuyAndSellDays(int[][] maxProfit,int[] stockPrice,int transactions,int days){
        System.out.println(days);
        int profit=maxProfit[transactions-1][days];
        int difference=0;transactions--;
        Stack<Stock> stack=new Stack<>();
        stack.push(new Stock("Sold",days));
        while(transactions>0){
            //Sold difference
            difference=maxProfit[transactions][days]-stockPrice[days];
            while(days>0){
                if(difference==(maxProfit[transactions-1][days]-stockPrice[days])) {
                    difference = maxProfit[transactions - 1][days]-stockPrice[days] ;
                    //Buy on this day.
                    stack.push(new Stock("Buy",days));
                    while(maxProfit[transactions-1][days]==maxProfit[transactions-1][days-1]){
                        //find next sell day.
                        days--;
                    }
                    stack.push(new Stock("Sold",days));
                    break;
                }
                days--;
            }
            transactions--;
        }
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    class Stock{
        private int position;
        private String comment;

        Stock(String comment,int position){
            this.comment=comment;this.position=position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getPosition() {
            return position;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getComment() {
            return comment;
        }

        @Override
        public String toString() {
            return this.comment+" "+this.position;
        }
    }


}
