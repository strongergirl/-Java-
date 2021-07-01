package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class myUI extends JPanel{

}

public class UI extends JFrame{
    private static final long serialVersionUID = 5952689219411916553L; //序列化字段
    private static JTextField a[][] = new JTextField[9][9];  //存储文本框中的数字
    static int ans[][] = new int[9][9];  //存储输入后的两位数组
    SudokuPuzzleGenerator example = new SudokuPuzzleGenerator();//创建一个对象
    public int right[][] = example.generatePuzzleMatrix();//获取生成的数独游戏
    public int rightans[][];
    SudokuSolve getanswer=new SudokuSolve();
    public char changer[][]=new char[9][9];
    public int rightanswer[][]=new int[9][9];
    public int getcus[][]=new int[9][9];
    private int[][] Wk(int a[][]){    //挖空 随机将矩阵中部分数据挖空
        Random r = new Random();
        int a1, a2;
        a1 = r.nextInt(9);//0~8随机数
        a2 = r.nextInt(9);
        for(int i = 0; i < 50; i++)
        {
            a[a1][a2] = 0;
            a1 = r.nextInt(9);
            a2 = r.nextInt(9);
        }
        return a;
    }
    private int[][] Wk2(int a[][]){    //挖空 随机将矩阵中部分数据挖空
        Random r = new Random();
        int a1, a2;
        a1 = r.nextInt(9);//0~8随机数
        a2 = r.nextInt(9);
        for(int i = 0; i < 100; i++)
        {
            a[a1][a2] = 0;
            a1 = r.nextInt(9);
            a2 = r.nextInt(9);
        }
        return a;
    }


