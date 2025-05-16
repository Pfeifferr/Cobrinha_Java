package game_cobrinha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    int screenWidth;
    int screenHeight;
    int unitSize;
    int gameUnits;
    int delay = 75;
    int[] x;
    int[] y;
    int bodyParts = 6;
    int applesEaten = 0;
    int appleX;
    int appleY;
    char direction = 'R'; // R = Right, L = Left, U = Up, D = Down
    boolean running = false;
    Timer timer;
    Random random;

    public GamePanel() {
        random = new Random();
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
    }

    public void setDimensions(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
        this.unitSize = Math.min(width, height) / 30; // Ajusta a unidade dinamicamente
        this.gameUnits = (screenWidth * screenHeight) / (unitSize * unitSize);
        x = new int[gameUnits]; // Inicializa o array x
        y = new int[gameUnits]; // Inicializa o array y
        newApple(); // Garante que a maçã esteja sempre dentro dos limites
        startGame(); // Inicia o jogo quando as dimensões forem definidas
        repaint();
    }

    public void startGame() {
        newApple();
        running = true;
        applesEaten = 0;
        bodyParts = 6;
        direction = 'R';
        x = new int[gameUnits]; // Reinicia as coordenadas
        y = new int[gameUnits]; // Reinicia as coordenadas
        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            g.setColor(Color.red); // Altera a cor da maçã
            g.fillOval(appleX, appleY, unitSize, unitSize);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green); // Altera a cor da cabeça da cobra
                    g.fillRect(x[i], y[i], unitSize, unitSize);
                } else {
                    g.setColor(new Color(45, 180, 0)); // Altera a cor do corpo da cobra
                    g.fillRect(x[i], y[i], unitSize, unitSize);
                }
            }

            g.setColor(Color.white);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (screenWidth - metrics.stringWidth("Score: " + applesEaten)) / 2,
                    g.getFont().getSize());
        } else {
            gameOver(g);
        }
    }

    public void newApple() {
        // Garante que a maçã fique dentro dos limites e em múltiplos de unitSize
        appleX = (random.nextInt((screenWidth / unitSize))) * unitSize;
        appleY = (random.nextInt((screenHeight / unitSize))) * unitSize;
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U' -> y[0] -= unitSize;
            case 'D' -> y[0] += unitSize;
            case 'L' -> x[0] -= unitSize;
            case 'R' -> x[0] += unitSize;
        }
    }

    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions() {
        for (int i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }

        // Limites da tela
        if (x[0] < 0 || x[0] >= screenWidth || y[0] < 0 || y[0] >= screenHeight) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (screenWidth - metrics1.stringWidth("Game Over")) / 2, screenHeight / 2 - 50);

        g.setColor(Color.white);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (screenWidth - metrics2.stringWidth("Score: " + applesEaten)) / 2,
                screenHeight / 2 + 10);

        String restartMessage = "Press 'R' to Restart";
        g.drawString(restartMessage, (screenWidth - metrics2.stringWidth(restartMessage)) / 2, screenHeight / 2 + 70);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT, KeyEvent.VK_A -> {
                    if (direction != 'R')
                        direction = 'L';
                }
                case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> {
                    if (direction != 'L')
                        direction = 'R';
                }
                case KeyEvent.VK_UP, KeyEvent.VK_W -> {
                    if (direction != 'D')
                        direction = 'U';
                }
                case KeyEvent.VK_DOWN, KeyEvent.VK_S -> {
                    if (direction != 'U')
                        direction = 'D';
                }
                case KeyEvent.VK_R -> {
                    if (!running)
                        startGame(); // Reinicia o jogo
                }
            }
        }
    }
}
