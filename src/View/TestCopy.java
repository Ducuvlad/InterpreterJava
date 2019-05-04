package View;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestCopy {
    public TestCopy(Integer i) {
        this.i = i;
    }

    public Integer i;
    public static void main(String[] args)
    {
        IntStream in=IntStream.range(0,10);
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,14,15,16);
        Integer j=numbers.stream().filter(i->i%4==0).mapToInt(i->i+1).sum();
        Integer k=numbers.stream()
                .filter(x-> x%4 == 0)
                .mapToInt(x->x+1)
                .sum();

        String h=numbers.stream().filter(i->i%2==0 || i%3==0).map(i->"A"+i.toString()+"B").collect(Collectors.joining());

        System.out.println(h);
    }
}
