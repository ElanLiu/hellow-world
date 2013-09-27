/**
 * 
 */
package com.helloworld.demo.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author yu.liuyly
 * 
 *         1、加载JDBC驱动程序： 
 *         2、提供JDBC连接的URL,协议：子协议：数据源标识 
 *         3、创建数据库的连接 
 *         4、创建一个Statement
 *         5、执行SQL语句 
 *         6、处理结果 
 *         7、关闭JDBC对象
 * 
 *         xiazai：mysql-connector-java-5.1.17-bin.jar JAR包，
 *         然后放进jdk1.6.0_37\jre\lib\ext 重启eclispe 就可以在JRE系统库中看到
 */
public class JDBCDemo {

	private static final Logger logger = Logger.getLogger(JDBCDemo.class);

	// 协议
	private static final String protocal = "jdbc";

	// 子协议
	private static final String subProtocal = "mysql";

	// 数据标示源
	private static final String dataSource = "//10.232.31.124:3306/yunostbchannel?useUnicode=true&amp;characterEncoding=UTF-8&amp;zeroDateTimeBehavior=convertToNull";

	private static final String userName = "yunostbchannel";

	private static final String passwd = "yunostbchannel";

	private static final String jdbcDriver = "com.mysql.jdbc.Driver";

	public JDBCDemo() {
		
		//初始化log4j的配置文件
		PropertyConfigurator.configure("src/main/resources/config/log4j.properties");
		
		
		// 1、加载JDBC驱动程序：
		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			logger.error("找不到驱动程序类 ，加载驱动失败！", e);
		}

		// 2、提供JDBC连接的URL,协议：子协议：数据源标识
		String conURL = protocal + ":" + subProtocal + ":" + dataSource;

		// 3、创建数据库的连接
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(conURL, userName, passwd);
		} catch (SQLException e) {
			logger.error("数据库连接失败！", e);
		}

		// 4、创建一个Statement/动态statement
		Statement st = null;
		PreparedStatement pst = null;
		String dynamicSql = "select * from yunostbchannel.c2b_order where id=?";
		try {
			st = conn.createStatement();
			pst = conn.prepareStatement(dynamicSql);
		} catch (SQLException e) {
			logger.error("数据库创建statement失败！", e);
		}

		// 5、执行SQL语句
		ResultSet rs = null;
		ResultSet rs2 = null;
		String querySql = "select * from yunostbchannel.c2b_order ";
		//String insertSql = "insert into yunostbchannel.c2b_order values";
		String updateSql = "update yunostbchannel.c2b_order set price='1111' where id=2";

		try {
			rs = st.executeQuery(querySql);
			//int rows = st.executeUpdate(updateSql) ;
			pst.setInt(1, 2);
			rs2 = pst.executeQuery() ;
			//logger.info(rows);
		} catch (SQLException e) {
			logger.error("数据库获取结果集失败！", e);
		}

		// 6、处理结果
		try {
			while (rs2.next()) {
				logger.info(rs2.getString("items"));
			}
		} catch (SQLException e) {
			logger.error("处理结果集失败！", e);
		}

		// 7、关闭JDBC对象
		if (rs != null) { // 关闭记录集
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (st != null) { // 关闭声明
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) { // 关闭连接对象
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new JDBCDemo();

	}

}
