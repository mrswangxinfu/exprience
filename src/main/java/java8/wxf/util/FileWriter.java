package java8.wxf.util;

import java.io.*;
import java.util.List;

/**
 * 文件写入
 */
public class FileWriter {
    /**
     * 按行写入所有数据
     * @param result 数据源
     * @param charsetName 字符集
     * @throws IOException
     */
    public void writeLines(List<String> result,String charsetName) throws IOException{

        int length = result.size();
        int i = 0;
        File file = new File("C:\\Users\\86183\\Desktop\\test.txt");
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream,charsetName);
        BufferedWriter buf = new BufferedWriter(writer);
        while (length > 0){
            buf.write(result.get(i));
            buf.newLine();
            length--;
            i++;
        }
        buf.close();
        writer.close();
    }
}
