package com.akpwebdesign.Breakout;
import java.util.Map;
import java.util.TreeMap;

import org.newdawn.slick.Input;

public class Code {

    private int[] code = 
        {Input.KEY_UP, Input.KEY_UP, Input.KEY_DOWN, Input.KEY_DOWN, Input.KEY_LEFT, Input.KEY_RIGHT, Input.KEY_LEFT, Input.KEY_RIGHT, Input.KEY_B, Input.KEY_A, Input.KEY_ENTER};
    private Map<Integer, Integer>[] graph;
    private int currentNode = 0;

    public Code(int[] code) {
    	this.code = code;
        //Create graph
        graph = generateSequenceMap(this.code);
    }

    public Code() {
    	//Create graph
        graph = generateSequenceMap(this.code);
    }

    public boolean checkCode(int keyPressed) {
        Integer nextNode = graph[currentNode].get(keyPressed);

        //Set currentNode to nextNode or to 0 if no matching sub-sequence exists
        currentNode = (nextNode==null ? 0 : nextNode);

        return currentNode == code.length-1;
    }


    private Map<Integer, Integer>[] generateSequenceMap(int[] sequence) {

        //Create map
        Map<Integer, Integer>[] graph = new Map[sequence.length];
        for(int i=0 ; i<sequence.length ; i++) {
            graph[i] = new TreeMap<Integer,Integer>();
        }

        //i is delta
        for(int i=0 ; i<sequence.length ; i++) {
            loop: for(int j=i ; j<sequence.length-1 ; j++) {
            if(sequence[j-i] == sequence[j]) {

                //Ensure that the longest possible sub-sequence is recognized
                Integer value = graph[j].get(sequence[j-i+1]);
                if(value == null || value < j-i+1)
                    graph[j].put(sequence[j-i+1], j-i+1);
            }
            else
                break loop;
            }
        }
        return graph;
    }
}