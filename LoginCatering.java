package form;

import database.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginCatering extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel infoLabel;
    private JButton loginButton;
    private Connection connection;

    public LoginCatering() {
        setTitle("Catering TOP One");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inisialisasi koneksi
        connection = DatabaseConnection.getConnection();

        // Fullscreen dengan frame standar (title bar dan tombol minimize/maximize/close)
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true); // pastikan resizable agar tombol maximize aktif

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(253, 240, 222,255));
        add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Judul
        JLabel titleLabel = new JLabel("Catering TOP One", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setForeground(new Color(0, 0, 0, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(titleLabel, gbc);

        // LOGO (Memuat gambar asli dari file)
        ImageIcon originalIcon = new ImageIcon("images/logocoba.jpg");
        // Mengambil objek Image dari ImageIcon
        Image originalImage = originalIcon.getImage();
        // Mengatur ukuran baru (misalnya width=150, height=100)
        int newWidth = 250;
        int newHeight = 250;
        // Melakukan resize gambar dengan kualitas smooth
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        // Membuat ImageIcon baru dari gambar yang sudah diresize
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        // Membuat JLabel dengan ImageIcon yang sudah diresize
        JLabel logoLabel = new JLabel(resizedIcon);
        // Mengatur posisi logo di bawah judul
        gbc.gridx = 0;
        gbc.gridy = 1;       // Baris di bawah judul (judul di baris 0)
        gbc.gridwidth = 2;   // Lebar 2 kolom sama seperti judul
        gbc.insets = new Insets(0, 0, 20, 0);  // Memberi jarak atas dan bawah
        // Menambahkan logo ke panel
        panel.add(logoLabel, gbc);

        // Informasi di bawah logo
        infoLabel = new JLabel("Harap masukkan Username dan Password dengan benar.", SwingConstants.CENTER);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        infoLabel.setForeground(new Color(0, 119, 255, 255));
        // Mengatur posisi infoLabel tepat di bawah logo
        gbc.gridx = 0;
        gbc.gridy = 2;           // Baris tepat di bawah logo (logo di 1)
        gbc.gridwidth = 2;       // Lebar sama dengan logo dan judul
        gbc.insets = new Insets(10, 0, 10, 0);  // Jarak atas dan bawah bisa disesuaikan

        panel.add(infoLabel, gbc);

        // Menentukan ukuran baru untuk icon (misal tinggi sama dengan tinggi font, lebar disesuaikan rasio)
        int iconHeight = 30;  // Sama dengan ukuran font, bisa disesuaikan
        int iconWidth = (int) ((double) originalIcon.getIconWidth() / originalIcon.getIconHeight() * iconHeight);

        // Memuat gambar asli dari file
        originalIcon = new ImageIcon("images/user.jpg");
        // Mengambil objek Image dari ImageIcon
        originalImage = originalIcon.getImage();
        // Resize gambar dengan kualitas smooth
        resizedImage = originalImage.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        // Membuat ImageIcon baru dari gambar yang sudah diresize
        resizedIcon = new ImageIcon(resizedImage);
        // Membuat JLabel dengan teks dan ikon yang sudah diresize
        JLabel usernameLabel = new JLabel("Username :", resizedIcon, JLabel.LEFT);
        // Mengatur font tulisan
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        // Mengatur posisi dan layout menggunakan GridBagConstraints
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 35, 10, 5);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        // Menambahkan label ke panel
        panel.add(usernameLabel, gbc);



        // Field Username dengan border kotak yang jelas dan ukuran tidak terlalu panjang
        usernameField = new JTextField(15);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameField.setBackground(Color.WHITE);
        usernameField.setForeground(Color.BLACK);
        usernameField.setBorder(new LineBorder(new Color(100, 100, 100), 2));
        usernameField.setCaretColor(Color.BLACK);

        gbc.gridx = 1;
        gbc.gridy = 3; // sesuaikan dengan posisi di layout
        gbc.fill = GridBagConstraints.NONE;  // jangan melebar mengikuti cell
        gbc.weightx = 0;                     // tidak mengambil ruang ekstra
        gbc.anchor = GridBagConstraints.WEST; // posisi rata kiri

        panel.add(usernameField, gbc);

        // Memuat gambar asli dari file
        originalIcon = new ImageIcon("images/pass.png");
        // Mengambil objek Image dari ImageIcon
        originalImage = originalIcon.getImage();
        // Resize gambar dengan kualitas smooth
        resizedImage = originalImage.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        // Membuat ImageIcon baru dari gambar yang sudah diresize
        resizedIcon = new ImageIcon(resizedImage);
        // Membuat JLabel dengan teks dan ikon di sebelah kiri
        JLabel passwordLabel = new JLabel("Password :", resizedIcon, JLabel.LEFT);
        // Mengatur font teks
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        // Jika ingin mengatur jarak antara ikon dan teks (opsional)
        passwordLabel.setIconTextGap(8); // jarak 8 piksel, bisa disesuaikan
        // Mengatur posisi dan layout menggunakan GridBagConstraints
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 35, 10, 5);
        gbc.anchor = GridBagConstraints.EAST;
        // Menambahkan label ke panel
        panel.add(passwordLabel, gbc);


        // Field Password dengan border kotak yang jelas dan ukuran tidak terlalu panjang
        passwordField = new JPasswordField(15); // 10 kolom agar lebih pendek
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(Color.BLACK);
        passwordField.setBorder(new LineBorder(new Color(100, 100, 100), 2));
        passwordField.setCaretColor(Color.BLACK);

        // Atur GridBagConstraints
        gbc.gridx = 1;
        gbc.gridy = 4; // sesuaikan dengan posisi
        gbc.fill = GridBagConstraints.NONE; // agar tidak melebar
        gbc.weightx = 0; // agar tidak mengambil ruang ekstra
        gbc.anchor = GridBagConstraints.WEST; // posisi rata kiri

        panel.add(passwordField, gbc);

        // Button Login
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        // Action listener tombol login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        // Enter key triggers login
        passwordField.addActionListener(e -> performLogin());
        usernameField.addActionListener(e -> performLogin());
    }

    private void performLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            infoLabel.setText("Harap mengisi Username dan Password terlebih dahulu");
            infoLabel.setForeground(new Color(183, 0, 255,255));
            return;
        }

        try {
            // Cek username dulu
            String sql = "SELECT * FROM tbuser WHERE nim = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                if (!rs.next()) {
                    infoLabel.setText("Username dan Password salah.");
                    infoLabel.setForeground(Color.RED);
                    // Kosongkan username dan password jika username salah
                    usernameField.setText("");
                    passwordField.setText("");

                } else {
                    String correctPassword = rs.getString("password");
                    String nama = rs.getString("nama");

                    if (correctPassword.equals(password)) {
                        infoLabel.setText("Login berhasil! Selamat datang, " + nama + ".");
                        infoLabel.setForeground(new Color(0, 128, 0)); // hijau
                        // Reset form
                        usernameField.setText("");
                        passwordField.setText("");
                    } else {
                        infoLabel.setText("Password salah.");
                        infoLabel.setForeground(Color.RED);
                        passwordField.setText("");
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Terjadi kesalahan koneksi database:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginCatering app = new LoginCatering();
            app.setVisible(true);
        });
    }
}
