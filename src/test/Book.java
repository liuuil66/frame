package test;

/**
 * Created by 刘洋丽 on 2018/7/17.
 */



import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.*;
import java.util.*;


public class Book {
    /**
     *演示通过Book连接数据库
     */
//
    private void testInsertDate(String book_name, String book_publishers,String book_author, Date create_tiome,int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/book",
                    "root", "root");
//             String sql = "insert into acount (id,name,number) values (9,6,9)";
            String sql = "insert into account values('"+ book_name +"','"+ book_publishers +"','"+ book_author +"'+create_time"+ id +")";
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println("所影响的条数为："+(rows>0));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private  void testDeleteDate(int id){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/book",
                    "root", "root");
//            String sql = "delete from account where id=1";
            String sql = "delete from account where id=" +id +"";
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println(rows + "行被删除！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            ;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private  void testUpdateDate(String book_name, String book_publishers, String book_author,Date create_tiome,int id){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/book",
                    "root", "root");
//            String sql = "UPDATE account set number=23 where id=6";
            String sql = "update account set book_name='"+book_name+"',book_publishers='"+ book_publishers+"'book_author='"+book_author+"', create_time='create_time'where id= "+id+"";
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println(rows + "行被修改！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            ;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void foundAllDate(){
        Connection connection =getConnection();
        String sql = "select * from account";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            StringBuffer buffer = new StringBuffer();
            buffer.append("==========================================");
            buffer.append("id\t\t\taccount\t\t\tnumber\t\t\t");
            buffer.append("==========================================");
            while (resultSet.next()){

                String book_name = resultSet.getString("huozhe");
                String book_publishers = resultSet.getString("beijing");
                String book_author=resultSet.getString("teytoer");
                Date create_time=resultSet.getDate("");
                int id = resultSet.getInt(3);
                buffer.append(id+"|\t"+book_name+"|\t"+book_publishers+"|"+"|\t"+book_author+"|"+"|\t"+create_time+"|");
            }
            System.out.println(buffer.toString());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL="jdbc:mysql://127.0.0.1:3306/test";
            try {
                Connection connection = DriverManager.getConnection(dbURL,"root","root");
                return  connection;
            }catch (SQLException e){
                e.printStackTrace();
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        Book demo = new Book();
        demo.foundAllDate();
        Scanner scanner = new Scanner(System.in);
        System.out.println("================================================");
        System.out.println("|         欢迎使用HNB 11 人工智能系统           |");
        System.out.println("1、添加数据   2、修改数据  3、删除数据  4、查询所有书籍");
        System.out.println("================================================");
        System.out.println("请选择你的操作：");
        int select = 0;
        select = scanner.nextInt();
        while (select < 1 || select > 4) {
            System.out.println("选择的操作数不能识别，请重新选择：");
            select = scanner.nextInt();
        }
        String value = null;
        Book tet= new Book();
        if (select == 1) {
            System.out.println("请输入要添加的书籍的名称和ID，中间用逗号分隔。");
            value = scanner.next();
            String [] values = value.split(",");
            tet.testInsertDate(values[0],values[1],values[2],values[3],(int) System.currentTimeMillis());
        } else if (select == 2) {
            System.out.println("请输入要修改的书籍名称，出版商，作者，ID，逗号分隔，系统将根据ID修改");
            value = scanner.next();
            String [] values = value.split(",");
            tet.testUpdateDate(values[0],values[1],values[2],values[3],Integer.parseInt(values[4]));
        } else if (select == 3) {
            System.out.println("请输入要删除的ID");
            value = scanner.next();
            tet.testDeleteDate(Integer.parseInt(value));
        } else if(select==4){
            System.exit(-1);
        }

    }
}
