package job4j.tictactoe;

import javafx.scene.Node;

import java.util.Arrays;
import java.util.function.Predicate;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isWinnerX() {
        return  horizontalCheck(Figure3T::hasMarkX) ||
                verticalCheck(Figure3T::hasMarkX) ||
                this.fillBy(Figure3T::hasMarkX, 0,0, 1, 1) ||
                this.fillBy(Figure3T::hasMarkX, this.table.length - 1 , 0, -1, 1);
    }

    public boolean isWinnerO() {
        return horizontalCheck(Figure3T::hasMarkO) ||
                verticalCheck(Figure3T::hasMarkO) ||
                this.fillBy(Figure3T::hasMarkO, 0,0, 1, 1) ||
                this.fillBy(Figure3T::hasMarkO, this.table.length - 1, 0, -1, 1);
    }

    public boolean horizontalCheck(Predicate<Figure3T> predicate) {
        for (int i = 0; i < this.table.length; i++) {
            if(this.fillBy(predicate, 0, i, 1, 0)){
                return true;
            }
        }
        return false;
    }

    public boolean verticalCheck(Predicate<Figure3T> predicate) {
        for (int i = 0; i < this.table.length; i++) {
            if(this.fillBy(predicate, i, 0, 0, 1)){
                return true;
            }
        }
        return false;
    }

    public boolean hasGap() {
        return   Arrays.stream(this.table)
                .flatMap(i -> Arrays.stream(i))
                .anyMatch(c -> c.hasMarkO() != true && c.hasMarkX() != true);
    }
}
