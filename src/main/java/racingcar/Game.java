package racingcar;

import java.util.ArrayList;

public class Game {
    private final int totalTurns;
    private int curTurn = 0;
    private final Car[] cars;

    public Game(int totalTurns, String[] names) {
        this(totalTurns, namesToCars(names));
    }

    public Game(int totalTurns, Car[] cars) {
        this.totalTurns = totalTurns;
        this.cars = cars;
    }

    public void play() {
        for (int i = 0; i < cars.length; i++) {
            moveCar(i, RandomNumGenerator.generateRandomNum());
        }
        printCars();
        curTurn++;
    }

    public boolean isEnd() {
        return curTurn == totalTurns;
    }

    public void printCars() {
        for (Car car : cars) {
            car.print();
        }
        System.out.println();
    }

    public void printWinners() {
        Car[] winners = this.findWinners();
        System.out.printf("%s가 최종 우승했습니다.\n", Game.carsToNames(winners));
    }

    public Car[] findWinners() {
        ArrayList<Car> winnersList = new ArrayList<>();
        for (Car car : cars) {
            updateWinnerListWithCar(winnersList, car);
        }
        return winnersList.toArray(new Car[winnersList.size()]);
    }

    public void moveCar(int carIdx, int randomNumber) {
        if (randomNumber >= 4) {
            cars[carIdx].move();
        }
    }

    private void updateWinnerListWithCar(ArrayList<Car> winners, Car car) {
        if (winners.isEmpty() || isEqualToWinnerPosition(winners, car)) {
            winners.add(car);
            return;
        }
        if (isBetterThanWinnerPosition(winners, car)) {
            winners.clear();
            winners.add(car);
        }
    }

    private boolean isBetterThanWinnerPosition(ArrayList<Car> winners, Car car) {
        return car.getPosition() > winners.get(0).getPosition();
    }

    private boolean isEqualToWinnerPosition(ArrayList<Car> winners, Car car) {
        return car.getPosition() == winners.get(0).getPosition();
    }

    private static Car[] namesToCars(String[] names) {
        Car[] cars = new Car[names.length];
        for (int i = 0; i < names.length; i++) {
            cars[i] = new Car(names[i]);
        }
        return cars;
    }

    private static String carsToNames(Car[] cars) {
        StringBuilder sb = new StringBuilder();
        for (Car car : cars) {
            sb.append(car.getName()).append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
