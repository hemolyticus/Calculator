/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpcalculator;

/**
 *
 * @author MJ
 */
public class CalculatorModel {

    private double num1;
    private double num2;
    private double inputNumber;
    private int inputOperator;
    private int operator;
    private boolean prevCalc = false;
    private boolean num1Set = false;
    private boolean operatorSet = false;
    private boolean radianSet;
    private boolean degreesSet;

    public boolean isRadianSet() {
        return radianSet;
    }

    public void setRadianSet(boolean radianSet) {
        this.radianSet = radianSet;
    }

    public boolean isDegreesSet() {
        return degreesSet;
    }

    public void setDegreesSet(boolean degreesSet) {
        this.degreesSet = degreesSet;
    }

    public boolean isNum1Set() {
        return num1Set;
    }

    public void setNum1Set(boolean num1Set) {
        this.num1Set = num1Set;
    }

    public boolean isOperatorSet() {
        return operatorSet;
    }

    public void setOperatorSet(boolean operatorSet) {
        this.operatorSet = operatorSet;
    }

    public double getInputNumber() {
        return inputNumber;
    }

    public void setInputNumber(double inputNumber) {
        this.inputNumber = inputNumber;
    }

    public int getInputOperator() {
        return inputOperator;
    }

    public void setInputOperator(int inputOperator) {
        this.inputOperator = inputOperator;
    }

    public boolean isPrevCalc() {
        return prevCalc;
    }

    public void setPrevCalc(boolean prevCalc) {
        this.prevCalc = prevCalc;
    }

    public double getNum1() {
        return num1;
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public double getNum2() {
        return num2;
    }

    public void setNum2(double num2) {
        this.num2 = num2;

    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public void convertToRadians() {
        this.inputNumber = Math.toDegrees(Math.toRadians(this.inputNumber));

    }

    public void convertToDegrees() {
        this.inputNumber = Math.toRadians(this.inputNumber);
    }

    public void determineOperator() throws Exception {
        switch (this.getInputOperator()) {
            case 1: //add

            case 2: //subtract

            case 3: //multiply

            case 4: //divide

            case 5: //exponent

                if (this.isNum1Set() && this.isOperatorSet()) {
                    this.num2 = this.inputNumber;

                    Calculate();

                    this.setNum2(0);
                    this.setOperator(this.getInputOperator());
                    this.setPrevCalc(true);
                    this.setNum1Set(true);
                    this.setOperatorSet(true);

                } else {
                    this.num1 = this.inputNumber;
                    this.operator = this.getInputOperator();
                    this.setNum1Set(true);
                    this.setOperatorSet(true);
                    this.setPrevCalc(false);

                }

                break;

            case 6://euler

            case 7://pi

            case 8://reciprocal

            case 9://factorial

                this.num1 = this.inputNumber;
                this.operator = this.inputOperator;
                singleOperatorCalculator();
                this.setNum1Set(true);
                this.setOperatorSet(false);
                this.setPrevCalc(true);
                break;

            case 10://sin

            case 11://cos

            case 12://tan

                if (this.isDegreesSet() && !this.isRadianSet()) {
                    this.inputNumber = Math.toRadians(this.inputNumber);
                } else if (this.isRadianSet() && !this.isDegreesSet()) {
                    this.inputNumber = Math.toDegrees(Math.toRadians(this.inputNumber));
                }
                this.num1 = this.inputNumber;

                this.operator = this.inputOperator;
                singleOperatorCalculator();
                this.setNum1Set(true);
                this.setOperatorSet(false);
                this.setPrevCalc(true);
                break;

            case 13://asin

            case 14://acos

            case 15://atan               

                this.num1 = this.inputNumber;

                this.operator = this.inputOperator;
                singleOperatorCalculator();
                if (this.isDegreesSet() && !this.isRadianSet()) {
                    this.num1 = this.num1;
                } else if (this.isRadianSet() && !this.isDegreesSet()) {
                    this.num1 = Math.toRadians(this.num1);
                }
                this.setNum1Set(true);
                this.setOperatorSet(false);
                this.setPrevCalc(true);

                break;

            case 16://equals

                this.num2 = this.inputNumber;
                if (this.num1Set) {
                    Calculate();
                } else {
                    num1 = num2;
                }
                this.setOperator(0);
                this.setOperatorSet(false);
                this.setNum1Set(true);
                this.setNum2(0);
                this.setPrevCalc(true);
                break;
        }
    }

    private void singleOperatorCalculator() throws Exception {
        switch (operator) {
            case 6:// euler

                num1 = Math.E;
                break;

            case 7://pi

                num1 = Math.PI;
                break;

            case 8://reciprocal

                if (num1 == 0) {
                    throw new Exception("ERROR:Inverse of zero is infinity");
                }
                num1 = 1 / num1;
                break;

            case 10://sin

                num1 = Math.sin(num1);
                break;

            case 11://cos

                num1 = Math.cos(num1);
                break;

            case 12://tan

                num1 = Math.tan(num1);
                break;
            case 13://asin

                num1 = Math.toDegrees(Math.asin(num1));
                break;

            case 14://acos

                num1 = Math.toDegrees(Math.acos(num1));
                break;

            case 15://atan

                num1 = Math.toDegrees(Math.atan(num1));
                break;

            case 9://factorial

                if (num1 < 0 || num1 >= 21 || num1 % 1 > 0) {
                    throw new Exception("ERROR:Must be a positive integer between 0 and 20");
                }
                if (num1 == 0) {
                    num1 = 1;
                } else {
                    for (int i = ((int) num1 - 1); i >= 1; i--) {
                        num1 *= i;
                    }
                }
                break;

        }
        setOperator(0);
        this.setPrevCalc(true);

    }

    private void Calculate() throws Exception {
        switch (operator) {
            case 1://add

                num1 = num1 + num2;

                break;

            case 2://subtract

                num1 = num1 - num2;

                break;
            case 3://multiply

                num1 = num1 * num2;

                break;
            case 4://divide

                if (num2 == 0) {
                    throw new Exception("ERROR:Divide by zero");
                } else {
                    num1 = num1 / num2;
                }
                break;

            case 5://exponent

                num1 = Math.pow(num1, num2);
        }
    }
}


