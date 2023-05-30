package com.yile.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //游戏主界面
    //定义二维数组（用于打乱图片及加载图片）
    int[][] newArr = new int[4][4];//[4][4]表示四行四列
    //x,y，记为空白图片在二维数组中的位置
    int x = 0;
    int y = 0;
    //定义一个变量用于存储图片路径
    String path = "PuzzlesGame\\sucai\\sucai\\image\\girl\\girl1\\";
    //定义一个正确顺序的二维数组用于判断胜利
    int[][] win = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
    //定义计步器
    int step = 0;
    //创建菜单选项中内容的对象，将其放置到成员位置方便判断事件
    JMenuItem replayJMenuItem = new JMenuItem("重新游戏");

    JMenuItem reloginJMenuItem = new JMenuItem("重新登录");
    JMenuItem closeJMenuItem = new JMenuItem("退出游戏");

    JMenuItem accountJMenuItem = new JMenuItem("公众号");
    JMenuItem girlJMenuItem = new JMenuItem("美女");
    JMenuItem animalJMenuItem = new JMenuItem("动物");
    JMenuItem sportJMenuItem = new JMenuItem("运动");

    //初始化游戏界面
    public GameJFrame() {
        //界面初始化
        initJFrame();

        //菜单初始化
        initJMenuBar();

        //数据初始化(打乱图片)
        initData();

        //图片初始化
        initImage();


        //使界面可视化，一般放在最后
        this.setVisible(true);
    }


    //数据初始化
    private void initData() {
        //打乱一维数组的数据，并按照4个一组的方式添加到二维数组中
        //定义一维数组
        int[] Arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
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

        //按照4个一组的方式添加到二维数组中
        //解法二：遍历二维数组，将一维数组中的元素赋值到二维数组中
        int index = 0;

        for (int i = 0; i < newArr.length; i++) {
            for (int j = 0; j < newArr.length; j++) {
                //获取空白方块的位置
                //因为文件中找不到0对应的图片，故会把0对应的图片加载为空白图片
                if (Arr[index] == 0) {
                    x = index / 4;
                    y = index % 4;
                }
                newArr[i][j] = Arr[index];
                index++;
            }
            System.out.println();
        }

    }

    //图片初始化
    private void initImage() {
        //清空之前已经加载的图片，若不清空会使后移动改变的图片被前面加载的图片覆盖而看不到改变后的图片
        this.getContentPane().removeAll();
        if (victory()) {
            //胜利，加载胜利图片
            //创建图片对象
            ImageIcon winIcon = new ImageIcon("D:\\java代码\\JigsawPuzzlesGame\\PuzzlesGame\\sucai\\sucai\\image\\win.png");
            //创建JLabel对象
            JLabel winJLabel = new JLabel(winIcon);
            //设置图片位置和大小
            winJLabel.setBounds(200, 300, 197, 73);
            //将胜利图片添加到隐藏容器中
            this.getContentPane().add(winJLabel);
        }

        //创建计步器的JLabel对象
        JLabel stepCount = new JLabel("步数：" + step);
        //设置计步器的宽高
        stepCount.setBounds(50, 30, 100, 20);
        //把其添加到隐藏容器中
        this.getContentPane().add(stepCount);

        //利用循环添加图片，优化代码
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //获取打乱后的图片
                int num = newArr[i][j];
                //创建ImageIcon对象,为图片对象
                ImageIcon icon = new ImageIcon(path + num + ".jpg");//优化路径
                //创建JLabel对象，为管理图片的容器
                JLabel jLabel = new JLabel(icon);
                //指定图片位置和大小
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                //添加图片边框
                jLabel.setBorder(new BevelBorder(1));
                //获取隐藏窗体并将JLabel放置在隐藏容器中
                this.getContentPane().add(jLabel);
            }
        }
        //放置背景图片（注意：先放置的图片会在上面，后放置的图片会插入到前面图片的下方）
        //创建图片对象
        ImageIcon backGroundIcon = new ImageIcon("PuzzlesGame\\sucai\\sucai\\image\\background.png");
        //创建JLabel对象
        JLabel jLabel = new JLabel(backGroundIcon);
        //指定背景图片的大小和位置
        jLabel.setBounds(40, 40, 508, 560);
        //获取隐藏窗体并将对应的JLabel对象放置在隐藏窗体中
        this.getContentPane().add(jLabel);

        //刷新界面
        this.getContentPane().repaint();


    }

    //菜单初始化
    private void initJMenuBar() {
        //创建整个菜单的对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上两个选项的对象,以及功能下的二级菜单的对象
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        JMenu ChangeJMenu = new JMenu("更换图片");



        //将选项中的内容添加到选项中
        functionJMenu.add(replayJMenuItem);
        functionJMenu.add(reloginJMenuItem);
        functionJMenu.add(closeJMenuItem);
        aboutJMenu.add(accountJMenuItem);
        //将changJMenu添加到功能选项下，注：JMenu可以同级嵌套
        functionJMenu.add(ChangeJMenu);
        //将girl,animal,sport添加到change菜单下
        ChangeJMenu.add(girlJMenuItem);
        ChangeJMenu.add(animalJMenuItem);
        ChangeJMenu.add(sportJMenuItem);


        //将选项添加到菜单上
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给选项内容绑定事件
        replayJMenuItem.addActionListener(this);
        reloginJMenuItem.addActionListener(this);
        closeJMenuItem.addActionListener(this);
        accountJMenuItem.addActionListener(this);

        girlJMenuItem.addActionListener(this);
        animalJMenuItem.addActionListener(this);
        sportJMenuItem.addActionListener(this);

        //给整个页面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置界面大小
        this.setSize(603, 680);
        //设置界面名称
        this.setTitle("拼图小游戏 V1.0");
        //设置界面关闭方法(0表示不进行任何操作，1表示点击关闭时隐藏页面，2表示要全部页面都关闭时程序才停止运行，3表示关闭这个界面则程序停止运行）
        this.setDefaultCloseOperation(3);
        //设置界面位置居中
        this.setLocationRelativeTo(null);
        //设置界面置顶（即不会在点击代码时被隐藏）
        this.setAlwaysOnTop(true);
        //取消隐藏窗体对图片的默认居中放置,使其可以按xy轴规则放置
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //按A键查看完整图片
        int code = e.getKeyCode();
        if (code == 65) {
            //清空之前的界面
            this.getContentPane().removeAll();
            //创建完整图片对象
            ImageIcon icon = new ImageIcon(path + "all.jpg");
            //创建JLabel对象
            JLabel jLabel = new JLabel(icon);
            //指定图片的大小和位置
            jLabel.setBounds(83, 134, 420, 420);
            //将JLabel对象添加到隐藏容器中
            this.getContentPane().add(jLabel);
            this.getContentPane().repaint();

            //加载背景图片
            //创建图片对象
            ImageIcon backGroundIcon = new ImageIcon("PuzzlesGame\\sucai\\sucai\\image\\background.png");
            //创建JLabel对象
            JLabel jLabel1 = new JLabel(backGroundIcon);
            //指定背景图片的大小和位置
            jLabel1.setBounds(40, 40, 508, 560);
            //获取隐藏窗体并将对应的JLabel对象放置在隐藏窗体中
            this.getContentPane().add(jLabel1);
            //刷新界面
            this.getContentPane().repaint();
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        //若满足胜利条件则不能再移动图片进行拼图
        if (victory()) {
            //结束方法
            return;
        }
        //获取键盘接受的按钮
        int code = e.getKeyCode();
        //给游戏添加移动的逻辑代码
        //左：37，上：38 右：39 下：40
        if (code == 37) {
            //索引越界判断
            if (y < 3) {
                //向左移动
                //让空白图片右边的图片向坐移动一位（交换二者位置）
                int temp = newArr[x][y];
                newArr[x][y] = newArr[x][y + 1];
                newArr[x][y + 1] = temp;
                y++;//空白图片的位置改变
                step++;//每移动一次步数加一
                //调用方法加载改变后的图片位置
                initImage();
            } else {
                System.out.println("已没有图片可以左移，请按其他方向键！");
            }
        } else if (code == 38) {
            //索引越界判断
            if (x < 3) {
                //向上移动
                //让空白图片下方的图片向上移动一位
                int temp = newArr[x][y];
                newArr[x][y] = newArr[x + 1][y];
                newArr[x + 1][y] = temp;
                x++;
                step++;//每移动一次步数加一
                //调用方法
                initImage();
            } else {
                System.out.println("已没有图片可以上移，请按其他方向键！");
            }
        } else if (code == 39) {
            //索引越界判断
            if (y > 0) {
                //向右移动
                //让空白图片的左边图片向右移动一位
                int temp = newArr[x][y];
                newArr[x][y] = newArr[x][y - 1];
                newArr[x][y - 1] = temp;
                y--;
                step++;//每移动一次步数加一
                //调用方法
                initImage();
            } else {
                System.out.println("已没有图片可以右移，请按其他方向键！");
            }
        } else if (code == 40) {
            //索引越界判断
            if (x > 0) {
                //向下移动
                //让空白图片上方的图片向下移动一位
                int temp = newArr[x][y];
                newArr[x][y] = newArr[x - 1][y];
                newArr[x - 1][y] = temp;
                x--;
                step++;//每移动一次步数加一
                //调用方法
                initImage();
            } else {
                System.out.println("已没有图片可以下移，请按其他方向键！");
            }
        }
        //松开按键A使图片回到拼图打乱的界面
        else if (code == 65) {
            this.initImage();
        }
        //作弊码，按W键一键通关
        else if (code == 87) {
            //清空界面
            this.getContentPane().removeAll();
            //将完整图片的二位数组赋值给打乱的二维数组
            newArr = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0},
            };
            x = y = 3;//修改空白图片的索引值
            //加载图片
            this.initImage();

        } else {
            System.out.println(code);
        }


    }

    //写一个方法用于判断胜利
    public boolean victory() {
        //遍历原二维数组，将其于正确顺序的二维数组win比较，若顺序全部相同，则返回true,否则返回false
        for (int i = 0; i < newArr.length; i++) {
            for (int j = 0; j < newArr[i].length; j++) {
                if (newArr[i][j] != win[i][j]) {
                    //只要有一个不相等则返回false
                    return false;
                }
            }
        }
        //代码走到这里则说明循环结束，原数组中的图片顺序与win数组中的全部相同
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //创建一个object对象用于接收动作监听到的事件
        Object obj = e.getSource();
        if (obj == replayJMenuItem) {
            System.out.println("重新游戏");
            //重新游戏
            //计步器清零，
            step = 0;
            //调用方法打乱数组
            this.initData();
            //调用方法重新加载图片
            this.initImage();
        }
        else if(obj == reloginJMenuItem){
            //重新登陆
            System.out.println("重新登录");
            //关闭当前页面
            this.setVisible(false);
            //打开登录页面
            new LoginJFrame();
        }
        else if(obj == closeJMenuItem){
            //关闭游戏
            System.out.println("关闭游戏");
            System.exit(0);//关闭虚拟机
        }
        else if(obj == accountJMenuItem){
            //公众号
            System.out.println("关于公众号");
            //创建弹窗对象
            JDialog jDialog = new JDialog();
            //创建图片管理容器JLabel
            JLabel jLabel = new JLabel(new ImageIcon("PuzzlesGame\\sucai\\sucai\\image\\about.png"));
            //设置图片位置和大小
            jLabel.setBounds(0,0,258,258);
            //将图片添加到弹框中
            jDialog.getContentPane().add(jLabel);
            //给弹框设置大小
            jDialog.setSize(344,344);
            //设置弹窗置顶
            jDialog.setAlwaysOnTop(true);
            //给弹框设置居中
            jDialog.setLocationRelativeTo(null);
            //设置弹窗关闭方式，使其不关闭则无法对进行其他操作
            jDialog.setModal(true);
            //让弹窗显示出来
            jDialog.setVisible(true);
        }
        else if(obj == girlJMenuItem){
            //更换美女图片
            //在美女图片中随机一张，并打乱重新加载
            //定义字符串数组用于随机图片路径
            String[] girlArr = {"girl1","girl2","girl3","girl4","girl5","girl6","girl7","girl8","girl9","girl10","girl11","girl12","girl13"};
            //定义随机数
            Random r = new Random();
            int index = r.nextInt(girlArr.length);
            path = "PuzzlesGame\\sucai\\sucai\\image\\girl\\" + girlArr[index] + "\\";
            //调用方法打乱图片
            this.initData();
            //调用方法重新加载图片
            this.initImage();
        }
        else if(obj == animalJMenuItem){
            //更换动物图片
            //定义动物图片数组
            String[] animalArr = {"animal1","animal2","animal3","animal4","animal5","animal6","animal7","animal8"};
            //定义随机数
            Random r = new Random();
            int index = r.nextInt(animalArr.length);
            path = "PuzzlesGame\\sucai\\sucai\\image\\animal\\" + animalArr[index] + "\\";
            //调用方法打乱图片
            this.initData();
            //调用方法加载新图片
            this.initImage();
        }
        else if(obj == sportJMenuItem){
            //更换运动图片
            //定义运动图片数组
            String[] sportArr = {"sport1","sport2","sport3","sport4","sport5","sport6","sport7","sport8","sport9","sport10"};
            //定义随机数
            Random r = new Random();
            int index = r.nextInt(sportArr.length);
            path = "PuzzlesGame\\sucai\\sucai\\image\\sport\\" + sportArr[index] + "\\";
            //调用方法打乱图片
            this.initData();
            //调用方法加载新图片
            this.initImage();
        }

    }
}
