/*データベース接続成功：com.mysql.cj.jdbc.ConnectionImpl@xxxxxxxx
レコード更新を実行します
1件のレコードが更新されました
数学・英語の点数が高い順に並べ替えました
1件目：生徒ID=5／氏名=武者小路勇気／数学=95／英語=80
2件目：生徒ID=2／氏名=刀沢彩香／数学=85／英語=70
3件目：生徒ID=4／氏名=武士山美咲／数学=75／英語=95
4件目：生徒ID=3／氏名=戦国広志／数学=75／英語=85
5件目：生徒ID=1／氏名=侍健太／数学=65／英語=90
*/

package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;

public class Scores_Chapter10 {

	public static void main(String[] args) {
        Connection con = null;
        Statement statement = null;

        try {
            // データベースに接続
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/java_challenge",
                "root",
                "Kogasa_613"
            );

            System.out.println("データベース接続成功:" + con);

            // SQLクエリを準備
            statement = con.createStatement();
            String sql = "UPDATE scores SET score_math = 95, score_english = 80 WHERE id = 5";
            String sql2 = "SELECT * FROM scores ORDER BY  score_math ASC ,score_english ASC" ;

            // SQLクエリを実行（DBMSに送信）
            //アップデート
            System.out.println("レコードの更新を実行します");
            int rowCnt = statement.executeUpdate(sql);
            System.out.println( rowCnt + "件のレコードが更新されました");
            //並び替え
            ResultSet result = statement.executeQuery(sql2);
            System.out.println("数学・英語の点数が高い順に並べ替えました");

            // SQLクエリの実行結果を抽出
            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                int score_math = result.getInt("score_math");
                int score_english = result.getInt("score_english");

                System.out.println(result.getRow() + "件目：生徒ID=" + id +
                                    "／氏名=" + name +
                                   "／数学の点数	=" + score_math +
                                   "／英語の点数	=" + score_english );
            }
        } catch(InputMismatchException e) {
            System.out.println("入力が正しくありません");
        } catch(SQLException e) {
            System.out.println("エラー発生：" + e.getMessage());
        } finally {
            // 使用したオブジェクトを解放
            if( statement != null ) {
                try { statement.close(); } catch(SQLException ignore) {}
            }
            if( con != null ) {
                try { con.close(); } catch(SQLException ignore) {}
            }
        }

	}

}
