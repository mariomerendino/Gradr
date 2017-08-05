package Logic;
import java.util.*;
import java.util.Scanner;
public class gradedAssignment{
    public Double score;
    public Double weight;
    public String name;
    public double weightedScore;
    public gradedAssignment(){

    }
    public gradedAssignment(Double s, Double w, String n){
        score = s;
        weight = w;
        name = n;
    }
    public gradedAssignment(gradedAssignment g){
        score = g.score;
        weight = g.weight;
        name = g.name;
    }
    public void setName(String n){
        n= name;
    }
    public void setScore(Double s){
        s = score;
    }
    public void setWeight(Double w){
        w = weight;
    }
    public String getName(){
        return name;
    }
    public Double getScore(){
        return score;
    }
    public Double getWeight(){
        return weight;
    }
    public Double getWeightedScore(){
        return weightedScore;
    }
    public void calculateWeightedScore(){
        weightedScore = score * (weight/100);
    }
    public void inputInfo(){
        Scanner user_input = new Scanner( System.in );
        System.out.print("Whats the name of the assingment\n");
        name = user_input.next();
        System.out.print("What was their grade \n");
        score = user_input.nextDouble();
        System.out.print("What was the weight");
        weight = user_input.nextDouble();
    }
}