package com.online.web;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class CSVUtils {

    // 单次读取数据量
    public static final int LIST_SIZE = 2000;

    /**
     * 按行读取csv文件并对csv行记录执行func操作
     *
     * @param reader csv文件
     * @param func    回调函数，接收List {@link CSVRecord} 对象，大小取决于 {@link CSVUtils#LIST_SIZE}
     * @throws IOException 文件IO异常
     */
    public static void readCSV(InputStreamReader reader, Function<List<CSVRecord>, ?> func) throws IOException {
        CSVFormat format = CSVFormat.Builder.create()
                .setHeader() // 读取header作为csv的key，否则CSVRecord.get(headerName)会报错
                .setSkipHeaderRecord(true) // 跳过第一行的列名，列名单独是文件的自行搜索CSVFormat构造
                .build();
        CSVParser parse = format.parse(reader);
        Iterator<CSVRecord> csvRecordIterator = parse.iterator();
        // 2023/12/08 更新：写demo时没注意，实际应当使用ConcurrentLinkedQueue同步队列保证线程安全
        List<CSVRecord> list = new ArrayList<>(LIST_SIZE);
        for (int i = 0; i < LIST_SIZE && csvRecordIterator.hasNext(); i++) {
            list.add(csvRecordIterator.next());
            if (i == LIST_SIZE - 1 && csvRecordIterator.hasNext()) {
                i = -1;
                func.apply(list); // 实际使用场景请使用线程池
                list = new ArrayList<>(LIST_SIZE);
            }
        }
        if (!list.isEmpty()) {
            func.apply(list); // 实际使用场景请使用线程池
        }
    }

}

