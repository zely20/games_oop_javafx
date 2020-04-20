package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BishopBlackTest {

    @Test
    public void positionIsStart() {
        BishopBlack black = new BishopBlack(Cell.C1);
        Cell rsl =  black.position();
        assertThat(rsl.toString(),is("C1"));
    }

    @Test
    public void copyTest() {
        BishopBlack black = new BishopBlack(Cell.C1);
        Figure rsl =  black.copy(Cell.A3);
        assertThat(rsl.position().toString(),is("A3"));
    }

    @Test
    public void wayTest() {
        BishopBlack black = new BishopBlack(Cell.C1);
        Cell[]rsl = black.way(Cell.C1,Cell.G5);
        Cell[]expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertThat(rsl,is(expected));
    }
}
