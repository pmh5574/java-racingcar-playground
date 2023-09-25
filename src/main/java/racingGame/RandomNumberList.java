package racingGame;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumberList {

    private Random random = new Random();
    private List<Integer> randomNumbers;

    private final int MIN = 0;

    private final int MAX = 9;

    public RandomNumberList(Integer carsSize) {
        this.randomNumbers = setRandomNumbers(carsSize);
    }

    private List<Integer> setRandomNumbers(Integer carsSize) {
        return IntStream.range(0, carsSize)
                .mapToObj(i -> random.nextInt(MAX - MIN + 1) + MIN)
                .collect(Collectors.toList());
    }

    public RandomNumberList(List<Integer> randomNumbers) {
        this.randomNumbers = limitCheck(randomNumbers);
    }

    private List<Integer> limitCheck(List<Integer> randomNumbers) {
        IntStream.range(0, randomNumbers.size())
                .forEach(randomNumber -> {
                    if (MIN > randomNumber || MAX < randomNumber) {
                        throw new RuntimeException();
                    }
                });
        return randomNumbers;
    }

    public List<Integer> getRandomNumbers() {
        return randomNumbers;
    }
}
