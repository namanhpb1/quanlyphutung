package quanlyphutung;

public class SparePart {
    private String maPT;
    private String tenPT;
    private String loaiPT;
    private int soLuong;
    private double donGia;
    private String viTriKho;
    private String nhaCungCap;

    public SparePart() {
    }

    public SparePart(String maPT, String tenPT, String loaiPT,
                     int soLuong, double donGia,
                     String viTriKho, String nhaCungCap) {
        this.maPT = maPT;
        this.tenPT = tenPT;
        this.loaiPT = loaiPT;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.viTriKho = viTriKho;
        this.nhaCungCap = nhaCungCap;
    }

    public String getMaPT() {
        return maPT;
    }

    public void setMaPT(String maPT) {
        this.maPT = maPT;
    }

    public String getTenPT() {
        return tenPT;
    }

    public void setTenPT(String tenPT) {
        this.tenPT = tenPT;
    }

    public String getLoaiPT() {
        return loaiPT;
    }

    public void setLoaiPT(String loaiPT) {
        this.loaiPT = loaiPT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getViTriKho() {
        return viTriKho;
    }

    public void setViTriKho(String viTriKho) {
        this.viTriKho = viTriKho;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }
}
