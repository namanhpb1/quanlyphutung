package quanlyphutung;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SparePartFrame extends JFrame {

    private JTextField txtMaPT;
    private JTextField txtTenPT;
    private JTextField txtLoaiPT;
    private JTextField txtSoLuong;
    private JTextField txtDonGia;
    private JTextField txtViTriKho;
    private JTextField txtNCC;

    private JButton btnHienThi;
    private JButton btnThem;
    private JButton btnCapNhat;
    private JButton btnXoa;
    private JButton btnReset;

    private JTable tblPhuTung;
    private DefaultTableModel tableModel;

    private SparePartDAO dao = new SparePartDAO();

    public SparePartFrame() {
        initComponents();
        setLocationRelativeTo(null);
        loadDataToTable();
    }

    private void initComponents() {
        setTitle("Quan ly Phu Tung Nha Xuong");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);

        // Panel nhập liệu
        JPanel pnlInput = new JPanel(new GridLayout(7, 2, 5, 5));
        pnlInput.add(new JLabel("Ma phu tung:"));
        txtMaPT = new JTextField();
        pnlInput.add(txtMaPT);

        pnlInput.add(new JLabel("Ten phu tung:"));
        txtTenPT = new JTextField();
        pnlInput.add(txtTenPT);

        pnlInput.add(new JLabel("Loai phu tung:"));
        txtLoaiPT = new JTextField();
        pnlInput.add(txtLoaiPT);

        pnlInput.add(new JLabel("So luong:"));
        txtSoLuong = new JTextField();
        pnlInput.add(txtSoLuong);

        pnlInput.add(new JLabel("Don gia:"));
        txtDonGia = new JTextField();
        pnlInput.add(txtDonGia);

        pnlInput.add(new JLabel("Vi tri kho:"));
        txtViTriKho = new JTextField();
        pnlInput.add(txtViTriKho);

        pnlInput.add(new JLabel("Nha cung cap:"));
        txtNCC = new JTextField();
        pnlInput.add(txtNCC);

        // Panel nút
        JPanel pnlButtons = new JPanel();
        btnHienThi = new JButton("Hien thi");
        btnThem = new JButton("Them");
        btnCapNhat = new JButton("Cap nhat");
        btnXoa = new JButton("Xoa");
        btnReset = new JButton("Reset");

        pnlButtons.add(btnHienThi);
        pnlButtons.add(btnThem);
        pnlButtons.add(btnCapNhat);
        pnlButtons.add(btnXoa);
        pnlButtons.add(btnReset);

        // Bảng
        String[] columnNames = {
                "Ma PT", "Ten PT", "Loai", "So luong",
                "Don gia", "Vi tri kho", "Nha cung cap"
        };
        tableModel = new DefaultTableModel(columnNames, 0);
        tblPhuTung = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblPhuTung);

        // Add vào frame
        add(pnlInput, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(pnlButtons, BorderLayout.SOUTH);

        // Gán sự kiện
        btnHienThi.addActionListener(e -> loadDataToTable());
        btnThem.addActionListener(e -> addSparePart());
        btnCapNhat.addActionListener(e -> updateSparePart());
        btnXoa.addActionListener(e -> deleteSparePart());
        btnReset.addActionListener(e -> resetForm());

        // Click bảng -> đổ dữ liệu lên form
        tblPhuTung.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblPhuTung.getSelectedRow();
                if (row >= 0) {
                    txtMaPT.setText(tableModel.getValueAt(row, 0).toString());
                    txtTenPT.setText(tableModel.getValueAt(row, 1).toString());
                    txtLoaiPT.setText(tableModel.getValueAt(row, 2).toString());
                    txtSoLuong.setText(tableModel.getValueAt(row, 3).toString());
                    txtDonGia.setText(tableModel.getValueAt(row, 4).toString());
                    txtViTriKho.setText(tableModel.getValueAt(row, 5).toString());
                    txtNCC.setText(tableModel.getValueAt(row, 6).toString());

                    txtMaPT.setEditable(false); // không cho sửa mã khi update
                }
            }
        });
    }

    // Hiển thị toàn bộ
    private void loadDataToTable() {
        List<SparePart> list = dao.getAll();
        tableModel.setRowCount(0);
        for (SparePart sp : list) {
            Object[] row = new Object[]{
                    sp.getMaPT(),
                    sp.getTenPT(),
                    sp.getLoaiPT(),
                    sp.getSoLuong(),
                    sp.getDonGia(),
                    sp.getViTriKho(),
                    sp.getNhaCungCap()
            };
            tableModel.addRow(row);
        }
    }

    // Kiểm tra dữ liệu nhập
    private boolean validateForm() {
        if (txtMaPT.getText().trim().isEmpty()
                || txtTenPT.getText().trim().isEmpty()
                || txtLoaiPT.getText().trim().isEmpty()
                || txtSoLuong.getText().trim().isEmpty()
                || txtDonGia.getText().trim().isEmpty()
                || txtViTriKho.getText().trim().isEmpty()
                || txtNCC.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Khong duoc bo trong truong nao!",
                    "Loi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Integer.parseInt(txtSoLuong.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "So luong phai la so nguyen!",
                    "Loi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Double.parseDouble(txtDonGia.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Don gia phai la so!",
                    "Loi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    // Lấy dữ liệu từ form -> đối tượng
    private SparePart getSparePartFromForm() {
        String maPT = txtMaPT.getText().trim();
        String tenPT = txtTenPT.getText().trim();
        String loaiPT = txtLoaiPT.getText().trim();
        int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
        double donGia = Double.parseDouble(txtDonGia.getText().trim());
        String viTriKho = txtViTriKho.getText().trim();
        String ncc = txtNCC.getText().trim();

        return new SparePart(maPT, tenPT, loaiPT,
                soLuong, donGia, viTriKho, ncc);
    }

    // Thêm mới
    private void addSparePart() {
        if (!validateForm()) {
            return;
        }

        String maPT = txtMaPT.getText().trim();
        if (dao.findById(maPT) != null) {
            JOptionPane.showMessageDialog(this,
                    "Ma phu tung da ton tai!",
                    "Loi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SparePart sp = getSparePartFromForm();
        boolean ok = dao.insert(sp);
        if (ok) {
            JOptionPane.showMessageDialog(this,
                    "Them phu tung thanh cong!");
            loadDataToTable();
            resetForm();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Them that bai!",
                    "Loi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Cập nhật
    private void updateSparePart() {
        if (!validateForm()) {
            return;
        }
        SparePart sp = getSparePartFromForm();
        boolean ok = dao.update(sp);
        if (ok) {
            JOptionPane.showMessageDialog(this,
                    "Cap nhat thanh cong!");
            loadDataToTable();
            resetForm();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Cap nhat that bai!",
                    "Loi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Xoá
    private void deleteSparePart() {
        String maPT = txtMaPT.getText().trim();
        if (maPT.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Hay nhap/chon Ma PT de xoa!",
                    "Thong bao", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int c = JOptionPane.showConfirmDialog(this,
                "Ban co chac chan muon xoa phu tung nay?",
                "Xac nhan", JOptionPane.YES_NO_OPTION);
        if (c == JOptionPane.YES_OPTION) {
            boolean ok = dao.delete(maPT);
            if (ok) {
                JOptionPane.showMessageDialog(this,
                        "Xoa thanh cong!");
                loadDataToTable();
                resetForm();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Xoa that bai!",
                        "Loi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Reset form
    private void resetForm() {
        txtMaPT.setText("");
        txtTenPT.setText("");
        txtLoaiPT.setText("");
        txtSoLuong.setText("");
        txtDonGia.setText("");
        txtViTriKho.setText("");
        txtNCC.setText("");
        txtMaPT.setEditable(true);
        tblPhuTung.clearSelection();
    }

    // main
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SparePartFrame().setVisible(true);
            }
        });
    }
}
