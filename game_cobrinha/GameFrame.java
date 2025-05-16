package game_cobrinha;

import javax.swing.JFrame;
import java.awt.*;

public class GameFrame extends JFrame {

    GamePanel gamePanel;

    public GameFrame(boolean fullscreen) {
        gamePanel = new GamePanel();
        this.add(gamePanel);
        this.setTitle("Trabalho Artes");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        if (fullscreen) {
            enableFullScreen(); // Ativa a tela cheia
        } else {
            // Ajusta a janela para ocupar 50% da tela
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = (int) (screenSize.width * 0.5);
            int height = (int) (screenSize.height * 0.5);
            setSize(width, height); // Define 50% da largura e altura da tela
            setLocationRelativeTo(null); // Centraliza na tela
            this.setVisible(true);
            gamePanel.setDimensions(width, height); // Passa as dimensões para o painel
        }
    }

    private void enableFullScreen() {
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        device.setFullScreenWindow(this); // Coloca a janela em tela cheia
        gamePanel.setDimensions(getWidth(), getHeight()); // Passa as dimensões para o painel
    }

    public static void main(String[] args) {
        boolean fullscreen = true; // Defina para true para iniciar em tela cheia
        new GameFrame(fullscreen);
    }
}
