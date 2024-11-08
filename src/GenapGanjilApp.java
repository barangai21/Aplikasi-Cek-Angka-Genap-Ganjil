import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GenapGanjilApp {
    
    // Membuat frame utama
    private JFrame frame;
    private JTextField textField;
    private JLabel resultLabel;
    private JButton cekButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GenapGanjilApp window = new GenapGanjilApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Konstruktor untuk inisialisasi GUI
    public GenapGanjilApp() {
        frame = new JFrame("Cek Nomor Genap/Ganjil");
        frame.setBounds(100, 100, 400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        // Label instruksi
        JLabel instructionLabel = new JLabel("Masukkan angka:");
        frame.getContentPane().add(instructionLabel);

        // TextField untuk input angka
        textField = new JTextField();
        textField.setColumns(10);
        frame.getContentPane().add(textField);

        // Menambahkan KeyAdapter untuk validasi input hanya angka
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume(); // Membatalkan input jika bukan angka
                }
            }
        });

        // Menambahkan FocusListener untuk membersihkan input saat fokus
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setText(""); // Membersihkan textField saat mendapatkan fokus
            }
        });

        // Tombol untuk cek
        cekButton = new JButton("Cek");
        cekButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mendapatkan input dari textField
                String input = textField.getText();
                
                // Validasi apakah input kosong atau tidak valid
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Harap masukkan angka!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int number = Integer.parseInt(input);

                        // Memeriksa apakah angka genap atau ganjil
                        if (number % 2 == 0) {
                            resultLabel.setText("Hasil: " + number + " adalah Genap");
                        } else {
                            resultLabel.setText("Hasil: " + number + " adalah Ganjil");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Input tidak valid. Masukkan angka yang benar.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        frame.getContentPane().add(cekButton);

        // Label untuk hasil
        resultLabel = new JLabel("Hasil: ");
        frame.getContentPane().add(resultLabel);
    }
}