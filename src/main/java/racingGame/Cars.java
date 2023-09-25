package racingGame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Cars {

    private List<Car> cars;

    private int carSize;

    private String mode;

    private RandomNumberList randomNumList;

    public Cars(String carList) {
        this.cars = makeCar(carList);
        this.carSize = this.cars.size();
        this.mode = "random";
    }

    public Cars(String carList, List<Integer> randomNumList) {
        this.cars = makeCar(carList);
        this.randomNumList = new RandomNumberList(randomNumList);
        this.carSize = this.cars.size();
        this.mode = "test";
    }

    private List<Car> makeCar(String carList) {

        List<Car> carsList = new ArrayList<>();

        String[] strCars = carList.split(",");

        for (int i = 0; i < strCars.length; i++) {
            String car = strCars[i];
            carsList.add(new Car(car));
        }

        return carsList;
    }

    public String play(int repeatNum) {

        setRandomNumListNull();

        IntStream.range(0, repeatNum)
                .forEach(i -> {
                    setCarsLine(randomNumList);
                    printPlay();
                });

        int topNum = 0;

        for (int i = 0; i < this.carSize; i++) {
            topNum = getTopNum(topNum, i);
        }

        int finalTopNum = topNum;

        StringBuilder carsListNm = new StringBuilder();

        this.cars
                .forEach(car -> {
                    if (car.line == finalTopNum) {
                        carsListNm.append(",").append(car.carName);
                    }
                });

        return carsListNm.deleteCharAt(0)
                .toString();
    }

    private void setRandomNumListNull() {
        if (this.randomNumList == null) {
            this.randomNumList = new RandomNumberList(this.carSize);
        }
    }

    private int getTopNum(int topNum, int i) {
        if (topNum < this.cars.get(i).line) {
            topNum = this.cars.get(i).line;
        }
        return topNum;
    }

    private void setCarsLine(RandomNumberList list) {
        if (this.mode.equals("random")) {
            list = new RandomNumberList(this.carSize);
        }

        RandomNumberList finalList = list;

        IntStream.range(0, this.carSize)
                .forEach(i -> this.cars.get(i).play(finalList.getRandomNumbers().get(i)));
    }

    private void printPlay() {
        this.cars
                .forEach(carData -> System.out.println(carData.carName + " : " + carData.line));
    }

}
