package org.lili.cucumber;

/**
 * @author lili
 * @date 2020/12/22 0:27
 * @notes
 */
public class FizzBuzz {
    public String play(int number) {
        if (number == 0) throw new IllegalArgumentException("Number Error");
        if (isMultipleOf(number, 3) && isMultipleOf(number, 5)) return "FizzBuzz";
        if (isMultipleOf(number, 3)) return "Fizz";
        if (isMultipleOf(number, 5)) return "Buzz";
        return String.valueOf(number);
    }

    private boolean isMultipleOf(int number, int i) {
        return number % i == 0;
    }
}
