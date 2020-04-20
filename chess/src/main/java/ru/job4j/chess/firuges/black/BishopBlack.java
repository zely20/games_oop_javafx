package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        if (!isDiagonal(source, dest)) {
            throw new IllegalStateException(
                    String.format("Could not way by diagonal from %s to %s", source, dest)
            );
        }
        int size = Math.abs(dest.y - source.y);
        Cell[] steps = new Cell[size];
        int deltaX = 0;
        int deltaY = 0;
        if (source.x < dest.x && source.y < dest.y) {
            deltaX = 1;
            deltaY = 1;
        } else if (source.x > dest.x && source.y > dest.y) {
            deltaX = -1;
            deltaY = -1;
        } else if (source.x > dest.x && source.y < dest.y) {
            deltaX = -1;
            deltaY = 1;
        } else if (source.x < dest.x && source.y > dest.y){
            deltaX = 1;
            deltaY = -1;
        }
        int x = source.x;
        int y = source.y;
        for (int index = 0; index < size; index++) {
            x += deltaX;
            y += deltaY;
            steps[index] = Cell.findBy(x, y);
        }
          return steps;
    }

    public boolean isDiagonal(Cell source, Cell dest) {
        if(Math.abs(dest.x-source.x) == Math.abs(dest.y - source.y)){
            return true;
        }
        return false;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
