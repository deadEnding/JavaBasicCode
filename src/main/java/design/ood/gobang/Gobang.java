package design.ood.gobang;

/**
 * 五子棋游戏设计
 */

public class Gobang {

    private int rows;
    private int cols;

    private Board board;

    public Gobang(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public void start() {
        board = new Board(rows, cols);
    }

    public void reset() {
        board = new Board(rows, cols);
    }

    public int place(int x, int y, Piece piece) throws Exception {
        return board.place(x, y, piece);
    }
}
