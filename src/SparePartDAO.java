package quanlyphutung;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SparePartDAO {

    public List<SparePart> getAll() {
        List<SparePart> list = new ArrayList<>();
        String sql = "SELECT * FROM PhuTung";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String maPT = rs.getString("maPT");
                String tenPT = rs.getString("tenPT");
                String loaiPT = rs.getString("loaiPT");
                int soLuong = rs.getInt("soLuong");
                double donGia = rs.getDouble("donGia");
                String viTriKho = rs.getString("viTriKho");
                String nhaCungCap = rs.getString("nhaCungCap");

                SparePart sp = new SparePart(maPT, tenPT, loaiPT,
                        soLuong, donGia, viTriKho, nhaCungCap);
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public SparePart findById(String maPT) {
        String sql = "SELECT * FROM PhuTung WHERE maPT = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maPT);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tenPT = rs.getString("tenPT");
                    String loaiPT = rs.getString("loaiPT");
                    int soLuong = rs.getInt("soLuong");
                    double donGia = rs.getDouble("donGia");
                    String viTriKho = rs.getString("viTriKho");
                    String nhaCungCap = rs.getString("nhaCungCap");

                    return new SparePart(maPT, tenPT, loaiPT,
                            soLuong, donGia, viTriKho, nhaCungCap);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(SparePart sp) {
        String sql = "INSERT INTO PhuTung(maPT, tenPT, loaiPT, soLuong, donGia, viTriKho, nhaCungCap) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sp.getMaPT());
            ps.setString(2, sp.getTenPT());
            ps.setString(3, sp.getLoaiPT());
            ps.setInt(4, sp.getSoLuong());
            ps.setDouble(5, sp.getDonGia());
            ps.setString(6, sp.getViTriKho());
            ps.setString(7, sp.getNhaCungCap());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(SparePart sp) {
        String sql = "UPDATE PhuTung " +
                "SET tenPT=?, loaiPT=?, soLuong=?, donGia=?, viTriKho=?, nhaCungCap=? " +
                "WHERE maPT=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sp.getTenPT());
            ps.setString(2, sp.getLoaiPT());
            ps.setInt(3, sp.getSoLuong());
            ps.setDouble(4, sp.getDonGia());
            ps.setString(5, sp.getViTriKho());
            ps.setString(6, sp.getNhaCungCap());
            ps.setString(7, sp.getMaPT());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String maPT) {
        String sql = "DELETE FROM PhuTung WHERE maPT=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maPT);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
