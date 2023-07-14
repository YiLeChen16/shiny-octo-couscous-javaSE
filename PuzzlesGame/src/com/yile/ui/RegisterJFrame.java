package com.yile.ui;

import cn.hutool.core.io.FileUtil;
import com.yile.domain.User;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RegisterJFrame extends JFrame implements MouseListener {
    //定义一个集合用于接收注册页面传递过来的用户信息
    ArrayList<User> userInfoList;

    //提升三个输入框的变量的作用范围，让这三个变量可以在本类中所有方法里面可以使用。
    JTextField username = new JTextField();
    JTextField password = new JTextField();
    JTextField rePassword = new JTextField();

    //提升两个按钮变量的作用范围，让这两个变量可以在本类中所有方法里面可以使用。
    JButton submit = new JButton();
    JButton reset = new JButton();


    public RegisterJFrame(ArrayList<User> list) {
        //将接收到的用户信息集合传递给全局变量userInfoList
        userInfoList = list;
        initFrame();
        initView();
        setVisible(true);
    }

    //鼠标点击
    @Override
    public void mouseClicked(MouseEvent e) {
        //判断点击了哪个按钮
        if(e.getSource() == submit){
            //点击了注册按钮
            //对用户输入的信息进行判断
            //1.输入框是否为空
            if(username.getText().length() == 0 || password.getText().length() == 0 || rePassword.getText().length() == 0){
                showDialog("用户名或密码不能为空！");
                return;
            }
            //2.用户名是否符合要求
            if(!username.getText().matches("[a-zA-Z0-9]{4,16}")){
                showDialog("用户名录入不规范！用户名只能由大小写字母和数字组成，且长度在4-16之间");
            }
            //3.密码是否符合要求
            if(!password.getText().matches("\\S*(?=\\S{6,})(?=\\S*\\d)(?=\\S*[a-z])\\S*")){
                showDialog("密码录入不规范！至少包含一位数字，一位小写字母，长度大于等于6");
                return;
            }
            //4.两次密码输入是否一致
            if(!password.getText().equals(rePassword.getText())){
                showDialog("两次密码输入不一致！");
                return;
            }
            //5.用户名是否重复，若不重复则将其写入文件中并提示注册成功，跳转到登录页面
            if(isContain(username.getText())){
                //用户名重复
                showDialog("用户名已存在！");
            }
            else{
                //将新的用户信息存入集合中
                User user = new User(username.getText(),password.getText());
                userInfoList.add(user);
                //将更新后的数据写入文件中
                //利用hutool包中的工具类实现
                FileUtil.writeLines(userInfoList,"D:\\java代码\\JigsawPuzzlesGame\\PuzzlesGame\\userinfo.txt","utf-8");
                showDialog("注册成功！");
                try {
                    new LoginJFrame();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(e.getSource() == reset){
            //点击了重置按钮
            //清空三个输入框
            username.setText("");
            password.setText("");
            rePassword.setText("");
        }
    }

    //判断用户名是否重复
    private boolean isContain(String userName) {
        //遍历集合
        for (User user : userInfoList) {
            if(userName.equals(user.getUsername())){
                return true;
            }
        }
        //不重复
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == submit) {
            submit.setIcon(new ImageIcon("PuzzlesGame\\sucai\\sucai\\image\\register\\注册按下.png"));
        } else if (e.getSource() == reset) {
            reset.setIcon(new ImageIcon("PuzzlesGame\\sucai\\sucai\\image\\register\\重置按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == submit) {
            submit.setIcon(new ImageIcon("PuzzlesGame\\sucai\\sucai\\image\\register\\注册按钮.png"));
        } else if (e.getSource() == reset) {
            reset.setIcon(new ImageIcon("PuzzlesGame\\sucai\\sucai\\image\\register\\重置按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void initView() {
        //添加注册用户名的文本
        JLabel usernameText = new JLabel(new ImageIcon("PuzzlesGame\\sucai\\sucai\\image\\register\\注册用户名.png"));
        usernameText.setBounds(85, 135, 80, 20);

        //添加注册用户名的输入框
        username.setBounds(195, 134, 200, 30);

        //添加注册密码的文本
        JLabel passwordText = new JLabel(new ImageIcon("PuzzlesGame\\sucai\\sucai\\image\\register\\注册密码.png"));
        passwordText.setBounds(97, 193, 70, 20);

        //添加密码输入框
        password.setBounds(195, 195, 200, 30);

        //添加再次输入密码的文本
        JLabel rePasswordText = new JLabel(new ImageIcon("PuzzlesGame\\sucai\\sucai\\image\\register\\再次输入密码.png"));
        rePasswordText.setBounds(64, 255, 95, 20);

        //添加再次输入密码的输入框
        rePassword.setBounds(195, 255, 200, 30);

        //注册的按钮
        submit.setIcon(new ImageIcon("PuzzlesGame\\sucai\\sucai\\image\\register\\注册按钮.png"));
        submit.setBounds(123, 310, 128, 47);
        submit.setBorderPainted(false);
        submit.setContentAreaFilled(false);
        submit.addMouseListener(this);

        //重置的按钮
        reset.setIcon(new ImageIcon("PuzzlesGame\\sucai\\sucai\\image\\register\\重置按钮.png"));
        reset.setBounds(256, 310, 128, 47);
        reset.setBorderPainted(false);
        reset.setContentAreaFilled(false);
        reset.addMouseListener(this);

        //背景图片
        JLabel background = new JLabel(new ImageIcon("PuzzlesGame\\sucai\\sucai\\image\\register\\background.png"));
        background.setBounds(0, 0, 470, 390);

        this.getContentPane().add(usernameText);
        this.getContentPane().add(passwordText);
        this.getContentPane().add(rePasswordText);
        this.getContentPane().add(username);
        this.getContentPane().add(password);
        this.getContentPane().add(rePassword);
        this.getContentPane().add(submit);
        this.getContentPane().add(reset);
        this.getContentPane().add(background);
    }

    private void initFrame() {
        //对自己的界面做一些设置。
        //设置宽高
        setSize(488, 430);
        //设置标题
        setTitle("拼图游戏 V1.0注册");
        //取消内部默认布局
        setLayout(null);
        //设置关闭模式
        setDefaultCloseOperation(3);
        //设置居中
        setLocationRelativeTo(null);
        //设置置顶
        setAlwaysOnTop(true);
    }

    //只创建一个弹框对象
    JDialog jDialog = new JDialog();
    //因为展示弹框的代码，会被运行多次
    //所以，我们把展示弹框的代码，抽取到一个方法中。以后用到的时候，就不需要写了
    //直接调用就可以了。
    public void showDialog(String content){
        if(!jDialog.isVisible()){
            //把弹框中原来的文字给清空掉。
            jDialog.getContentPane().removeAll();
            JLabel jLabel = new JLabel(content);
            jLabel.setBounds(0,0,200,150);
            jDialog.add(jLabel);
            //给弹框设置大小
            jDialog.setSize(200, 150);
            //要把弹框在设置为顶层 -- 置顶效果
            jDialog.setAlwaysOnTop(true);
            //要让jDialog居中
            jDialog.setLocationRelativeTo(null);
            //让弹框
            jDialog.setModal(true);
            //让jDialog显示出来
            jDialog.setVisible(true);
        }
    }
}
