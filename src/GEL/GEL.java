package GEL;

import javax.swing.JFrame;


public final class GEL extends JFrame {

    private final int OFFSET = 30;

    public GEL() {
        InitUI();
    }

    public void InitUI() {
        Board board = new Board();
        add(board);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(board.getBoardWidth() + OFFSET,
                board.getBoardHeight() + 2*OFFSET);
        setLocationRelativeTo(null);
        setTitle("Guardie e Ladri");
    }


    public static void main(String[] args) {
        GEL GEL = new GEL();
        GEL.setVisible(true);
    }
}