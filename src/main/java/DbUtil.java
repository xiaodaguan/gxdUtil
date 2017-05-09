import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * Created by guanxiaoda on 17/5/8.
 */

public class DbUtil {


    private static Logger logger = LoggerFactory.getLogger(DbUtil.class);


    private static JdbcTemplate template = null;


    private static BasicDataSource dataSource = new BasicDataSource();
    private final static String URL = "jdbc:oracle:thin:@172.18.79.3:1521/orcl";
    private final static String UNAME = "QDTRAMGRRED";
    private final static String UPASS = "qdtramgrred";
    static {

        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl(URL);
        dataSource.setUsername(UNAME);
        dataSource.setPassword(UPASS);
        dataSource.setMaxActive(5);
        template = new JdbcTemplate(dataSource);

    }


    public static void main(String[] args) {
        logger.info("read data");
        System.out.println("read data");
        SqlRowSet rs = template.queryForRowSet("select id,url from bbs_data");
        logger.info("updating...");
        System.out.println("updating");
        int count=0;
        while(rs.next()){
            count++;
            int id = rs.getInt("id");
            System.out.println("updating: id{"+id+"}.");
            String url = rs.getString("url");

            String md5 = StringUtil.MD5(url);
            int updateRow = template.update("update bbs_data set md5 = '"+md5+"' where id = "+id );
            if(updateRow ==0){
                logger.error("update failed: id = {}", id);
                System.err.println("x: id: "+id);
            }
            else{
                logger.info("updated:");
                System.out.println("√");
            }
        }
    }
}
