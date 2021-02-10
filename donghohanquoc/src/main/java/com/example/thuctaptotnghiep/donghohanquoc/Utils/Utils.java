package com.example.thuctaptotnghiep.donghohanquoc.Utils;

import java.text.Normalizer;

public class Utils {
    public static String formatStringtoUrl(String  value)
    {
        value= value.replace("","");
        return ConvertVietnameseToEnglish(value.toLowerCase());
    }
    // class Class này cung cấp các phương thức để tiêu chuẩn hóa văn bản Unicode
    // thành văn bản tương đương.
    // Nhằm mục đích thuận tiện cho việc sắp xếp và tìm kiếm chuỗi.
    public static String ConvertVietnameseToEnglish(String src)
    {
        //Tiêu chuẩn hóa chuỗi s được truyền vào theo định dạng NFD.
        // Kết quả trả về là chuỗi đã được tiêu chuẩn hóa.
        // link đọc https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/replaceAll
        return Normalizer.normalize(src, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
