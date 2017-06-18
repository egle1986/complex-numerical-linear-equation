package com.company;

public class Main {
    /*
    3a+2b+1c-6=0
    2a+1b+3c-6=0
    2a+2b+2c-6=0

    A=2-2/3b-1/3c
        4-4/3b-2/3c+1b+3c-6=0
    -1/3b+7/3c-2=0
    B=7C-6


     */

    public static void main(String[] args) {
        Formula[] formulas = new Formula[2];
        formulas[0] = new Formula(1.0, 0.0, 1.0, 0.0, -6.0, 4.0);
        formulas[1] = new Formula(2.0, 0.0, 1.0, 0.0, -4.0, 4.0);
        System.out.println(formulas[0]);
        System.out.println(formulas[1]);
        Formula[] result= solve(formulas);
        if (result!=null) {
            System.out.println("Vastus :");
            System.out.println(result[0]);
            System.out.println(result[1]);
        }
        System.out.println();


        Formula[] formulas3 = new Formula[3];
        //X0 N, X0 I , X1 N, X1 I ...... FREE N FREE I
        formulas3[0] = new Formula(3.0, 0.0, 2.0, 0.0, 1.0, 0.0, -6.0, 0.0);
        formulas3[1] = new Formula(2.0, 0.0, 1.0, 0.0, 3.0, 0.0, -6.0, 0.0);
        formulas3[2] = new Formula(2.0, 0.0, 2.0, 0.0, 2.0, 0.0, -6.0, 0.0);
        System.out.println(formulas3[0]);
        System.out.println(formulas3[1]);
        System.out.println(formulas3[2]);
        result= solve(formulas3);
        if (result!=null) {
            System.out.println("Vastus :");
            System.out.println(result[0]);
            System.out.println(result[1]);
            System.out.println(result[2]);
        }
    }

    static public Formula[] solve(Formula[] formulas ){
        int size = formulas.length;
        Formula[] expr = new Formula[size];
        Formula[] replaced = new Formula[size];
        try {
            for (int i = 0; i < size; i++) {
                replaced[i] = formulas[i];
                for (int j = 0; j < i; j++) {
                    System.out.println("asenda X" + j + " valemis " + i);
                    replaced[i] = expr[j].applyTo(replaced[i]);
                    System.out.println(replaced[i]);
                }
                System.out.println("leiame X" + i + " valemist " + i);
                expr[i] = replaced[i].findX(i);
                System.out.println(expr[i]);
            }
            for (int i = size - 2; i >= 0; i--) {
                for (int j = size - 1; j > i; j--) {
                    System.out.println("asendame tagasi tulemusele " + i + " lahendi " + j);
                    expr[i] = expr[j].applyTo(expr[i]);
                    System.out.println(expr[i]);
                }
            }
            return expr;
        }catch (ArithmeticException ae){
            System.out.println("ühest lahendit ei leidunud ");
        }
        return null;
    }

}
