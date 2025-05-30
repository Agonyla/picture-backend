package com.agony.picturebackend.util;

import cn.hutool.core.io.FileUtil;

/**
 * @author: Agony
 * @create: 2025/5/10 20:50
 * @describe: 颜色转换工具类
 */
public class ColorTransformUtils {

    private ColorTransformUtils() {
        // 工具类不需要实例化
    }

    /**
     * 获取标准颜色（将数据万象的 5 位色值转为 6 位）
     *
     * @param color
     * @return
     */
    public static String getStandardColor(String color) {
        // 每一种 rgb 色值都有可能只有一个 0，要转换为 00)
        // 如果是六位，不用转换，如果是五位，要给第三位后面加个 0
        // 示例：
        // 0x080e0 => 0x0800e
        if (color.length() == 7) {
            color = color.substring(0, 4) + "0" + color.substring(4, 7);
        }
        return color;
    }


    public static String expandHexColor(String compressed) {
        // 去除可能存在的0x前缀
        String input = compressed.startsWith("0x") ? compressed.substring(2) : compressed;
        int length = input.length();
        // 长度为3直接返回
        if (length == 3) {
            return "0x000000";
        }
        int index = 0;
        StringBuilder expanded = new StringBuilder();

        // 处理三个颜色分量
        for (int i = 0; i < 3; i++) {
            char current = input.charAt(index);
            if (current == '0') {
                // 当前分量是00的情况
                expanded.append("00");
                index++;
            } else {
                // 正常分量处理（可能包含补零）
                if (index + 1 < length) {
                    expanded.append(current).append(input.charAt(index + 1));
                    index += 2;
                } else {
                    // 最后一个字符单独处理，补零
                    expanded.append(current).append('0');
                    index += 2;
                }
            }
        }

        return "0x" + expanded.toString();
    }

    public static void main(String[] args) {
        // 测试用例
        System.out.println(getStandardColor("000"));     // 0x000000
        System.out.println(getStandardColor("0a00"));    // 0x00a000
        System.out.println(getStandardColor("a0b40"));   // 0xa0b400
        System.out.println(getStandardColor("0ab0"));    // 0x00ab00
        System.out.println(getStandardColor("00ab"));   // 0x0000ab
        System.out.println(getStandardColor("0ab00"));  // 0x00ab00


        System.out.println(expandHexColor("000"));     // 0x000000
        System.out.println(expandHexColor("0a00"));    // 0x00a000
        System.out.println(expandHexColor("a0b40"));   // 0xa0b400
        System.out.println(expandHexColor("0ab0"));    // 0x00ab00
        System.out.println(expandHexColor("00ab"));   // 0x0000ab
        System.out.println(expandHexColor("0ab00"));  // 0x00ab00

        String url = "https://www.codefather.cn/logo.png";
        System.out.println(FileUtil.mainName(url));
    }
}
