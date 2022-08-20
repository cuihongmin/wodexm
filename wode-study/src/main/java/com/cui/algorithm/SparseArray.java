package com.cui.algorithm;

import java.io.*;
import java.util.Arrays;

/**
 * @Descripttion 稀疏数组
 * @Author cuihongmin
 * @Date 2022/8/20 10:42
 */
public class SparseArray {
    public static void main(String[] args) throws Exception {
        // 先创建一个原始的二维数组11*11
        // 0：表示没有棋子，1表示黑子 2表示篮子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        // 输出原始二维数组
        System.out.println("原始的二维数组~~");
        for (int[] row:chessArr1) {
//            System.out.println(Arrays.toString(row));
            for (int data: row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        // 将二维数组 转 稀疏数组
        // 1.先遍历二维数组 得到非0数据的个数
        int sum = 0;
        for (int i = 0; i <11 ; i++) {
            for (int j = 0; j <11 ; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum=" + sum);
        // 2.创建对应的稀疏数组 （行=sum+1，列=3）
        int sparseArr[][] = new int[sum+1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = 11; // 第一行的第一列
        sparseArr[0][1] = 11; // 第一行的第二列
        sparseArr[0][2] = sum; // 第一行的第三列
        // 遍历二维数组，将非0的值存放到sparseArr中
        int count = 0; //用于记录是第几个非0数据
        for (int i = 0; i <11 ; i++) {
            for (int j = 0; j <11 ; j++) {
               if (chessArr1[i][j] != 0) {
                   count++;
                   sparseArr[count][0] = i;
                   sparseArr[count][1] = j;
                   sparseArr[count][2] = chessArr1[i][j];
               }
            }
        }
        // 输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为~~~~");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }

        // 将稀疏数组写入文件中，文件名为map.data
        System.out.println("将稀疏数组写入文件map.data中");
        File file = new File("D:\\尚硅谷韩顺平老师数据结构分享\\map.data");
        FileOutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        System.out.println(sparseArr.length + "xijj");
        for (int i = 0; i <sparseArr.length ; i++) {

            if (i== sparseArr.length-1){
                // 此if...else的目的是最后一行的末尾不加","
                writer.write(sparseArr[i][0]+","+sparseArr[i][1]+","+sparseArr[i][2]);
            }else {
                writer.write(sparseArr[i][0]+","+sparseArr[i][1]+","+sparseArr[i][2]+",");
            }
        }
        writer.close();
        outputStream.close();
        System.out.println("写入成功");
        // 将稀疏数组 --》》恢复成原始的二维数组(首先读取文件)
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream, "utf-8");
        StringBuffer sb = new StringBuffer();
        System.out.println(reader.ready());
        while (reader.ready()) {
            sb.append((char) reader.read());//加入stringbuffer，转为char,不然只会打印地址
        }
        System.out.println("读出的文件" + sb.toString());
        reader.close();
        inputStream.close();

        // 把读出的文件，赋值给稀疏数组
        String [] str = sb.toString().split(",");
//        System.out.println(str.length/3); // 4
        int sparseArray3[][] = new int[str.length / 3][3];
        int q = 0;
        for (String s:str) {
//            System.out.println(s + "ssss");
//            System.out.println(q/3);
//            System.out.println(q%3);
            sparseArray3[q/3][q%3] = Integer.parseInt(s); // Integer.parseInt(s)是将整型数据Integer转换为基本数据类型int
            q++;
        }

        // 输出读取到的稀疏数组
        System.out.println("输出读取到的稀疏数组如下++=++==+==");
        for (int k = 0; k <sparseArray3.length ; k++) {
            System.out.printf("%d\t%d\t%d\t",sparseArray3[k][0],sparseArray3[k][1],sparseArray3[k][2]);
            System.out.println();
        }


        /**
        * 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的chessArr2 = int[11][11]
         * 2.在读取稀疏数组后几行的数据，并赋值给原始的二维数组即可
        *  */
        //1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int chessArr2[][] = new int[sparseArr[0][1]][sparseArr[0][1]]; // 拿到稀疏数组的第一行的第一列和第1行的第二列
        //2.在读取稀疏数组的后几行的数据(从第二行开始），并赋值给 原始的二维数组即可
        for (int i = 1; i <sparseArr.length ; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        // 输出恢复后的二维数组
        System.out.println("恢复后的二维数组~~~~");
        for (int[] row:chessArr2) {
            for (int data:row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