    public UI(){
        Container c = getContentPane();//创建一个容器
        c.setLayout(new BorderLayout(2, 1));  //边框布局
        JMenuItem ok = new JMenuItem("提交");  //定义菜单
        JMenuItem restart = new JMenuItem("重新开始");  //定义菜单
        JMenuItem fresh = new JMenuItem("刷新");
        JMenuItem explain = new JMenuItem("详情");
        JMenuItem answer = new JMenuItem("正确答案");
        JMenuItem cusbutton =new JMenuItem("自主定义数独");
        JMenuItem subbutton =new JMenuItem("提交自定义数独");
        JMenuItem primary =new JMenuItem("初级/开始");
        JMenuItem senior =new JMenuItem("高级/开始");
        JPanel panel = new JPanel();  //定义一个容器
        JPanel panel1 = new JPanel();  //定义一个容器
        panel.add(ok);     //将菜单在容器内显示
        panel.add(explain);
        panel.add(answer);
        panel1.add(cusbutton);
        panel1.add(subbutton);
        panel1.add(restart);
        panel1.add(fresh);
        panel.add(primary);
        panel.add(senior);
        JPanel p1 = new JPanel();  //定义9行9列的网格布局
        JPanel p3 = new JPanel(new GridLayout(9, 9, 5, 5));  //定义9行9列的网格布局
        p3.setPreferredSize(new Dimension(540, 480));
        p1.add(p3,BorderLayout.CENTER);
        JPanel p4 = new JPanel();  //定义9行9列的网格布局
        add(panel,BorderLayout.NORTH);   //将菜单放置在北面
        add(panel1,BorderLayout.SOUTH);   //将菜单放置在北面
        add(p1,BorderLayout.CENTER);   //将数字放置在正中间
        rightans = Wk(right);//将获取已经进行挖空的数组放到rightans中
        //将挖空好的数字进行输出
        for(int k = 0;k < 9; k ++)
        {
            for(int n = 0;n < 9;n++)
            {
                if(rightans[k][n] != 0)//将保留的数字直接进行输出
                {
                    a[k][n] = new JTextField("" + rightans[k][n]);
                    a[k][n].setHorizontalAlignment(JTextField.CENTER);//将数字水平居中
                    a[k][n].setEditable(false);   //只可显示不可修改
                    p3.add(a[k][n]);     //添加文本框
                }
                else//挖空的位置直接保留为可进行修改的文本框
                {
                    a[k][n] = new JTextField();
                    a[k][n].setHorizontalAlignment(JTextField.CENTER);
                    p3.add(a[k][n]);
                }
            }
        }
        add(p1);   //将数字面板显示在容器里

        //匿名创建事件监听器 提交函数
        ok.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                if(gettext() == 1)
                {
                    if(judge() == true)
                    {
                        JOptionPane.showMessageDialog(null, "Your answer is right!","Result",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Your answer is wrong!","Result",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        //获取正确结果
        answer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                add(p4);
                p4.removeAll();
                JPanel p2=new JPanel(new GridLayout(9,9,1,1));
                p2.setPreferredSize(new Dimension(200, 200));
                p4.add(p2);
                //将矩阵转换为char[][]类型来获取最后的结果
                for(int i=0;i<9;i++)
                {
                    for(int j=0;j<9;j++)
                    {
                        if(rightans[i][j]==0)
                            changer[i][j]='.';
                        else
                            changer[i][j]=(char)(rightans[i][j]+'0');
                    }
                }
                System.out.println("aa");
                rightanswer=getanswer.solveSudoku(changer);//通过解数独的函数获取解
                int tmp=0;
                for(int k = 0;k < 9; k ++)
                {
                    for(int n = 0;n < 9;n++)
                    {
                        System.out.println(rightanswer[k][n]);
                        if(rightanswer[k][n]!=0)
                            tmp=1;
                        a[k][n] = new JTextField("" + rightanswer[k][n]);
                        a[k][n].setHorizontalAlignment(JTextField.CENTER);//将数字水平居中
                        a[k][n].setEditable(false);   //只可显示不可修改
                        p2.add(a[k][n]);     //添加文本框
                    }
                }
                p4.updateUI();
                p4.revalidate();//对panel1面板中的组件重新布局并绘制
                p4.repaint();
                if(tmp==0)
                    JOptionPane.showMessageDialog(null, p4,"错误\n该数独无解",JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, p4,"正确答案",JOptionPane.PLAIN_MESSAGE);

            }
        });

        //输入自定义数独
        cusbutton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                p1.removeAll();
                JPanel p2=new JPanel(new GridLayout(9,9,1,1));
                p2.setPreferredSize(new Dimension(540, 480));
                add(p2,BorderLayout.CENTER);   //将数字放置在正中间
                for(int k = 0;k < 9; k ++)
                {
                    for(int n = 0;n < 9;n++)
                    {
                        a[k][n] = new JTextField();
                        a[k][n].setHorizontalAlignment(JTextField.CENTER);
                        p2.add(a[k][n]);
                    }
                }
                p1.add(p2);
                p1.updateUI();
                p1.revalidate();//对panel1面板中的组件重新布局并绘制
                p1.repaint();
            }
        }));

        //提交自定义数独并获取答案
        subbutton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int i,j;
                for(i = 0; i < 9; i++)
                {
                    for(j = 0; j < 9 ; j ++)
                    {
                        rightans[i][j] = 0;
                    }
                }
                for(int k = 0;k < 9; k++)
                {
                    for(int n = 0;n < 9; n++)
                    {
                        try   //异常处理
                        {
                            rightans[k][n] = Integer.parseInt(a[k][n].getText());
                            //将答案类型转换之后传给ans
                        }
                        catch(NumberFormatException nfe)
                        {
                            rightans[k][n] = 0;
                        }
                    }
                }
                JOptionPane.showMessageDialog(null,"提交成功","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        }));
        explainListenerClass listener2 = new explainListenerClass();
        explain.addActionListener(listener2);

        //重新开始函数
        restart.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("aa");
                p1.removeAll();
                JPanel p2=new JPanel(new GridLayout(9,9,1,1));
                p2.setPreferredSize(new Dimension(540, 480));
                add(p2,BorderLayout.CENTER);   //将数字放置在正中间
                for(int k = 0;k < 9; k ++)
                {
                    for(int n = 0;n < 9;n++)
                    {
                        if(rightans[k][n] != 0)//将保留的数字直接进行输出
                        {
                            a[k][n] = new JTextField("" + rightans[k][n]);
                            a[k][n].setHorizontalAlignment(JTextField.CENTER);//将数字水平居中
                            a[k][n].setEditable(false);   //只可显示不可修改
                            p2.add(a[k][n]);     //添加文本框
                        }
                        else//挖空的位置直接保留为可进行修改的文本框
                        {
                            a[k][n] = new JTextField();
                            a[k][n].setHorizontalAlignment(JTextField.CENTER);
                            p2.add(a[k][n]);
                        }
                    }
                }
                p1.add(p2);
                p1.updateUI();
                p1.revalidate();//对panel1面板中的组件重新布局并绘制
                p1.repaint();
            }
        }));

        //刷新函数
        fresh.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                p1.removeAll();
                JPanel p2=new JPanel(new GridLayout(9,9,1,1));
                SudokuPuzzleGenerator tmp = new SudokuPuzzleGenerator();//创建一个对象
                right=tmp.generatePuzzleMatrix();
                rightans = Wk(right);
                add(p2,BorderLayout.CENTER);   //将数字放置在正中间
                for(int k = 0;k < 9; k ++)
                {
                    for(int n = 0;n < 9;n++)
                    {
                        if(rightans[k][n] != 0)//将保留的数字直接进行输出
                        {
                            a[k][n] = new JTextField("" + rightans[k][n]);
                            a[k][n].setHorizontalAlignment(JTextField.CENTER);//将数字水平居中
                            a[k][n].setEditable(false);   //只可显示不可修改
                            p2.add(a[k][n]);     //添加文本框
                        }
                        else//挖空的位置直接保留为可进行修改的文本框
                        {
                            a[k][n] = new JTextField();
                            a[k][n].setHorizontalAlignment(JTextField.CENTER);
                            p2.add(a[k][n]);
                        }
                    }
                }
                p2.setPreferredSize(new Dimension(540, 480));
                p1.add(p2);
                p1.updateUI();
                p1.revalidate();//对panel1面板中的组件重新布局并绘制
                p1.repaint();
            }
        }));

        //初级
        primary.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                p1.removeAll();
                JPanel p2=new JPanel(new GridLayout(9,9,1,1));
                p2.setPreferredSize(new Dimension(540, 480));
                SudokuPuzzleGenerator tmp = new SudokuPuzzleGenerator();//创建一个对象
                right=tmp.generatePuzzleMatrix();
                rightans = Wk(right);
                //add(p2,BorderLayout.CENTER);   //将数字放置在正中间
                for(int k = 0;k < 9; k ++)
                {
                    for(int n = 0;n < 9;n++)
                    {
                        if(rightans[k][n] != 0)//将保留的数字直接进行输出
                        {
                            a[k][n] = new JTextField("" + rightans[k][n]);
                            a[k][n].setHorizontalAlignment(JTextField.CENTER);//将数字水平居中
                            a[k][n].setEditable(false);   //只可显示不可修改
                            p2.add(a[k][n]);     //添加文本框
                        }
                        else//挖空的位置直接保留为可进行修改的文本框
                        {
                            a[k][n] = new JTextField();
                            a[k][n].setHorizontalAlignment(JTextField.CENTER);
                            p2.add(a[k][n]);
                        }
                    }
                }
                p1.add(p2);
                p1.updateUI();
                p1.revalidate();//对panel1面板中的组件重新布局并绘制
                p1.repaint();
            }
        }));

        //高级
        senior.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SudokuPuzzleGenerator tmp = new SudokuPuzzleGenerator();//创建一个对象
                right=tmp.generatePuzzleMatrix();
                rightans = Wk2(right);
                p1.removeAll();
                JPanel p2=new JPanel(new GridLayout(9,9,1,1));
                p2.setPreferredSize(new Dimension(540, 480));
                p1.add(p2);
                p1.updateUI();
                p1.revalidate();//对panel1面板中的组件重新布局并绘制
                p1.repaint();
                //add(p2,BorderLayout.CENTER);   //将数字放置在正中间
                for(int k = 0;k < 9; k ++)
                {
                    for(int n = 0;n < 9;n++)
                    {
                        if(rightans[k][n] != 0)//将保留的数字直接进行输出
                        {
                            a[k][n] = new JTextField("" + rightans[k][n]);
                            a[k][n].setHorizontalAlignment(JTextField.CENTER);//将数字水平居中
                            a[k][n].setEditable(false);   //只可显示不可修改
                            p2.add(a[k][n]);     //添加文本框
                        }
                        else//挖空的位置直接保留为可进行修改的文本框
                        {
                            a[k][n] = new JTextField();
                            a[k][n].setHorizontalAlignment(JTextField.CENTER);
                            p2.add(a[k][n]);
                        }
                    }
                }
                setVisible(true);  //窗口可视化
            }
        }));
    }

    //获取文本框的文字
    static int gettext()
    {
        int i,j;
        for(i = 0; i < 9; i++)
        {
            for(j = 0; j < 9 ; j ++)
            {
                ans[i][j] = 0;
            }
        }
        for(int k = 0;k < 9; k++)
        {
            for(int n = 0;n < 9; n++)
            {
                try   //异常处理
                {
                    ans[k][n] = Integer.parseInt(a[k][n].getText());
                    //将答案类型转换之后传给ans
                }
                catch(NumberFormatException nfe)
                {
                    JOptionPane.showMessageDialog(null,"数据中包括非数字，请重新输入","警告",JOptionPane.WARNING_MESSAGE);
                    return 0;
                }
            }
        }
        return 1;
    }

    //判断输入的答案是否正确
    public static boolean judge()
    {
        int i,j,k;
        int [][]answer = ans;

        for(i = 0; i < 9; i ++)
        {
            if(judge9(answer[i]) == false)  //判断每列是否有重复数字，第一个参数
                return false;
        }
        for(j = 0; j < 9; j ++)     //判断每行是否有重复数字
        {

            int[] newAnswerColumn = new int[9];
            for(i = 0; i < 9; i ++)
            {
                newAnswerColumn[i] = answer[i][j];//转化为一维数组
            }
            if(judge9(newAnswerColumn) == false)
                return false;
        }
        for(i = 0; i < 3; i ++)   //判断每个小九宫格内是否有重复数字
        {
            for(j = 0; j < 3; j ++)
            {
                k = 0;
                int[] newAnswer = new int[9];
                for(int m = i * 3; m < i * 3 + 3; m ++)
                {
                    for(int n = j * 3; n < j * 3 + 3; n ++)
                    {
                        newAnswer[k] = answer[m][n];
                        k++;
                    }
                }
                if(judge9(newAnswer) == false)
                {
                    return false;
                }
            }
        }
        return true;
    }

    //判断是否有相同的数字
    public static boolean judge9(int[] answer)
    {
        int i,j;
        for(i = 0; i < 9; i ++)
        {
            for(j = 0; j < 9; j ++)
            {
                if(i == j)
                    continue;
                if(answer[i] == answer[j])  //如果有重复的数字，返回false
                {
                    return false;
                }
            }
        }
        return true;  //没有重复数字，返回true
    }

    //主函数
    public static void main(String[] args) {
        JFrame frame = new UI();
        frame.setTitle("SuDoku");
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

//事件监听器
class explainListenerClass implements ActionListener{
    public void actionPerformed(ActionEvent e){
        JOptionPane.showMessageDialog(null, "填入数字保证每行每列及每个小的九宫格内数字无重复\n" +
                "点击下方刷新可开始新的数独游戏\n"+
                "点击重新开始可以清空已经添加的数字重新开始游戏\n"+
                "点击自主定义数独即可自己写入数字，对于数独中需要填写的位置可以直接留空白或者填0，\n"+
                "注意请保证自定义的数独是有解的数独\n"+
                "点击提交自定义数独提示提交成功后即可点击正确答案获得自定义数独的答案\n"+
                "可以选择数独的难度等级，默认为初级（刷新时也默认初级刷新）\n"+
                "每点击一次初级或高级默认刷新\n"+
                "made by tengteng","Explain",JOptionPane.INFORMATION_MESSAGE);
    }
}
