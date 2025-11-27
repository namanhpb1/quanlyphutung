-- ================================
--  DATABASE: QUAN LY PHU TUNG
--  Version: 1.0
--  Author: namanhpb1
--  Description:
--     Cơ sở dữ liệu quản lý phụ tùng
--     dùng cho đồ án Java Swing + MySQL
-- ================================

-- Tạo database
CREATE DATABASE IF NOT EXISTS quanlyphutung
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE quanlyphutung;

-- ================================
--  BẢNG PHUTUNG
-- ================================
DROP TABLE IF EXISTS PhuTung;

CREATE TABLE PhuTung (
    maPT        VARCHAR(20) PRIMARY KEY,
    tenPT       VARCHAR(100) NOT NULL,
    loaiPT      VARCHAR(50),
    soLuong     INT DEFAULT 0,
    donGia      DOUBLE DEFAULT 0,
    viTriKho    VARCHAR(50),
    nhaCungCap  VARCHAR(100)
);

-- ================================
--  DỮ LIỆU MẪU
-- ================================

INSERT INTO PhuTung (maPT, tenPT, loaiPT, soLuong, donGia, viTriKho, nhaCungCap) VALUES
('PT001', 'Lọc gió động cơ', 'Động cơ', 50, 150000, 'Kệ A1', 'Toyota Việt Nam'),
('PT002', 'Má phanh trước', 'Phanh',    30, 450000, 'Kệ B3', 'NCC Nhật Bản'),
('PT003', 'Dây curoa tổng', 'Động cơ',  20, 320000, 'Kệ C2', 'Honda Motor'),
('PT004', 'Lọc dầu động cơ', 'Động cơ', 60, 120000, 'Kệ A2', 'Petrolimex'),
('PT005', 'Bugi đánh lửa',   'Điện',     80, 90000,  'Kệ D1', 'NCC Hàn Quốc');

-- ================================
--  KẾT THÚC FILE SQL
-- ================================
