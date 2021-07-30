package java8.wxf.util;



import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件读取
 */
public class FileReader{
    /**
     * 按行读取所有数据
     * @param charsetName 字符集
     * @return
     * @throws IOException
     */
    public List<String> readLines(String charsetName) throws IOException{
        List<String> result = new ArrayList<>();
        File file = new File("C:\\Users\\86183\\Desktop\\javac.txt");
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream, charsetName);
        BufferedReader buf = new BufferedReader(reader);
        String str;
        while ((str = buf.readLine()) != null){
            result.add(str);
        }
        buf.close();
        reader.close();
        return result;
    }
}
