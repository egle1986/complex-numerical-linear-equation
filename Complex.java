package com.company;

import java.math.BigDecimal;
import java.math.MathContext;

public class Complex {
    private BigDecimal n;
    private BigDecimal i;

    public static final Complex ZERO = new Complex(0.0, 0.0);
    public static final Complex ONE = new Complex(1.0, 0.0);

    public Complex(Double n, Double i) {
        this.n = new BigDecimal(n);
        this.i = new BigDecimal(i);
    }

    public Complex(BigDecimal n, BigDecimal i) {
        this.n = n;
        this.i = i;
    }

    public Complex plus(Complex k){
        return new Complex(n.add(k.n),i.add(k.i));
    }

    public Complex minus(Complex k){
        return new Complex(n.subtract(k.n),i.subtract(k.i));
    }

    public Complex multi(Complex k){
        return new Complex(n.multiply(k.n).subtract(i.multiply(k.i)),n.multiply(k.i).add(i.multiply(k.n)));
    }

    public Complex div(Complex k){
        BigDecimal divBy= k.n.multiply(k.n).add(k.i.multiply(k.i));
        BigDecimal nn=n.multiply(k.n).add(i.multiply(k.i));
        BigDecimal ii=n.multiply(k.i).add(i.multiply(k.n));
        return new Complex(nn.divide(divBy, MathContext.DECIMAL64),ii.divide(divBy,MathContext.DECIMAL64));
    }

    public Complex reverse(){
        return new Complex(n.negate(),i.negate());
    }

    @Override
    public String toString(){
        if (i.doubleValue()==0 && n.doubleValue()==0)
            return "0";
        if (i.doubleValue()==0)
            return "" +n;
        if (n.doubleValue()==0)
            return i+ "*i";
        if (i.doubleValue()<0)
            return "" + n + i + "*i";
        return n+"+" + i +"*i";
    }

    public boolean isZero(){
        return (i.doubleValue() == 0 && n.doubleValue() == 0);
    }
}
