package GEL;

import javax.swing.*;


public final class GEL extends JFrame {

    public GEL() {
        InitUI();
    }

    private void InitUI() {
        Board board = new Board();
        add(board);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int OFFSET = 30;
        setSize(board.getBoardWidth() + OFFSET,
                board.getBoardHeight() + 2* OFFSET);
        setLocationRelativeTo(null);
        setTitle("Guardie e Ladri");
    }


    public static void main(String[] args) {
        GEL GEL = new GEL();
        GEL.setVisible(true);
        new ServerThread();
    }
}