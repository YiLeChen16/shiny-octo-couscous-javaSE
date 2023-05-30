package Test;

import java.util.Random;

public class test2 {
    //打乱一维数组的数据，并按照4个一组的方式添加到二维数组中
    public static void main(String[] args) {
        //定义一维数组
        int[] Arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //打乱一维数组
        //遍历数组，获取每一个元素，并将获取的元素与随机到的索引的元素进行交换，达到打乱数据的目的
        //定义随机数
        Random r = new Random();
        for (int i = 0; i < Arr.length; i++) {
            //获取随机索引
            int index = r.nextInt(Arr.length);
            //交换元素
            int temp = Arr[i];
            Arr[i] = Arr[index];
            Arr[index] = temp;
        }
        //遍历一维数组验证结果
        for (int i = 0; i < Arr.length; i++) {
            System.out.print(Arr[i] + ", ");
        }
        System.out.println();

        //定义二维数组
        int[][] newArr = new int[4][4];//[4][4]表示四行四列
        //按照4个一组的方式添加到二维数组中
        //解法二：遍历二维数组，将一维数组中的元素赋值到二维数组中
        int num = 0;
        for (int i = 0; i < newArr.length; i++) {
            for (int j = 0; j < newArr.length; j++) {
               newArr[i][j] = Arr[num];
               num++;
            }
            System.out.println();
        }
        //遍历二维数组验证结果
        for (int i = 0; i < newArr.length; i++) {
            for (int j = 0; j < newArr.length; j++) {
                System.out.print(newArr[i][j] + ", ");
            }
            System.out.println();
        }



    }

}
