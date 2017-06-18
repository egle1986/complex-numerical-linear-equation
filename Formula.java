package com.company;

public class Formula {
    Complex[] values;
    Complex free;
    int expressed=-1;

    public Formula(int n){
        values = new Complex[n];
    }

    //X0 N, X0 I , X1 N, X1 I ...... FREE N FREE I
    public Formula(double... numbers){
        values = new Complex[numbers.length / 2 - 1];
        for (int i=0;i<numbers.length-3;i+=2){
            values[i / 2] = new Complex(numbers[i], numbers[i + 1]);
        }
        free=new Complex(numbers[numbers.length-2], numbers[numbers.length-1]);
    }

    public Formula findX(int n){
        Formula result=new Formula(values.length);
        for (int i=0;i<values.length;i++){
            if (i==n)
                result.values[n] = Complex.ONE;
            else
                result.values[i]=values[i].reverse().div(values[n]);
        }
        result.free = free.reverse().div(values[n]);
        result.expressed=n;
        return result;
    }

    public Formula applyTo(Formula to){
        Formula result=new Formula(values.length);
        for (int i=0;i<values.length;i++){
            if (i==expressed)
                result.values[expressed]= Complex.ZERO;
            else
                result.values[i]=to.values[expressed].multi(values[i]).plus(to.values[i]);
        }
        result.free = to.values[expressed].multi(free).plus(to.free);
        result.expressed=to.expressed;
        return result;
    }

    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        if (expressed>=0)
            sb.append("X" + expressed + " = ");
        for (int i = 0; i < values.length; i++) {
            if (i==expressed) continue;
            if (!values[i].isZero()) {
                sb.append("(" + values[i] + ")*X" + i);
                sb.append(" + ");
            }
        }
        sb.append("(" + free + ")");
        if (expressed<0)
            sb.append(" = 0");
        return sb.toString();
    }
}
