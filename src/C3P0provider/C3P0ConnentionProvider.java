package C3P0provider;

import java.io.FileInputStream;  
import java.sql.Connection;  
import java.sql.SQLException;  
import java.util.Properties;  
  
import javax.sql.DataSource;  
  
import com.mchange.v2.c3p0.DataSources;  
  
/** 
 * c3p0连接池管理类 
 * @author ICE 
 * 
 */  
public class C3P0ConnentionProvider {  
	
	private static C3P0ConnentionProvider instance ;
  
    private static final String JDBC_DRIVER = "driverClass";  
    private static final String JDBC_URL = "jdbcUrl";  
      
    private static DataSource ds;  
    /** 
     * 初始化连接池代码块 
     */  
//    static{  
//        initDBSource();  
//    }  
     
    public static C3P0ConnentionProvider getInstance()
    {
    	if(null == instance)
    	{
    		C3P0ConnentionProvider ccp = new C3P0ConnentionProvider();
    		ccp.initDBSource();
    		instance = ccp ;
    	}
    	return instance;
    }
      
    /** 
     * 初始化c3p0连接池 
     */  
//    private static final void initDBSource(){  
    private void initDBSource(){  
        Properties c3p0Pro = new Properties();  
        try {  
            //加载配置文件  
            c3p0Pro.load(new FileInputStream("H:/JavaWeb/workspace(myeclipse2016)/optionLog/src/c3p0.properties"));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }   
          
        String drverClass = c3p0Pro.getProperty(JDBC_DRIVER);  
        if(drverClass != null){  
            try {  
                //加载驱动类  
                Class.forName(drverClass).newInstance();  
            } catch (ClassNotFoundException e) {  
                e.printStackTrace();  
            } catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
              
        }  
          
        Properties jdbcpropes = new Properties();  
        Properties c3propes = new Properties();  
        for(Object key:c3p0Pro.keySet()){  
            String skey = (String)key;  
            if(skey.startsWith("c3p0.")){  
                c3propes.put(skey, c3p0Pro.getProperty(skey));  
            }else{  
                jdbcpropes.put(skey, c3p0Pro.getProperty(skey));  
            }  
        }  
          
        try {  
            //建立连接池  
            DataSource unPooled = DataSources.unpooledDataSource(c3p0Pro.getProperty(JDBC_URL),jdbcpropes);  
            ds = DataSources.pooledDataSource(unPooled,c3propes);  
              
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * 获取数据库连接对象 
     * @return 数据连接对象 
     * @throws SQLException  
     */  
//    public static synchronized Connection getConnection() throws SQLException{  
    public synchronized Connection getConnection() throws SQLException{  
        final Connection conn = ds.getConnection();  
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);  
        return conn;  
    }  
}  