package design.ood.gobang;

public class Board {

    private int rows;
    private int cols;
    private int[][] board;

    private int[][] left;
    private int[][] right;
    private int[][] top;
    private int[][] bottom;

    private int winPlayer;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        board = new int[rows][cols];
        left = new int[rows][cols];
        right = new int[rows][cols];
        top = new int[rows][cols];
        bottom = new int[rows][cols];
    }

    public boolean valid(int x, int y) {
        return x >= 0 && y >= 0 && x < rows && y < cols && board[x][y] == 0 && winPlayer == 0;
    }

    public int place(int x, int y, Piece piece) throws Exception {
        if (!valid(x, y)) {
            throw new Exception();
        }

        int player = piece.getPlayer();
        board[x][y] = player;

        left[x][y] = y == 0 || board[x][y - 1] != player ? 0 : left[x][y - 1] + 1;
        right[x][y] = y == cols - 1 || board[x][y + 1] != player ? 0 : right[x][y + 1] + 1;
        top[x][y] = x == 0 || board[x - 1][y] != player ? 0 : top[x - 1][y] + 1;
        bottom[x][y] = x == rows - 1 || board[x + 1][y] != player ? 0 : bottom[x + 1][y] + 1;

        if (left[x][y] == 4 || right[x][y] == 4 || top[x][y] == 4 || bottom[x][y] == 4) {
            return winPlayer = player;
        }
        return 0;
    }
}
