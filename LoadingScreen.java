package TestLoading2;

import javax.swing.*;
import java.awt.*;

public class LoadingScreen extends JFrame {
    private JProgressBar progressBar;

    public LoadingScreen() {
        setTitle("Loading...");
        setUndecorated(true); // Hilangkan border window
        setSize(1000, 600);
        setLocationRelativeTo(null); // Tengah layar
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Panel utama pakai GridBagLayout agar semua di tengah
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Spasi antar komponen

        // Logo
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("FB_IMG_1697680902791.jpg"));

        Image scaledImage = icon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        gbc.gridy = 0;
        mainPanel.add(logoLabel, gbc);

        // Progress bar
        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(300, 25));
        progressBar.setForeground(Color.WHITE);
        progressBar.setBackground(Color.DARK_GRAY);
        gbc.gridy = 1;
        mainPanel.add(progressBar, gbc);

        add(mainPanel); // Masukkan panel ke frame

        simulateLoading(); // Jalankan loading
    }

    private void simulateLoading() {
        Timer timer = new Timer(50, e -> {
            int val = progressBar.getValue();
            if (val < 100) {
                progressBar.setValue(val + 1);
            } else {
                ((Timer) e.getSource()).stop();
                dispose(); // Tutup loading screen
                showMainApp(); // Tampilkan aplikasi utama
            }
        });
        timer.start();
    }

    private void showMainApp() {
        JFrame mainFrame = new JFrame("Aplikasi Utama");
        mainFrame.setSize(1000, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.add(new JLabel("Selamat datang di Aplikasi!", SwingConstants.CENTER));
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoadingScreen().setVisible(true));
    }
}
