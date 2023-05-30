package Test;

import javax.swing.*;
import javax.xml.transform.Source;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class MyJFrame implements MouseListener {
    //创建按钮对象
    JButton jbt = new JButton("点我啊");
    JButton jbt1 = new JButton("先点我！");


    public MyJFrame() {

        //创建窗体的对象
        JFrame jFrame = new JFrame();
        //设置窗体的宽高
        jFrame.setSize(603,680);
        //设置窗体的标题
        jFrame.setTitle("事件演示");
        //设置窗体置顶
        jFrame.setAlwaysOnTop(true);
        //设置窗体的关闭模式
        jFrame.setDefaultCloseOperation(3);
        //设置窗体居中
        jFrame.setLocationRelativeTo(null);
        //关闭隐藏窗体对图片默认居中的放置,使图片可以按xy轴的规则放置
        jFrame.setLayout(null);


        //设置按钮位置及大小
        jbt.setBounds(0,0,100,100);
        jbt1.setBounds(100,2,100,150);
        //设置按钮的动作监听
        jbt.addMouseListener(this);
        jbt1.addMouseListener(this);
        //获取隐藏容器并将按钮放进去
        jFrame.getContentPane().add(jbt);
        jFrame.getContentPane().add(jbt1);

        //将整个界面显示出来
        jFrame.setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Object ob = e.getSource();
        if(ob == jbt1){
            jbt1.setSize(200,200);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Random r = new Random();

        //获取当前被操作的按钮对象
        Object ob = e.getSource();
        //对按钮进行判断
        if(ob == jbt){
            int random1 = r.nextInt(500);
            int random2 = r.nextInt(400);
            jbt.setBounds(random1,random2,150,100);
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
